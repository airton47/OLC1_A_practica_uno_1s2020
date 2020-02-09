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
    private int[] primeros;
    private int[] ultimos;
    private String anulabilidad;
    private Tipo tipo;
    
    public Nodo(){
        this.izq = null;
        this.der = null;
    }
    
    public Nodo(String caracter,int identificador,Tipo tipo,int[] primeros, int[] ultimos){
        this.caracter = caracter;
        this.identificador = identificador;
        this.primeros = primeros;
        this.ultimos = ultimos;
        this.izq = null;
        this.der = null;
        
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public int[] getPrimeros() {
        return primeros;
    }

    public void setPrimeros(int[] primeros) {
        this.primeros = primeros;
    }

    public int[] getUltimos() {
        return ultimos;
    }

    public void setUltimos(int[] ultimos) {
        this.ultimos = ultimos;
    }

    public String getAnulabilidad() {
        return anulabilidad;
    }

    public void setAnulabilidad(String anulabilidad) {
        this.anulabilidad = anulabilidad;
    }
    
    

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo (Tipo tipo){
        this.tipo = tipo;
        if(Tipo.OPERADOR == tipo){
            //Cuando el simbolo u hoja no es anulable, osea anulabilidad = F|N
            this.anulabilidad = "N";
        }else{
            //Cuando el simbolo u hoja  es anulable, osea anulabilidad = V|A
            this.anulabilidad = "A";
        }
    }
    
    
    
    enum Tipo{
        OPERADOR,SIMBOLO;
    }
}
