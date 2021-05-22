/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entities.Oferta;
import java.util.ArrayList;

/**
 *
 * @author R2
 */
public class OfertaControlador {
    
    private ArrayList<Oferta> ofertas;

    public OfertaControlador() {
        this.ofertas = new ArrayList<>();
        //agregar ofertas
    }
    
    public void create(Oferta oferta){
        this.ofertas.add(oferta);
    }
    
    public void delete(Oferta oferta){
        this.ofertas.remove(oferta);
    }
    
    public void update(Oferta oferta){
        int index = this.ofertas.indexOf(oferta);
        this.ofertas.set(index, oferta);
    }
    
    public ArrayList<Oferta> getAll(){
        return ofertas;
    }
    
    public Oferta find(int id){
        for(Oferta oferta : this.ofertas){
            if(oferta.getId() == id){
                return oferta;
            }
        }
        return null;
    }
    
    public boolean validate(Oferta oferta){
        for(Oferta o : this.ofertas){
            if(o.equals(oferta)){
                return true;
            }
        }
        return false;
    }
}
