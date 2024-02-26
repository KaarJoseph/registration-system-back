package ec.edu.ups.practicaMicroserviciosDocker.dao;

import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import ec.edu.ups.practicaMicroserviciosDocker.model.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Stateless
public class DocenteDAO implements Serializable {
    @PersistenceContext
    private EntityManager em;

    public void create(Docente docente) {
        em.persist(docente);
    }
    public Docente read(String cedula) {
        Docente c = em.find(Docente.class, cedula);
        return c;
    }
    public void update(Docente docente) {
        em.merge(docente);
    }
    public void delete(String cedula) {
        Docente c = em.find(Docente.class, cedula);
        em.remove(c);
    }
    public List<Docente> getAll(){
        String jpql = "SELECT d FROM Docente d";
        Query q = em.createQuery(jpql);
        return q.getResultList();
    }
}
