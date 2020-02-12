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
public class Analizador {
    
    public Analizador(){
        
    }
    
    public void analizar (String entrada){
        int estado = 0;
        int x = entrada.length();
        String cadena = "";
        char[] car = entrada.toCharArray();
        for(int i = 0;i<x;i++){
            switch(estado){
                case 0:
                    if(car[i]>31&car[i]<126){
                        cadena += car[i];
                        estado = 1;
                    }else if(isCaracterEspacio(car[i])){//Se crea una token de identificador
                        cadena += car[i];
                        estado = 0;
                    }else{
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 1:
                    if(Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        estado = 1;
                    }else if(isCaracterEspacio(car[i])){
                        cadena = "";
                        estado = 0;
                    }
                    break;
                case 2:
                    break;
            }
        }
        
        /*
            switch(estado){
            case 0:
                
                
                if(Character.isLetterOrDigit(car[i])){
                    cadena += car[i];
                    estado = 2; //Se dirige al estado en donde se analiza texto
                }else if(car[i]>31&car[i]<126 & !Character.isLetterOrDigit(car[i])){
                    cadena += car[i];
                    estado = 1; //Se direge al estado que analiza simbolos especiales
                }else if(Character.isSpaceChar(car[i]) | "\n".equals(Character.toString(car[i]))){
                    cadena = "";
                    estado = 0;
                }
                else{
                    estado = 0;
                    System.out.println("Se he encontrado en error lexico");
                }
                
                break;
            case 1:
                if(car[i]>31&car[i]<126){
                    estado = 2;
                }else if(Character.isSpaceChar(car[i]) | "\n".equals(Character.toString(car[i]))){
                    estado = 1;
                }else{
                    System.out.println("Se ha encontrado un error lexico!");
                }
                break;
            case 2:
                if("{".equals(Character.toString(car[i]))){
                    estado = 2;
                }else if("}".equals(Character.toString(car[i]))){
                    estado = 2;
                }else if(":".equals(Character.toString(car[i]))){
                    estado = 2;
                }else if ("-".equals(Character.toString(car[i]))){
                    estado = 2;
                }else if ("~".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if(";".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if(">".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if("*".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if("+".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if(".".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if("|".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if("%".equals(Character.toString(car[i]))){
                    estado = 0;
                }else if(!Character.isLetterOrDigit(car[i])){
                    
                }else{
                    System.out.println("Se he encontrado en error lexico");
                }
                break;
            case 3: 
                break;
        }
            */
    }
        
    public boolean isCaracterEspacio(char c){
        boolean val = false;
        if(Character.isSpaceChar(c) | "\n".equals(Character.toString(c))){
            val = true;
        }
        return val;
    }
    
}


