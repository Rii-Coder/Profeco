/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entities.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class UsuarioControlador {
    
    private ArrayList<Usuario> usuarios;
    
    public UsuarioControlador(){
        this.usuarios = new ArrayList<Usuario>();
        this.usuarios.add(new Usuario(1, "profeco", "profeco", "profeco"));
        this.usuarios.add(new Usuario(1, "consumidor", "consumidor", "consumidor"));
        this.usuarios.add(new Usuario(1, "comercio", "comercio", "comercio"));
        
    }
    
    public void create(Usuario usuario){
        this.usuarios.add(usuario);
    }
    
    public void delete(Usuario usuario){
        this.usuarios.remove(usuario);
    }
    
    public void update(Usuario usuario){
        int index = this.usuarios.indexOf(usuario);
        this.usuarios.set(index, usuario);
    }
    
    public Usuario find(int id){
        for(Usuario usuario : this.usuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }
    
    public boolean validate(Usuario usuario){
        for(Usuario u : this.usuarios){
            if(u.getUsername().equals(usuario.getUsername()) && u.getPassword().equals(usuario.getPassword())){
                return true;
            }
        }
        return false;
    }
}
