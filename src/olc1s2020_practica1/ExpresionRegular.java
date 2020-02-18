/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author aiyel
 */
public class ExpresionRegular {
    private Arbol arbol;
    private String nombre;
    private LinkedList<String> elementos;
    
    public ExpresionRegular(String nombre,LinkedList<String> elementos){
        this.elementos = elementos;
        this.nombre = nombre;
        this.arbol = new Arbol(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<String> getElementos() {
        return elementos;
    }

    public void setElementos(LinkedList<String> elementos) {
        this.elementos = elementos;
    }
    
    public String toString(){
        String texto = "Expresion Regular-> "+this.nombre;
        if(!this.elementos.isEmpty()){
            texto += " elementos: ";
            for(String i:elementos){
                texto += i;
            }
        }
        return texto;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }
    
    public void armarArbol(LinkedList<Conjunto> conjuntos){
        if(!elementos.isEmpty()){
            Stack<Nodo> pila = new Stack();
            LinkedList<String> aux = new LinkedList<>();
            LinkedList<Nodo> nodos = new LinkedList<>();
            LinkedList<Nodo> op_or = new LinkedList<>();
            LinkedList<Nodo> op_unaria = new LinkedList<>();
            LinkedList<Nodo> op_cocatenacion = new LinkedList<>();
            //primero vamos a quitar los simbolos de llaves {conj} para los conjuntos
            aux = elementos;
            //Esto sera para remover los corchetes de los conjuntos
            aux.removeIf((String stg)->(stg.equals("{")|stg.equals("}")));
                        
            System.out.print("\n");
            for(String st:aux){
                System.out.print(st+" ");
            }
            System.out.print(" Fin");
            //ahora se hace el primer recorrido para convertir a los elementos
            //de la lista'elementos' en nodos para nuestro arbol
            int identificador = 1;
            for(String ss : aux){
                
                if(ss.length()==1 & (ss.charAt(0)=='+'|ss.charAt(0)=='*')|ss.charAt(0)=='|'|ss.charAt(0)=='?'|ss.charAt(0)=='.'){
                    switch (ss.charAt(0)) {
                        case '+':
                            nodos.add(new Nodo(ss,0,"MAS"));
                            break;
                        case '*':
                            nodos.add(new Nodo(ss,0,"ASTERISCO"));
                            break;
                        case '|':
                            nodos.add(new Nodo(ss,0,"OR"));
                            break;
                        case '.':
                            nodos.add(new Nodo(ss,0,"PUNTO"));
                            break;
                        case '?':
                            nodos.add(new Nodo(ss,0,"INTERROGACION"));
                            break;                            
                        default:
                            break;
                    }
                    
                }else{
                    LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    primeros.add(identificador);
                    primeros.add(identificador);
                    if(esConjunto(ss,conjuntos)){                        
                        nodos.add(new Nodo(ss,identificador,"CADENA",primeros,ultimos));
                    }else{                        
                        nodos.add(new Nodo(ss,identificador,"CONJUNTO",primeros,ultimos));
                    }
                }
                identificador ++;
            }
            /*La lista de nodos ahora tiene todos los elementos encesarios
            Ahora hemos de hacer el segundo recorrido para identificar que nodos
            tienen operaciones binarias del tipo or y hacer una nueva lista
            algo importante a notar aqui es que el recorrido sera de izquierda a derecha
            en el LinkedList de op_or
            */
            int n = nodos.size()-1;
            //nodos = voltearLista(nodos);//volteamos el orden de la matria
            Nodo nodo_aux;  //Nodo auxiliar para crear al nodo que si tendra hijos
            for(int a = n;a>=0;a--){
                if(!nodos.get(a).getTipo().equals("OR")){ //aqui determinamos si sera un nodo normal                   
                    //lo agregamos a pila de nodos
                    pila.push(nodos.get(a));
                }else{//Aqui definimos al nodo tipo OR y le asignamos como hijos a los 2 siguientes
                    nodo_aux = nodos.get(a);
                    Nodo izq = pila.pop();
                    Nodo der = pila.pop();
                    
                    nodo_aux.der = der;
                    nodo_aux.izq =izq;
                    LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    primeros.add(der.getIdentificador());
                    primeros.add(izq.getIdentificador());
                    ultimos.add(der.getIdentificador());
                    ultimos.add(izq.getIdentificador());
                    
                    nodo_aux.setPrimeros(primeros);
                    nodo_aux.setUltimos(ultimos);                   
                    
                    pila.push(nodo_aux);
                }
            }
            /*Ahora hemos de hacer el segundo recorrido para determinar que nodos
            son del tipo +,*,? para hacer una nueva lista de nodos que agregara
            nodos con las operaciones unarias anteriores, al igual que antes
            el recorrido sera de izquierda a derecha
            */
            System.out.println(nombre);
            for(Nodo nn :pila){
                
                System.out.println(nn.toString());
            }
            while(!pila.isEmpty()){
                op_or.add(pila.pop());
            }
            
            nodo_aux = null;
            int num_op = op_or.size()-1;
            for(int a = num_op;a>=0;a--){
                
                if(op_or.get(a).getTipo().equals("MAS")){
                    System.out.println("Binaria");
                    nodo_aux = op_or.get(a);
                    
                    if(pila.lastElement().getTipo().equals("PUNTO")){
                        Nodo aux_2 = pila.pop();
                        Nodo izq_aux = pila.pop();
                        Nodo der_aux = pila.pop();
                        aux_2.izq = izq_aux;
                        aux_2.der = der_aux;
                        nodo_aux.der = aux_2;
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    nodo_aux.setPrimeros(primeros);
                    nodo_aux.setUltimos(ultimos);                    
                    pila.push(nodo_aux);
                    }
                    
                    
                }else if(op_or.get(a).getTipo().equals("ASTERISCO")){
                    System.out.println("Binaria");
                    nodo_aux = op_or.get(a);
                    
                    if(pila.lastElement().getTipo().equals("PUNTO")){
                        Nodo aux_2 = pila.pop();
                        Nodo izq_aux = pila.pop();
                        Nodo der_aux = pila.pop();
                        aux_2.izq = izq_aux;
                        aux_2.der = der_aux;
                        nodo_aux.der = aux_2;
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    nodo_aux.setPrimeros(primeros);
                    nodo_aux.setUltimos(ultimos);                    
                    pila.push(nodo_aux);
                    }
                    
                    
                }else if(op_or.get(a).getTipo().equals("INTERROGACION")){
                    System.out.println("Binaria");
                    nodo_aux = op_or.get(a);
                    
                    if(pila.lastElement().getTipo().equals("PUNTO")){
                        Nodo aux_2 = pila.pop();
                        Nodo izq_aux = pila.pop();
                        Nodo der_aux = pila.pop();
                        aux_2.izq = izq_aux;
                        aux_2.der = der_aux;
                        nodo_aux.der = aux_2;
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    nodo_aux.setPrimeros(primeros);
                    nodo_aux.setUltimos(ultimos);                    
                    pila.push(nodo_aux);
                    }
                    
                    
                }else{
                    pila.push(op_or.get(a));
                    System.out.println("No binaria");
                }
                                
            }
            System.out.println("\n");
            for(Nodo np:pila){
                System.out.println("op2: "+np.toString());
            }
            
            while(!pila.isEmpty()){
                op_unaria.add(pila.pop());
            }
            int num_un = op_unaria.size()-1;
            for(int a = num_un;a>=0;a--){
                if(op_unaria.get(a).getTipo().equals("PUNTO")){
                    nodo_aux = op_unaria.get(a);
                    if(!pila.isEmpty()){
                        Nodo izq = pila.pop();
                        nodo_aux.izq = izq;
                    }
                    if(!pila.isEmpty()){
                        Nodo der = pila.pop();
                        nodo_aux.der = der;
                    }
                    
                    pila.push(nodo_aux);
                }else{
                    pila.push(op_unaria.get(a));
                }
            }
            System.out.println("\n");
            for(Nodo np:pila){
                System.out.println("op3: "+np.toString());
            }
            
            Nodo raiz = new Nodo(".",0,"N");
            Nodo aceptacion = new Nodo("#",identificador+1,"N");
            Arbol tree = new Arbol(raiz);
            tree.setNombre(nombre);
            tree.raiz.izq = pila.pop();
            tree.raiz.der = aceptacion;
            tree.inOrder();
            tree.generarImagen_Tree();
            System.out.println("Fin ");
            
        }
    }
    
    private LinkedList<Nodo> voltearLista(LinkedList<Nodo> nodos){
        LinkedList<Nodo> nodos_aux = new LinkedList<>();
        int num = nodos.size()-1;
        if(!nodos.isEmpty()){
            for(int a = num;a>=0;a--){
                nodos_aux.add(nodos.get(a));
            }
        }
        return nodos_aux;
    }
    
    private String verificarSiEsConjunto(String cadena,LinkedList<Conjunto> conjuntos){
        String result = "";
        for(Conjunto c : conjuntos){
            if(c.getNombre().contains(cadena)){
                result = cadena.replace("{","");
                result = result.replace("}", "");
            }else{
                result = cadena;
            }
        }
        return result;
    }
    
    private boolean esConjunto(String cadena,LinkedList<Conjunto> conjuntos){
        boolean result = false;
        for(Conjunto c : conjuntos){
            if(c.getNombre().equals(cadena)){
                result = true;
            }
        }
        return result;
    }
    
}
