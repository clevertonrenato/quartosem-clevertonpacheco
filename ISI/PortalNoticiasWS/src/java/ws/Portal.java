/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import modelo.Noticia;
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
    @Path("getNoticias")
    public String getNoticia() {
       List<Noticia> lista = new ArrayList<Noticia>();
       Noticia n = new Noticia();
       n.setFonte("G1");
       n.setId(1);
       n.setTexto("Presidente da Câmara resolveu aproveitar quórum para aprovar proposta.\n" +
"PEC amplia de 70 para 75 anos aposentadoria de ministro do STF.");
       n.setTitulo("Política");
       lista.add(n);
       n = new Noticia();
       n.setFonte("G1");
       n.setId(2);
       n.setTexto("O juiz federal Sergio Moro autorizou a transferência de R$ 157 milhões do"
               + " Ministério Público Federal (MPF) para uma conta da Petrobras. O valor, conforme"
               + " o despacho, é referente a uma devolução de recursos com origem em crimes de "
               + "corrupção confessados pelo ex-gerente da estatal Pedro Barusco. Os valores devem "
               + "ser devolvidos em cerimônia no dia 11 de maio.");
       n.setTitulo("Justiça Federal autoriza devolução de R$ 157 milhões para a Petrobras");
       lista.add(n);
       
       Gson g = new Gson();
       return g.toJson(lista);
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
