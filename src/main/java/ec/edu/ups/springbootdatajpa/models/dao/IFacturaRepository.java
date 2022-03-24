package ec.edu.ups.springbootdatajpa.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ec.edu.ups.springbootdatajpa.models.entity.Factura;

public interface IFacturaRepository extends CrudRepository<Factura, Long> {
    
    @Query("select f from Factura f join fetch f.cliente c join fetch f.items i join fetch i.producto p where f.id=?1")
    public Factura fecthByIdWithClienteWithItemFacturaWithProducto(Long id);
}
