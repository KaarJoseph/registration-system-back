package ec.edu.ups.practicaMicroserviciosDocker.negocio;

import ec.edu.ups.practicaMicroserviciosDocker.dao.CursoDAO;
import ec.edu.ups.practicaMicroserviciosDocker.model.Curso;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
@Stateless
public class GestionCurso {
    @Inject
    private CursoDAO cursoDAO;
    public void guardarCurso(Curso curso) throws Exception {
            if(cursoDAO.read(curso.getId())==null) {
                try {
                    cursoDAO.create(curso);
                } catch (Exception e) {
                    throw new Exception("Error al insertar: " + e.getMessage());
                }
            } else {
                try {
                    cursoDAO.update(curso);
                } catch (Exception e) {
                    throw new Exception("Error al actualizar: "+e.getMessage());
                }
            }
    }

    //Listar
    public List<Curso> listar(){
        return cursoDAO.getAll();
    }
    //Borrar
    public boolean borrar(int id) {
        cursoDAO.delete(id);
        return true;
    }
    //Buscar
    public Curso buscar(int id) {
        return cursoDAO.read(id);
    }
}
