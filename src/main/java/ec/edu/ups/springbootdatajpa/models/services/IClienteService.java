package ec.edu.ups.springbootdatajpa.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;
import ec.edu.ups.springbootdatajpa.models.entity.Factura;
import ec.edu.ups.springbootdatajpa.models.entity.Producto;

public interface IClienteService {
    
    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public void save(Cliente cliente);

    public Cliente findOne(Long id);
    
    public void delete(Long id);

    public List<Producto> findByNombre(String nombre);

    public void saveFactura(Factura factura);

    public Producto findProductoById(Long id);

    public Factura findFacturaById(Long id);

    public void deleteFactura(Long id);
}
