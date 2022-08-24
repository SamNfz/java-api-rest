package fr.m2i.java.api.rest;

import javax.ws.rs.*;

@Path("/personnes")
public class PersonneResource {
    
    // URI : /personnes/
    @GET
    public String getPersonnes() {
        System.out.println("Endpoint : getPersonnes");
        return "Jean Neymar / Jean Peuplus / Jean Sairien";
    }

    // URI : /personnes/1
    @GET
    @Path("/{id}")
    public String getPersonneById(@PathParam("id") int id) {
        System.out.println("Endpoint : getPersonneById");
        return "Personne avec id : " + id + " - Jean Neymar";
    }
    
    // URI : /personnes/create-from-form
    @POST
    @Path("/create-from-form")
    @Consumes("application/x-www-form-urlencoded")
    public String createFromForm(@FormParam("prenom") String prenom, @FormParam("nom") String nom) {
        System.out.println("Endpoint : create from form");
        return "Le nom complet de la personne est : " + prenom + " " + nom;
    }
}
