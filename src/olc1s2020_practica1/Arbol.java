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
    
    public Arbol(Nodo raiz,String nombre){
        this.raiz = raiz;
        this.nombre = nombre;
    }
    
    public Arbol(Nodo raiz){
        this.raiz = raiz;
        this.nombre = "";
    }
    
    public void insertar(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public void generarImagen_Tree(){
        Graficador graph = new Graficador();
        graph.crearDot(nombre, generarDotCode_Tree_2());
        graph.generarImagen(nombre+".dot",nombre+".jpg");
    }
    
    private String generarDotCode_Tree(){
        String cuerpo = "digraph{\n";
        cuerpo += "node[shape=oval]\n";
        cuerpo += generarTree(raiz);
        cuerpo += "}";
        return cuerpo;
    }
    
    
    
    private String generarTree(Nodo node){
        String cadena = "";
        if(node!=null){
            cadena += generarTree(node.izq);
            cadena += ("node"+reemplazar(node.hashCode())+"[label=\" "+node.getCaracterSimple()+"\"];\n");
            if(node.izq!=null){
                cadena += "node"+reemplazar(node.hashCode())+"->node"+reemplazar(node.izq.hashCode())+";\n";
            }
            if(node.der!=null){
                cadena += "node"+reemplazar(node.hashCode())+"->node"+reemplazar(node.der.hashCode())+";\n";
            }
            cadena += generarTree(node.der);
            
        }
        return cadena;
    }
    
    private String generarDotCode_Tree_2(){
        String cuerpo = "digraph{\n";
        cuerpo += "node[shape=record];\n";
        cuerpo += generarTree_2(raiz);
        cuerpo += "}";
        return cuerpo;
    }
    
    private String generarTree_2(Nodo node){
        String cadena = "";
        if(node!=null){
            cadena += generarTree_2(node.izq);
            cadena += ("node"+reemplazar(node.hashCode())+"[label=\""+node.primerosToString()+"|{"+node.getAnulabilidad()+"|"+node.getCaracterSimple()+"|"+node.getIdentificador()+"}|"+node.ultimosToString()+" \"];\n");
            if(node.izq!=null){
                cadena += "node"+reemplazar(node.hashCode())+"->node"+reemplazar(node.izq.hashCode())+";\n";
            }
            if(node.der!=null){
                cadena += "node"+reemplazar(node.hashCode())+"->node"+reemplazar(node.der.hashCode())+";\n";
            }
            cadena += generarTree_2(node.der);
            
        }
        return cadena;
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
