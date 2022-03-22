package ec.edu.ups.springbootdatajpa.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;

public interface IClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
    
}
