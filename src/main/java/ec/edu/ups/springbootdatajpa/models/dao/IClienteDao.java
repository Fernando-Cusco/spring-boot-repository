package ec.edu.ups.springbootdatajpa.models.dao;

import java.util.List;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;

public interface IClienteDao {

    public List<Cliente> findAll();

    public void save(Cliente cliente);

    public Cliente findOne(Long id);
    
    public void delete(Long id);
}
