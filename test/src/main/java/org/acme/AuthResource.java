package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
public class AuthResource {

    @GET
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    public String generateToken() {
        // Genera un token per un utente di prova
        return JwtGenerator.generateJwt("user");
    }
}
