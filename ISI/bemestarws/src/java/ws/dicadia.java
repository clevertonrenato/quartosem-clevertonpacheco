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
import modelo.Produto;

/**
 * REST Web Service
 *
 * @author Renato
 */
@Path("bemestarws")
public class dicadia {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public dicadia() {
    }

    /**
     * Retrieves representation of an instance of ws.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("dicadia")
    public String getJson() {
        Gson g = new Gson();
        return g.toJson("beba muita agua");
    }

    @GET
    @Produces("application/json")
    @Path("imc/{alt}/{peso}")

    public String getimc(@PathParam("alt") Float altura, @PathParam("peso") Float peso) {

        Gson g = new Gson();

        return g.toJson(peso / (altura * altura));
    }

    @GET
    @Produces("application/json")
    @Path("calorias/{alimento}")
    public String getCalorias(@PathParam("alimento") String alimento) {
        //TODO return proper representation object
        Produto produto = new Produto();
        produto.buscarAlimentos(alimento);
        Gson g = new Gson();
        return g.toJson(produto);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
