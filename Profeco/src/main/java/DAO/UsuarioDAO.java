/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author R2
 */
public class UsuarioDAO {
    
    public static boolean validar(String username, String password){
        return(username.equals("admin") && password.equals("admin"));
    }
    
}
