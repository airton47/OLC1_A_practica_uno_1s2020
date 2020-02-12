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
    private String tipo;
    
    public Nodo(){
        this.izq = null;
        this.der = null;
    }
    
    public Nodo(String caracter,int identificador,String tipo,int[] primeros, int[] ultimos){
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo (String tipo){
        this.tipo = tipo;
        String aux = tipo.toUpperCase();
        if(aux.equals("CONCATENACION")){
            this.anulabilidad = "N";
        }else if(aux.equals("UNION")){
            this.anulabilidad = "N";
        }else if(aux.equals("CERRADURA_KLEEN")){
            this.anulabilidad = "A";
        }else if(aux.equals("CERRADURA_POSITIVA")){
            this.anulabilidad = "N";
        }else if (aux.equals("CERRADURA_QUESTION")){
            this.anulabilidad = "A";
        }else{
            this.anulabilidad = "N";
        }
    }
    
    
    
    
}
