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
    
    public String getCaracterSimple() {
        String lexem = caracter.replace("\"", "");
        if(lexem.equals("|")){
            lexem = "OR";
        }else if(lexem.equals("<")){
            lexem = "menor_que";
        }else if(lexem.equals(">")){
            lexem = "mayor_que";
        }else if(lexem.equals(" ")){
            lexem = "espacio_en_blanco";
        }
        return lexem;
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
    
    
    public String toString(){
        String texto = "";
        texto += "Node_id:"+identificador+" | No_char:"+caracter+" | Node_null:"+anulabilidad;
        return texto;
    }
    
    public String primerosToString(){
        String prim = "";
        if(!primeros.isEmpty() ){
            for(int x:primeros){
                prim += x+" "; 
            }
        }
        return prim;
    }
    
    public String ultimosToString(){
        String ult = "";
        if(!ultimos.isEmpty()){
            for(int x :ultimos){
                ult += x + " ";
            }
        }
        return ult;
    }
    
    public void addPrimerosFrom(Nodo nodo){
        for(Integer i : nodo.getPrimeros()){
            primeros.add(i);
        }
    }
    
    public void addUltimosFrom(Nodo nodo){
        for(Integer i: nodo.getUltimos()){
            ultimos.add(i);
        }
    }
    
    public void refreshPrimerosUltimos(){
        //this.tipo = tipo;
        String aux = tipo.toUpperCase();
        if(aux.equals("PUNTO")){//Para '.'
            anulable = izq.isAnulable() & der.isAnulable();
            if(anulable = true){
                anulabilidad = "A";
            }else{
                anulabilidad = "N";
            }
            if(izq.isAnulable()){
                for(Integer i : izq.getPrimeros()){
                    primeros.add(i);
                }
                for(Integer i : der.getPrimeros()){
                    primeros.add(i);
                }
            }else{
                for(Integer i : izq.getPrimeros()){
                    primeros.add(i);
                }
            }
            //para ultimos del nodo
            if(der.isAnulable()){
                for(Integer i : izq.getUltimos()){
                    ultimos.add(i);
                }
                for(Integer i : der.getUltimos()){
                    ultimos.add(i);
                }
            }else{
                for(Integer i : izq.getUltimos()){
                    ultimos.add(i);
                }
            }
            
        }else if(aux.equals("OR")){//Para '|'
            anulable = izq.isAnulable() | der.isAnulable();
            if(anulable = true){
                anulabilidad = "A";
            }else{
                anulabilidad = "N";
            }
            for(Integer i: izq.getPrimeros()){
                primeros.add(i);
            }
            for(Integer i: der.getPrimeros()){
                primeros.add(i);
            }
            for(Integer i: izq.getUltimos()){
                ultimos.add(i);
            }
            for(Integer i: der.getUltimos()){
                ultimos.add(i);
            }
        }else if(aux.equals("ASTERISCO")){//Para '*'
            if(this.izq!=null){
                primeros = this.izq.getPrimeros();
                ultimos = this.izq.getUltimos();
            }
            if(this.der!=null){
                primeros = this.der.getPrimeros();
                ultimos = this.der.getUltimos();
            }
            
        }else if(aux.equals("MAS")){//Para '+'
            if(izq!=null){
                anulable = izq.isAnulable();
                if(anulable==true){
                    anulabilidad = "A";                    
                }else{
                    anulabilidad = "N";
                }
                primeros = izq.getPrimeros();
                ultimos = izq.getUltimos();
                
                
            }
            
            if(der!=null){
                anulable = der.isAnulable();
                if(anulable==true){
                    anulabilidad = "A";
                    
                }else{
                    anulabilidad = "N";
                }
                primeros = der.getPrimeros();
                ultimos = der.getUltimos();
            }
            
        }else if (aux.equals("INTERROGACION")){//Para '?'
            anulable = true;
            if(izq!=null){
                primeros = izq.getPrimeros();
                ultimos = izq.getUltimos();
                
            }
            if(der!=null){
                primeros = der.getPrimeros();
                ultimos = der.getUltimos();
            }
            
        }else{//Para cadenas y conjuntos
            
                    
        }
    }
    
    public void refreshUltimos(){
        
    }
}
