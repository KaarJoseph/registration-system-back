package ec.edu.ups.practicaMicroserviciosDocker.servicio;

import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import ec.edu.ups.practicaMicroserviciosDocker.model.Curso;
import ec.edu.ups.practicaMicroserviciosDocker.negocio.GestionCurso;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("curso")
public class GCursoService {
    @Inject
    private GestionCurso gestionCurso;

    @POST
    @Path("crear")
    @Produces("application/json")
    @Consumes("application/json")
    public Response guardarCurso(Curso curso) {
        try {
            gestionCurso.guardarCurso(curso);
            return Response.status(Response.Status.OK).entity(curso).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al guardar: "+e.getMessage());
            return Response.status(Response.Status.OK).entity(error).build();
        }
    }
    @GET
    @Path("all")
    @Produces("application/json")
    public Response getCursos() {
        List<Curso> listado = gestionCurso.listar();
        if(gestionCurso.listar().isEmpty()) {
            return Response.status(Response.Status.OK).entity("Lista vacia").build();
        }else {
            return Response.status(Response.Status.OK).entity(listado).build();}
    }
    @DELETE
    @Path("borrar/{id}")
    public void borrar( @PathParam("id") int id) {
        gestionCurso.borrar(id);
    }
    @GET
    @Path("buscar/{id}")
    @Produces("application/json")
    public Response buscar(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(gestionCurso.buscar(id)).build();
    }
}
