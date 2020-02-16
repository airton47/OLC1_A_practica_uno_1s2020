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
    private String tipo;
    public Conjunto(String nombre,String tipo){
        this.nombre = nombre;
        this.elementos = new LinkedList<>();
        this.tipo = tipo;
    }
    
    public Conjunto(String nombre,String tipo,LinkedList<String> elementos){
        this.nombre = nombre;
        this.elementos = elementos;
        this.tipo = tipo;
    }
    
    public void setElementos(String[] elementos){
        for(String e:elementos){
            this.elementos.add(e);
        }
    }
    
    public LinkedList<String> getElementos(){
        return this.elementos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    public boolean pertenece(String caracter){
        boolean value = false;
        if(!elementos.isEmpty()){
            if(this.tipo == "INTERVALO"){
                char c = caracter.charAt(0);
                char inicio = this.elementos.get(0).charAt(0);
                char fin = this.elementos.get(1).charAt(0);
                if(c>=inicio & c<=fin){
                    value = true;
                }
            }else if(this.tipo == "LISTA"){
                for(String e:elementos){
                    if(e.equals(caracter)){
                        return true;
                    }
                }
            }
        }
        return value;
    }
    
    public String toString(){
        String texto = "";
        texto += "Conjunto -> " +this.nombre;
        if(!elementos.isEmpty()){
            texto += " elementos: ";
            for(String st:elementos){
                texto += st+" ";
            }
            
        }
        return texto;
    }
    
}
