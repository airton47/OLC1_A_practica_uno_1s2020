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
        
        entrada += "¿";
        int x = entrada.length();
        String cadena = "";
        String aux = "";
        char[] car = entrada.toCharArray();
        for(int i = 0;i<=x-1;i++){
            switch(estado){
                case 0://car[i]>31&car[i]<126
                    if(Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        estado = 1;
                    }else if(car[i]>32&car[i]<126 & !Character.isLetterOrDigit(car[i])){//Se crea una token de identificador
                        cadena += car[i];
                        aux += car[i];
                        estado = 4;
                    }else if(isCaracterEspacio(car[i])){
                        estado = 0;                        
                    }else if(i == entrada.length()-1 & "¿".equals(Character.toString(car[i]))){
                        System.out.println("Se ha concluido con el analisis lexico!");
                    }else{
                        System.out.println("Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 1://Estado de aceptacion para un identificador
                    if(Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        estado = 1;
                    }else if("~".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        estado = 2;
                    }else if(isCaracterEspacio(car[i]) | !Character.isLetter(car[i])){
                        System.out.println("Se ha encontrado lexema de un identificador: "+cadena);
                        cadena = "";    //Aqui se acepta una cadena de un caracter
                        
                        estado = 0;
                    }else if(car[i]>32&car[i]<126 & !Character.isLetter(car[i])){
                        System.out.println("Se ha encontrado lexema de un identificador: "+cadena);
                        cadena = "";
                        cadena += car[i];
                        estado = 0;
                    }
                    else{
                        System.out.println("Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 2:
                    if(Character.isLetter(car[i])){
                        cadena += car[i];
                        estado = 3;
                    }
                    break;
                case 3: //Estado de aceptacio para conjunto de letras en intervalo
                    if(";".equals(Character.toString(car[i]))){//Se reconoce
                        System.out.println("Se ha encontra un lexema de conjuntos!");
                        cadena += car[i];
                        estado = 0;
                        cadena = "";
                    }else if(isCaracterEspacio(car[i])){
                        estado = 3;
                    }else{
                        System.out.println("Se ha encontrado un error lexico!");
                        estado = 0;
                        cadena = "";
                    }
                    break;
                case 4:
                    if("~".equals(Character.toString(car[i]))){
                       cadena += car[i];
                       estado = 5; 
                    }else if(isCaracterEspacio(car[i])){
                        if("{".equals(aux)){
                            System.out.println("Se ha encontrado lexema de llave: {");
                            cadena = "";
                            estado = 0;
                        }else if("}".equals(aux)){
                            System.out.println("Se ha encontrado lexema de llave: }");
                            cadena = "";
                            estado = 0;
                        }else if("%".equals(aux)){
                            System.out.println("Se ha encontrado lexema de: %");
                            cadena = "";
                            estado = 0;
                        }else if(":".equals(aux)){
                            System.out.println("Se ha encontrado lexema de dos puntos");
                            cadena = "";
                            estado = 0;
                        }else if("-".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : -");
                            cadena = "";
                            estado = 0;
                        }else if(">".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : >");
                            cadena = "";
                            estado = 0;
                        }else if(".".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : .");
                            cadena = "";
                            estado = 0;
                        }else if("+".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : +");
                            cadena = "";
                            estado = 0;
                        }else if("*".equals(aux)){
                        System.out.println("Se ha encontrado lexema de llave: *");
                            cadena = "";
                            estado = 0;
                        }else if("?".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : ?");
                            cadena = "";
                            estado = 0;
                        }else if("|".equals(aux)){
                            System.out.println("Se ha encontrado lexema de llave: |");
                            cadena = "";
                            estado = 0;
                        }else if("<".equals(aux)){
                            System.out.println("Se ha encontrado lexema de llave: <");
                            cadena = "";
                            estado = 0;
                        }else if("/".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : /");
                            cadena = "";
                            estado = 0;
                        }else if("\"".equals(aux)){
                            System.out.println("Se ha encontrado lexema de : \"");
                            cadena = "";
                            estado = 0;
                        }
                    }else{
                        System.out.println("Se ha encontrado un error lexico!");
                        estado = 0;
                        cadena = "";
                    }
                    break;
                case 5:
                    if(car[i]>32&car[i]<126 & !Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        estado = 6;
                    }else{
                        System.out.println("Se ha encontrado un error lexico!");
                        estado = 0;
                        cadena = "";
                    }
                    break;
                case 6:
                    if(";".equals(Character.toString(car[i]))){
                        //cadena += car[i];
                        System.out.println("Se ha encontrado un lexema de intervalo ASCII:"+cadena);
                        estado = 0;
                        cadena = "";
                    }else if(isCaracterEspacio(car[i])){
                        estado = 6;
                    }else{
                        System.out.println("Se ha encontrado un error lexico!");
                        estado = 0;
                        cadena = "";
                    }
                    break;
                case 7:
                    break;
                case 8:
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
    
    public void analisis(String entrada){
        int estado = 0;
        
        entrada += "¿";
        int x = entrada.length();
        String cadena = "";
        String aux = "";
        char[] car = entrada.toCharArray();
        for(int i = 0;i<=x-1;i++){
            switch(estado){
                case 0:
                    if(Character.isLetter(car[i])){
                        cadena += car[i];
                        estado = 1;
                    }else if(Character.isDigit(car[i])){
                        cadena +=car[i];
                        estado = 2;
                    }else if("~".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        System.out.println("Se ha encontrado lexema de simbolo:"+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(car[i]>32&car[i]<126 & !Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        if("/".equals(Character.toString(car[i]))){
                            estado = 3;
                        }else if("<".equals(Character.toString(car[i]))){
                            estado = 5;
                        }else if("\"".equals(Character.toString(car[i]))){
                            estado = 8;
                        }else if("{".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("}".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("-".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if(">".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("%".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if(",".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if(";".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if(".".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("*".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("+".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("|".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else if("?".equals(Character.toString(car[i]))){
                            System.out.println("Se ha encontrado lexema: "+cadena);
                            estado = 0;
                            cadena = "";
                        }else{
                            System.out.println("Se ha encontrado lexema de ASCII: "+cadena);
                            estado = 0;
                            cadena = "";
                        }
                        
                    }else if(isCaracterEspacio(car[i])){
                        estado = 0;
                    }else if(i == entrada.length()-1 & "¿".equals(Character.toString(car[i]))){
                      System.out.println("Se ha concluido con el analisis lexico!");
                    }else{
                        System.out.println("0-Se ha encontrado un error lexico!"+car[i]);
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 1:
                    if(Character.isLetterOrDigit(car[i])){
                        cadena += car[i];
                        estado = 1;
                    }else if(isCaracterEspacio(car[i])){
                        //Se reconoce un lexema de edentificador
                        System.out.println("Se ha encontrado lexema identificador: "+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(car[i]>32&car[i]<126 & !Character.isLetter(car[i]) | "~".equals(Character.toString(car[i]))){
                        //Se reconoce un lexema de edentificador
                        i = i-1;
                        System.out.println("Se ha encontrado lexema identificador: "+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(i == entrada.length()-1 & "¿".equals(Character.toString(car[i]))){
                        System.out.println("Se ha encontrado lexema identificador: "+cadena);
                        System.out.println("Se ha concluido con el analisis lexico!");
                    }else{
                        System.out.println("1-Se ha encontrado un error lexico!"+car[i]);
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 2:
                    if(Character.isDigit(car[i])){
                        cadena += car[i];
                        estado = 2;
                    }else if (isCaracterEspacio(car[i])){
                        System.out.println("Se ha encontrado lexema digito: "+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(car[i]>32&car[i]<126 & !Character.isLetter(car[i]) | "~".equals(Character.toString(car[i]))){
                        //Se reconoce un lexema de edentificador
                        i = i-1;
                        System.out.println("Se ha encontrado lexema identificador: "+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(i == entrada.length()-1 & "¿".equals(Character.toString(car[i]))){
                        System.out.println("Se ha encontrado lexema digito: "+cadena);
                        System.out.println("Se ha concluido con el analisis lexico!");
                    }else{
                        System.out.println("2-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 3:
                    if("/".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        estado = 4;
                    }else if(car[i]>32&car[i]<126){
                        i = i-1;
                        cadena = "";
                        System.out.println("Se ha encontrado lexema: /");
                        estado = 0;
                    }else{
                        System.out.println("3-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 4:
                    if(!"\n".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        estado = 4;
                    }else{
                        System.out.println("Se ha encontrado lexema de comentario una linea:"+cadena);
                        cadena = "";
                        estado = 0;
                    }
                    break;
                case 5:
                    if("!".equals(Character.toString(car[i]))){
                        estado = 6;
                    }else if(car[i]>32&car[i]<126){
                        i = i-1;
                        cadena = "";
                        System.out.println("Se ha encontrado lexema: <");
                        estado = 0;
                    }else{
                        System.out.println("5-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 6:
                    if(!"!".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        estado = 6;
                    }else if("!".equals(Character.toString(car[i]))){
                        estado = 7;
                    }else if(car[i]>32&car[i]<126){
                        i = i-1;
                        cadena = "";
                        System.out.println("Se ha encontrado lexema: <");
                        estado = 0;
                    }else{
                        System.out.println("6-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 7:
                    if(">".equals(Character.toString(car[i]))){
                        System.out.println("Se ha encontrado lexema de comentario multilinea"+cadena);
                        cadena = "";
                        estado = 0;
                    }else if(car[i]>32&car[i]<126){
                        i = i-1;
                        cadena = "";
                        System.out.println("Se ha encontrado lexema: <");
                        estado = 0;
                    }else{
                        System.out.println("7-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
                case 8:
                    if(!"\"".equals(Character.toString(car[i]))){
                        cadena += car[i];
                        estado = 8;
                    }else if("\"".equals(Character.toString(car[i]))){
                        System.out.println("Se ha encontrado lexema de cadena");
                        cadena = "";
                        estado = 0;
                    }else if(car[i]>32&car[i]<126){
                        i = i-1;
                        cadena = "";
                        System.out.println("Se ha encontrado lexema: <");
                        estado = 0;
                    }else{
                        System.out.println("8-Se ha encontrado un error lexico!");
                        cadena = "";
                        estado  = 0;
                    }
                    break;
            }
        }
    }
        
    public boolean isCaracterEspacio(char c){
        boolean val = false;
        if(Character.isSpaceChar(c) | "\n".equals(Character.toString(c)) | "\t".equals(Character.toString(c))){
            val = true;
        }
        return val;
    }
    
}


