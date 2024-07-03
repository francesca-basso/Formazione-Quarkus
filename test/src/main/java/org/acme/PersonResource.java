package org.acme;

import org.acme.entity.Person;
import org.acme.services.PersonService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
public class PersonResource {
    
    @Inject
    PersonService personService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person newPerson) {
        try {
            Person createdPerson = personService.createPerson(newPerson.getName());
            return Response.status(Response.Status.CREATED)
                    .entity(createdPerson)
                    .build();
        } catch (Exception e) {
            throw new WebApplicationException("Errore durante l'aggiunta della persona: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        Person person = personService.findPersonById(id);
        if (person != null) {
            return Response.ok(person).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
