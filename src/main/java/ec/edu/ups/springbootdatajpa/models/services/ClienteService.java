package ec.edu.ups.springbootdatajpa.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.ups.springbootdatajpa.models.dao.IClienteRepository;
import ec.edu.ups.springbootdatajpa.models.dao.IFacturaRepository;
import ec.edu.ups.springbootdatajpa.models.dao.IProductoRepository;
import ec.edu.ups.springbootdatajpa.models.entity.Cliente;
import ec.edu.ups.springbootdatajpa.models.entity.Factura;
import ec.edu.ups.springbootdatajpa.models.entity.Producto;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IFacturaRepository facturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
        
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreLikeIgnoreCase("%"+nombre+"%");
    }

    @Override
    @Transactional(readOnly = false)
    public void saveFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura fecthByIdWithClienteWithItemFacturaWithProducto(Long id) {
        return facturaRepository.fecthByIdWithClienteWithItemFacturaWithProducto(id);
    }

    @Override
    public Cliente fetchByIdWithFacturas(Long id) {
        return clienteRepository.fetchByIdWithFacturas(id);
    }   
}