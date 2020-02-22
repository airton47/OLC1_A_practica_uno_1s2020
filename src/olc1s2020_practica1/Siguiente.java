/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author aiyel
 */
public class Siguiente {
    private int id_nodo;
    private LinkedList<Integer> siguientes;
    public Siguiente(int id){
        this.id_nodo = id;
        this.siguientes = new LinkedList<>();
    }    

    public int getId_nodo() {
        return id_nodo;
    }

    public void setId_nodo(int id_nodo) {
        this.id_nodo = id_nodo;
    }

    public LinkedList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(LinkedList<Integer> siguientes) {
        this.siguientes = siguientes;
    }
    
    public String siguientesToString(){
        String a = "Node "+this.getId_nodo()+": ";
        for(Integer i:siguientes){
            a+= i+ " ";
        }
        /*
        
        */
        return a;
    }
    
}
