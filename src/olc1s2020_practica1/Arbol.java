/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

/**
 *
 * @author aiyel
 */
public class Arbol {
    
    public Nodo raiz;
    private String nombre;
    public Arbol(String nombre){
        this.raiz = new Nodo();
        this.nombre = nombre;
    }
    
    public Arbol(){
        this.raiz = new Nodo();
        this.nombre = "";
    }
    
    public void insertar(){
        
    }
    
    public String generaDotCode_Tree(){
        String cuerpo = "";
        
        return cuerpo;
    }
    
    public String generarDotCode_NextTable(){
        String cuerpo = "";
        return cuerpo;
    }
    
    public String generarDotCode_TransitionTable(){
        String cuerpo = "";
        return cuerpo;
    }
    
}
