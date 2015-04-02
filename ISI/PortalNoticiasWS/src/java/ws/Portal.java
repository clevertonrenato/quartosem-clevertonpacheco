/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import modelo.Tema;

/**
 * REST Web Service
 *
 * @author Renato
 */
@Path("portal")
public class Portal {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Portal
     */
    public Portal() {
    }

    /**
     * Retrieves representation of an instance of ws.Portal
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        Tema tema = new Tema();
        tema.setNome("classificados");
        tema.setCor_fundo("black");
        tema.setCor_titulo("yellow");
        Gson g = new Gson();
        
       return g.toJson(tema);
    }
    
    @GET
    @Produces("text/plain")
    @Path("getTema")
    public String getTema() {
        //TODO return proper representation object
        return"Aqui vai retornar o tema";
    }
    
    @GET
    @Produces("text/plain")
    @Path("getNoticia/{id}")
    public String getNoticia(@PathParam("id")Integer id) {
        //TODO return proper representation object
        return"Aqui vai retornar a noticia cujo id e" + id;
    }

    /**
     * PUT method for updating or creating an instance of Portal
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
