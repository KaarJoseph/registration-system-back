package ec.edu.ups.practicaMicroserviciosDocker.servicio;

import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import ec.edu.ups.practicaMicroserviciosDocker.model.Docente;
import ec.edu.ups.practicaMicroserviciosDocker.negocio.GestionDocente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("docente")
public class GDocenteService {
    @Inject
    private GestionDocente gestionDocente;

@POST
@Path("crear")
@Produces("application/json")
@Consumes("application/json")
public Response guardarDocente(Docente docente){
    try{
        gestionDocente.guardarCliente(docente);
        return Response.status(Response.Status.OK).entity(docente).build();
    } catch (Exception e){
        e.printStackTrace();
        Error error = new Error();
        error.setCodigo(99);
        error.setMensaje("Error al guardar:"+e.getMessage());
        return Response.status(Response.Status.OK).entity(error).build();
    }
}
    @GET
    @Path("all")
    @Produces("application/json")
    public Response getDocente() {
        List<Docente> listado = gestionDocente.listar();
        if(gestionDocente.listar().isEmpty()) {
            return Response.status(Response.Status.OK).entity("Lista vacia").build();
        }else {
            return Response.status(Response.Status.OK).entity(listado).build();}
    }
    @DELETE
    @Path("borrar/{cedula}")
    public void borrar( @PathParam("cedula") String cedula) {
        gestionDocente.borrar(cedula);
    }
    @GET
    @Path("buscar/{cedula}")
    @Produces("application/json")
    public Response buscar(@PathParam("cedula") String cedula) {
        return Response.status(Response.Status.OK).entity(gestionDocente.buscar(cedula)).build();
    }
}
