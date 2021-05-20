package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import entities.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author R2
 */
@WebServlet(urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String usuario = request.getParameter("user");
            String contra = request.getParameter("password");
    

            Usuario usuarioObj = new Usuario(0, usuario, contra, "asdf");

            URL url = new URL("http://localhost:8080/profeco/api/auth/login");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);

            Gson gson = new Gson();
            String jsonInputString = gson.toJson(usuarioObj);

            System.out.println("Json " + jsonInputString);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes();
                os.write(input, 0, input.length);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

                StringBuilder respuesta = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    respuesta.append(responseLine.trim());
                }
                System.out.println(respuesta.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (con.getResponseMessage().equals("Created")) {
                if (usuario.equals("mercado")) {
                    response.sendRedirect("MenuMercado.html");
                    
                }else if(usuario.equals("profeco")){
                    response.sendRedirect("MenuProfeco.html");
                }else{
                    response.sendRedirect("MenuConsumidor.html");
                }
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Pagina correcta</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Credenciales correctas</h1>");
                out.println("</body>");
                out.println("</html>");
                
            } else if (con.getResponseCode() == 401) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Regrese al login e ingrese una credencial valida</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1> Credenciales invalidas</h1>");
                out.println("<a href=\"index.html#\">LOGIN</a>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {
            System.out.println("Error 0");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
