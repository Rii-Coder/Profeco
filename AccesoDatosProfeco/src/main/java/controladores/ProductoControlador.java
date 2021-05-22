/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entities.Producto;
import java.util.ArrayList;

/**
 *
 * @author Dhtey
 */
public class ProductoControlador {
    private ArrayList<Producto> productos;

    public ProductoControlador() {
        this.productos = new ArrayList<>();
        Producto producto1 = new Producto(1, "Queso", 25.00);
        Producto producto2 = new Producto(2, "Jamon", 35.00);
        Producto producto3 = new Producto(3, "Pan", 15.00);
        Producto producto4 = new Producto(4, "Mayoonesa", 30.00);
        Producto producto5 = new Producto(5, "Salsa", 20.00);
    }
    
    public void create(Producto producto){
        this.productos.add(producto);
    }
    
    public void delete(Producto producto){
        this.productos.remove(producto);
    }
    
    public void update(Producto producto){
        int index = this.productos.indexOf(producto);
        this.productos.set(index, producto);
    }
    
    public ArrayList<Producto> getAll(){
        return productos;
    }
    
    public Producto find(int id){
        for(Producto producto : this.productos){
            if(producto.getId() == id){
                return producto;
            }
        }
        return null;
    }
    
    public boolean validate(Producto producto){
        for(Producto p : this.productos){
            if(p.equals(producto)){
                return true;
            }
        }
        return false;
    }
}
