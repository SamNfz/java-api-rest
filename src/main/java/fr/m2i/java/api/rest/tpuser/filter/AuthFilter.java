package fr.m2i.java.api.rest.tpuser.filter;

import fr.m2i.java.api.rest.tpuser.model.User;
import fr.m2i.java.api.rest.tpuser.util.BasicAuth;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class AuthFilter implements ContainerRequestFilter {

    @Context
    public HttpServletRequest request;
            
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        // On récupère les identifiants / mot de passe depuis le header
        String auth = requestContext.getHeaderString("Authorization");
        
        if (auth == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You must be connected")
                    .build()
            );
        }
        
        // LAP => Login and Password
        String[] lap = BasicAuth.decode(auth);
        
        // On vérifie que lap est au bon format
        if (lap == null || lap.length != 2) {
            throw new WebApplicationException(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You must be connected")
                    .build()
            );
        }
    }
    
    public User checkUser(String email, String password) {
        User admin = new User("Super", "admin", "SUPER_ADMIN", "super@admin.com", "admin");

        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return admin;
        }

        return null;
    }
       
}
