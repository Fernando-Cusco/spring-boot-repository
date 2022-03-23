package ec.edu.ups.springbootdatajpa.models.dao;

import org.springframework.data.repository.CrudRepository;

import ec.edu.ups.springbootdatajpa.models.entity.Factura;

public interface IFacturaRepository extends CrudRepository<Factura, Long> {
    
}
