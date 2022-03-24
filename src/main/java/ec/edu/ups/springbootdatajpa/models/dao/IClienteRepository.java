package ec.edu.ups.springbootdatajpa.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;

public interface IClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
    
    @Query("select c from Cliente c left join fetch c.facturas f where c.id = ?1")
    public Cliente fetchByIdWithFacturas(Long id);
}
