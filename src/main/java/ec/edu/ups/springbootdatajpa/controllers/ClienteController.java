package ec.edu.ups.springbootdatajpa.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;
import ec.edu.ups.springbootdatajpa.models.services.IClienteService;
import ec.edu.ups.springbootdatajpa.services.IUploadFileService;
import ec.edu.ups.springbootdatajpa.utils.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
    
    

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUploadFileService uploadFileService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, "createAt", new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/listar")
    private String listarClientes(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 3);
        Page<Cliente> clientes = clienteService.findAll(pageRequest);
        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clientes);
        model.addAttribute("page", pageRender);
        return "listar";
    }

    @GetMapping("/form")
    public String crear(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("titulo", "Formulario de Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("boton", "Crear cliente");
        return "form";
    }

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename()+"\"").body(resource);
    }

    @PostMapping("/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
        boolean editando = (cliente.getId() != null)? true: false;
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Cliente");
            if (editando) {
                model.addAttribute("boton", "Editar cliente");
            } else {
                model.addAttribute("boton", "Crear cliente");
            }
            return "form";
        }
        if (!foto.isEmpty()) {
            if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
                uploadFileService.delete(cliente.getFoto());
            }
            String unique = null;
            try {
                unique = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            flash.addFlashAttribute("info", "Se ha subido correctamente el archivo");
            cliente.setFoto(unique);
        }
        clienteService.save(cliente);
        status.setComplete();
        if (editando) {
            flash.addFlashAttribute("success", "Cliente actualizado con exito");
        } else {
            flash.addFlashAttribute("success", "Cliente creado con exito");
        }
        return "redirect:/listar";
    }

    @GetMapping("/form/{id}")
    public String cargarCliente(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = null;
        if (id > 0) {
            cliente = clienteService.findOne(id);
            if (cliente == null) {
                flash.addFlashAttribute("danger", "El cliente no existe en la base de datos");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("danger", "Cliente no encontrado");
            return "redirect:/listar";
        }
        model.addAttribute("titulo", "Editar de Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("boton", "Editar cliente");
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (id > 0) {
            Cliente cliente = clienteService.findOne(id);
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con exito"); 
            if (uploadFileService.delete(cliente.getFoto())) {
                flash.addFlashAttribute("info", "Foto eliminada con exito");
            }
            
        } else {
            flash.addFlashAttribute("danger", "Cliente no encontrado");
        }
        return "redirect:/listar";
    }

    @GetMapping("/detalle/{id}")
    public String verFoto(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteService.findOne(id);
        if (cliente == null) {
            flash.addFlashAttribute("danger", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }
        model.addAttribute("titulo", "Detalle cliente");
        model.addAttribute("cliente", cliente);
        return "ver";
    }

}
