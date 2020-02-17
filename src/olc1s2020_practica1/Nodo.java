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
public class Nodo {
    public Nodo izq;
    public Nodo der;
    private int identificador;
    private String caracter;
    private LinkedList<Integer> primeros;
    private LinkedList<Integer> ultimos;
    private String anulabilidad;
    private String tipo;
    private boolean anulable;
    
    public Nodo(){
        this.izq = null;
        this.der = null;
        this.caracter = "";
        this.identificador = 0;
        this.anulabilidad = "";
    }
    
    public Nodo(String caracter,int identificador,String tipo){
        this.identificador = identificador;
        this.caracter = caracter;
        this.primeros = new LinkedList();
        this.ultimos = new LinkedList();
        setTipo(tipo);
    }
    
    public Nodo(String caracter,int identificador,String tipo,String anulabilidad){
        this.identificador = identificador;
        this.caracter = caracter;
        setTipo(tipo);
        this.anulabilidad = anulabilidad;
        this.primeros = new LinkedList();
        this.ultimos = new LinkedList();
    }
    
    public Nodo(String caracter,int identificador,String tipo,LinkedList<Integer> primeros, LinkedList<Integer> ultimos){
        this.caracter = caracter;
        this.identificador = identificador;
        this.primeros = primeros;
        this.ultimos = ultimos;
        this.izq = null;
        this.der = null;
        setTipo(tipo);
        
    }
    

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public LinkedList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(LinkedList<Integer> primeros) {
        this.primeros = primeros;
    }

    public LinkedList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(LinkedList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    

    public String getAnulabilidad() {
        return anulabilidad;
    }

    public void setAnulabilidad(String anulabilidad) {
        this.anulabilidad = anulabilidad;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }
    
    

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo (String tipo){
        this.tipo = tipo;
        String aux = tipo.toUpperCase();
        if(aux.equals("PUNTO")){//Para '.'
            this.anulabilidad = "N";
            this.anulable = false;
        }else if(aux.equals("OR")){//Para '|'
            this.anulabilidad = "N";
            this.anulable = false;
        }else if(aux.equals("ASTERISCO")){//Para '*'
            this.anulabilidad = "A";
            this.anulable = true;
        }else if(aux.equals("MAS")){//Para '+'
            this.anulabilidad = "N";
            this.anulable = false;
        }else if (aux.equals("INTERROGACION")){//Para '?'
            this.anulabilidad = "A";
            this.anulable = true;
        }else{//Para cadenas y conjuntos
            this.anulabilidad = "N";
            this.anulable = false;
        }
    }
    
    
    
    
}
