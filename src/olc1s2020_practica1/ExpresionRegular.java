/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

import java.util.LinkedList;

/**
 *
 * @author aiyel
 */
public class ExpresionRegular {
    
    private String nombre;
    private LinkedList<String> elementos;
    
    public ExpresionRegular(String nombre,LinkedList<String> elementos){
        this.elementos = elementos;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<String> getElementos() {
        return elementos;
    }

    public void setElementos(LinkedList<String> elementos) {
        this.elementos = elementos;
    }
    
    public String toString(){
        String texto = "Expresion Regular-> "+this.nombre;
        if(!this.elementos.isEmpty()){
            texto += " elementos: ";
            for(String i:elementos){
                texto += i;
            }
        }
        return texto;
    }
    
}
