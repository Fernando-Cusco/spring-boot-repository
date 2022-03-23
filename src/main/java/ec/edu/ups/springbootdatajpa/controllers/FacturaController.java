package ec.edu.ups.springbootdatajpa.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;
import ec.edu.ups.springbootdatajpa.models.entity.Factura;
import ec.edu.ups.springbootdatajpa.models.entity.ItemFactura;
import ec.edu.ups.springbootdatajpa.models.entity.Producto;
import ec.edu.ups.springbootdatajpa.models.services.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable("clienteId") Long clienteId, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteService.findOne(clienteId);
        if (cliente == null) {
            flash.addFlashAttribute("danger", "No existe el cliente para facturar");
            return "redirect:/listar";
        }
        Factura factura = new Factura();
        factura.setCliente(cliente);
        model.addAttribute("factura", factura);
        model.addAttribute("titulo", "Nueva Factura para el cliente: " + cliente.getNombre() + " " + cliente.getApellido());
        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
        return clienteService.findByNombre(term);
    }
    

    @PostMapping("/form")
    public String guardar(@Valid Factura factura, BindingResult result, @RequestParam(name = "item_id[]", required = false) Long[] itemId, @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash, SessionStatus status, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Nueva Factura para el cliente: " + factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
            return "factura/form";
        }
        if (itemId == null || itemId.length == 0) {
            model.addAttribute("titulo", "Nueva Factura para el cliente: " + factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
            model.addAttribute("danger", "Debe agregar productos para el detalle de la factura");
            return "factura/form";
        }
        int cont = 0;
        for(Long id: itemId) {
            Producto producto = clienteService.findProductoById(id);
            ItemFactura itemFactura = new ItemFactura();
            itemFactura.setCantidad(cantidad[cont]);
            itemFactura.setProducto(producto);
            factura.agregarItemFactura(itemFactura);
            cont++;
        }
        clienteService.saveFactura(factura);
        status.setComplete();
        flash.addFlashAttribute("success", "Factura guardada con éxito");
        return "redirect:/detalle/" + factura.getCliente().getId();
    }
    
}
