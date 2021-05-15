/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import DAO.UsuarioDAO;
import Entidades.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author R2
 */
@Path("auth")
public class LoginServicio {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response validar(Usuario usuario) throws IllegalArgumentException, UnsupportedEncodingException {
        boolean status = UsuarioDAO.validar(usuario.getUsername(), usuario.getPassword());
        String token = null;
        if (status) {

            try {
                Algorithm algorithm = Algorithm.HMAC256("millave");
                token = JWT.create()
                        .withIssuer("auth0")
                        .sign(algorithm);

            } catch (JWTCreationException e) {
                e.printStackTrace();
            }
            JsonObject json = Json.createObjectBuilder()
                                .add("JWT", token).build();
            
            return Response.status(Response.Status.CREATED).entity(json).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("obtener")
    public Response obtener() {
        Usuario usuario = new Usuario(0, "abraham", "123123");


        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }
    
    

}
