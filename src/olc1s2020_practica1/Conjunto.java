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
public class Conjunto {
    
    private String nombre;
    private LinkedList<String> elementos;
    
    public Conjunto(String nombre){
        this.nombre = nombre;
        this.elementos = new LinkedList<>();
    }
    
    public Conjunto(String nombre,String[] elementos){
        this.nombre = nombre;
        setElementos(elementos);
    }
    
    public void setElementos(String[] elementos){
        for(String e:elementos){
            this.elementos.add(e);
        }
    }
    
    public LinkedList<String> getElementos(){
        return this.elementos;
    }
    
    public boolean pertenece(String caracter){
        boolean value = false;
        if(!elementos.isEmpty()){
            for(String e:elementos){
                if(e.equals(caracter)){
                    value = true;
                }
            }
        }
        return value;
    }
    
}
