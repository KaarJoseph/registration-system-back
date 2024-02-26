package ec.edu.ups.practicaMicroserviciosDocker.servicio;

import java.util.List;


import ec.edu.ups.practicaMicroserviciosDocker.model.Cliente;
import ec.edu.ups.practicaMicroserviciosDocker.negocio.GestionCliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("cliente")
public class GClienteService {
@Inject
private GestionCliente gCliente;
//servicios
@POST
@Path("crear")
@Produces("application/json")
@Consumes("application/json")
public Response guardarCliente(Cliente cliente) {
	try {
		gCliente.guardarCliente(cliente);
		return Response.status(Response.Status.OK).entity(cliente).build();
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
public Response getClientes() {
	List<Cliente> listado = gCliente.listar();
	if(gCliente.listar().isEmpty()) {
		return Response.status(Response.Status.OK).entity("Lista vacia").build();
	}else {
	return Response.status(Response.Status.OK).entity(listado).build();}
}
@DELETE
@Path("borrar/{cedula}")
public void borrar( @PathParam("cedula") String cedula) {
	gCliente.borrar(cedula);
}
@GET
@Path("buscar/{cedula}")
@Produces("application/json")
public Response buscar(@PathParam("cedula") String cedula) {
	return Response.status(Response.Status.OK).entity(gCliente.buscar(cedula)).build();
}
}
