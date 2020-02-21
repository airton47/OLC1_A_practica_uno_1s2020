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
public class Token {
    private String lexema;      //Lexema o cadena propio
    private Type type;      //Tipo de token
    private String nombre;  //Nombre del token si lo tiene
    private String tipo;    //Tipo del token como una adena String
    
    public Token(Type type,String lexema,String nombre){
        this.type = type;
        this.tipo = type.toString();
        this.lexema = lexema;
        this.nombre = nombre;
    }
    
    public Token(Type type,String lexema){
        this.type = type;
        this.tipo = type.toString();
        this.lexema = lexema;
        this.nombre = "";
    }

    public String getLexema() {
        return lexema;
    }
    
    public String getLexemaSimple() {
        return lexema.replace("\"","");
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    
}


