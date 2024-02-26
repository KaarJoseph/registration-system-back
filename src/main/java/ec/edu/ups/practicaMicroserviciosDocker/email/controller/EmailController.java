package ec.edu.ups.practicaMicroserviciosDocker.email.controller;

import ec.edu.ups.practicaMicroserviciosDocker.email.EmailRequest;
import ec.edu.ups.practicaMicroserviciosDocker.email.service.EmailService;

import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/email")
public class EmailController {

    @Inject
    private EmailService emailService;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(EmailRequest emailRequest) {
        try {
            emailService.sendEmail(emailRequest);
            return Response.ok().build();
        } catch (MessagingException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error sending email: " + e.getMessage()).build();
        }
    }


}
