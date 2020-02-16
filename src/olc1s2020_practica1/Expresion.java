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
public class Expresion {
    private String entrada;
    private String expresion_regular;
    
    public Expresion(String entrada, String expresion_regular){
        this.entrada = entrada;
        this.expresion_regular = expresion_regular;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getExpresion_regular() {
        return expresion_regular;
    }

    public void setExpresion_regular(String expresion_regular) {
        this.expresion_regular = expresion_regular;
    }
    
    public String toString(){
        String texto = this.expresion_regular+": , entrada: ";
        texto += this.entrada;
        return texto;
    }
    
}
