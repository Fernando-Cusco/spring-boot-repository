package ec.edu.ups.springbootdatajpa.models.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ec.edu.ups.springbootdatajpa.models.entity.Cliente;

// @Repository
public class ClienteDao implements IClienteDao {


    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cliente> findAll() {
        // return em.createQuery("from Cliente").getResultList();
        return null;
    }

    @Override
    public void save(Cliente cliente) {
        // if (cliente.getId() != null && cliente.getId() > 0) {
        //     em.merge(cliente);
        // } else {
        //     em.persist(cliente);
        // }
    }

    @Override
    public Cliente findOne(Long id) {
        // return em.find(Cliente.class, id);
        return null;
    }

    @Override
    public void delete(Long id) {
        // em.remove(findOne(id));
    }
    
}
