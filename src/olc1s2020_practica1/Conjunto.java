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
    
}
