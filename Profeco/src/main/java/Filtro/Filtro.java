/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filtro;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.annotation.WebFilter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author R2
 */
@Provider
@WebFilter(filterName = "Filtro", urlPatterns = {"/*"})
public class Filtro implements ContainerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException, WebApplicationException {
        
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();
        System.out.println("Method: " + method);
        System.out.println("ENTRO AL FILTRO");
        if(method.equals("POST") && path.contains("login")){
            System.out.println("Entrando al filtro");
        }else{
            String token = requestContext.getHeaderString(AUTHENTICATION_HEADER);
            System.out.println("Token: " +  token);
            if(token != null){
                System.out.println("Verificar token");
            }else{
                throw new WebApplicationException(Status.UNAUTHORIZED);
            }
        }
        
    }

   
    private void verificarToken(String token) throws JWTVerificationException{
        try{
            Algorithm algorithm = Algorithm.HMAC256("millave");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
        }catch(JWTVerificationException e){
            throw new JWTVerificationException("Token no valido");
        }
    }
}
