package ec.edu.ups.practicaMicroserviciosDocker.dao;

import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ClienteDAO implements Serializable {
    @PersistenceContext
    private EntityManager em;

    public Cliente create(Cliente cliente) {
        em.persist(cliente);
        return cliente;
    }
    public Cliente read(String cedula) {
        Cliente c = em.find(Cliente.class, cedula);
        return c;
    }
    public void update(Cliente cliente) {
        em.merge(cliente);
    }
    public void delete(String cedula) {
        Cliente c = em.find(Cliente.class, cedula);
        em.remove(c);
    }
    public List<Cliente> getAll(){
        String jpql = "SELECT c FROM Cliente c";
        Query q = em.createQuery(jpql);
        return q.getResultList();
    }
}
