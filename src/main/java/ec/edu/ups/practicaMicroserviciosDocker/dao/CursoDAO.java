package ec.edu.ups.practicaMicroserviciosDocker.dao;

import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import ec.edu.ups.practicaMicroserviciosDocker.model.Curso;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.util.List;

@Stateless
public class CursoDAO implements Serializable {
    @PersistenceContext
    private EntityManager em;

    public void create(Curso curso) {
        em.persist(curso);
    }
    public Curso read(int id) {
        Curso c = em.find(Curso.class, id);
        return c;
    }
    public void update(Curso curso) {
        em.merge(curso);
    }
    public void delete(int id) {
        Curso c = em.find(Curso.class, id);
        em.remove(c);
    }
    public List<Curso> getAll(){
        String jpql = "SELECT c FROM Curso c";
        Query q = em.createQuery(jpql);
        return q.getResultList();
    }
}
