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
public class Arbol {
    
    public Nodo raiz;
    private String nombre;
    public Arbol(String nombre){
        this.raiz = new Nodo();
        this.nombre = nombre;
    }
    
    public Arbol(Nodo raiz){
        this.raiz = raiz;
        this.nombre = "";
    }
    
    public void insertar(){
        
    }
    
    public String generaDotCode_Tree(){
        String cuerpo = "";
        cuerpo += generarTree(raiz);
        return cuerpo;
    }
    
    private String generarTree(Nodo raiz){
        String cuerpo = "";
        return cuerpo;
    }
    
    public String generarDotCode_NextTable(){
        String cuerpo = "";
        return cuerpo;
    }
    
    public String generarDotCode_TransitionTable(){
        String cuerpo = "";
        return cuerpo;
    }
    
    public void inOrder(){
        inOrder(raiz);
    }
    
    private void inOrder(Nodo node){
        if(node!=null){
            inOrder(node.izq);
            System.out.println("|id:"+node.getCaracter()+"|");
            inOrder(node.der);
        }
    }
    
    //Para usar este metodo hay que llamarlo asi: reemplazar(node.hashCode())
    public String reemplazar(int posmemoria){
		String cad = Integer.toString(posmemoria);
        return cad.replace("-", "_");
    }
    
}
