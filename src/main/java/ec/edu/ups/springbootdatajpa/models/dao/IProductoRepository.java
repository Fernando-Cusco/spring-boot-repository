package ec.edu.ups.springbootdatajpa.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ec.edu.ups.springbootdatajpa.models.entity.Producto;


public interface IProductoRepository extends CrudRepository <Producto, Long> {
    
    @Query("select p from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String nombre);

    public List<Producto> findByNombreLikeIgnoreCase(String nombre);

}
