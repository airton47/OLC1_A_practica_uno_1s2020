/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aiyel
 */
public class Graficador {
    
    public String reemplazar(int posmemoria){
		String cad = Integer.toString(posmemoria);
        return cad.replace("-", "_");
    }
    
    public void crearDot(String nombre,String cuerpo){
        FileWriter wr = null;
        PrintWriter pw = null;
        try {
        wr = new FileWriter(nombre+".dot");
        pw = new PrintWriter(wr);
        pw.println(cuerpo);
        //pw.println(generarDot(primero));
        //pw.println("}");
        pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Graficador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    wr.close();
                } catch (IOException ex) {
                    Logger.getLogger(Graficador.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public void generarImagen(String archivo,String imagen){
    		String doPath = "\"C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe\"";
            String cmd = doPath + " -Tjpg "+archivo+" -o "+imagen;
            try {
                Runtime.getRuntime().exec(cmd);
            } catch (IOException ex) {
                Logger.getLogger(Graficador.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
