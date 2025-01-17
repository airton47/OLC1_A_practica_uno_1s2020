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
    private LinkedList<Siguiente> siguientes;
    
    public ExpresionRegular(String nombre,LinkedList<String> elementos){
        this.elementos = elementos;
        this.nombre = nombre;
        this.arbol = new Arbol(nombre);
        this.siguientes = new LinkedList<>();
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

    public LinkedList<Siguiente> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(LinkedList<Siguiente> siguientes) {
        this.siguientes = siguientes;
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
                    ultimos.add(identificador);
                    if(esConjunto(ss,conjuntos)){    
                        siguientes.add(new Siguiente(identificador));
                        nodos.add(new Nodo(ss,identificador,"CADENA",primeros,ultimos));
                        identificador ++;
                    }else{                        
                        siguientes.add(new Siguiente(identificador));
                        nodos.add(new Nodo(ss,identificador,"CONJUNTO",primeros,ultimos));
                        identificador ++;
                    }
                    
                }
                //identificador ++; codigo original no el de arriba
            }
            System.out.println("tamanio: "+siguientes.size()+nombre);
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
                    //lo agregamos a pila de nodos para despues talvez usarlo como hijo de otro nodo
                    pila.push(nodos.get(a));
                }else{//Aqui definimos al nodo tipo OR y le asignamos como hijos a los 2 primeros en la
                    //pila de nodos
                    nodo_aux = nodos.get(a);//nodo que sera el nuevo padre
                    Nodo izq = pila.pop();//primer nodo en pila e hijo izquierdo del nuevo padre
                    Nodo der = pila.pop();//segundo nodo en la pila e hijo del nuevo padre
                    
                    nodo_aux.der = der;
                    nodo_aux.izq =izq;
                    //LinkedList<Integer> primeros = new LinkedList<>();
                    //LinkedList<Integer> ultimos = new LinkedList<>();
                    
                    //primeros.add(izq.getIdentificador());
                    //primeros.add(der.getIdentificador());
                    //ultimos.add(izq.getIdentificador());
                    //ultimos.add(der.getIdentificador());
                    
                    //nodo_aux.setPrimeros(primeros);
                    //nodo_aux.setUltimos(ultimos);                   
                    nodo_aux.refreshPrimerosUltimos();
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
            //Aqui se vacia la pila y se meten todos los nodos en otra lista
            while(!pila.isEmpty()){
                op_or.add(pila.pop());
            }
            /*
            Este es el segundo paso para finalmento tomar las operacion unarias de + ? *
            */
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
                        aux_2.refreshPrimerosUltimos();
                        for(Integer i:aux_2.izq.getUltimos()){
                            for(Integer ii:aux_2.der.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                        nodo_aux.der = aux_2;
                        nodo_aux.refreshPrimerosUltimos();
                        for(Integer i:nodo_aux.getUltimos()){
                            for(Integer ii:nodo_aux.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    //nodo_aux.setPrimeros(primeros);
                    //nodo_aux.setUltimos(ultimos);  
                    nodo_aux.refreshPrimerosUltimos();
                    for(Integer i:nodo_aux.getUltimos()){
                        for(Integer ii:nodo_aux.getPrimeros()){
                            addSiguiente((int)i,(int)ii);
                        }
                    }
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
                        aux_2.refreshPrimerosUltimos();
                        for(Integer i:aux_2.izq.getUltimos()){
                            for(Integer ii:aux_2.der.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                        nodo_aux.der = aux_2;
                        nodo_aux.refreshPrimerosUltimos();
                        for(Integer i:nodo_aux.getUltimos()){
                            for(Integer ii:nodo_aux.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    //nodo_aux.setPrimeros(primeros);
                    //nodo_aux.setUltimos(ultimos);
                    nodo_aux.refreshPrimerosUltimos();
                    for(Integer i:nodo_aux.getUltimos()){
                        for(Integer ii:nodo_aux.getPrimeros()){
                            addSiguiente((int)i,(int)ii);
                        }
                    }
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
                        aux_2.refreshPrimerosUltimos();
                        for(Integer i:aux_2.izq.getUltimos()){
                            for(Integer ii:aux_2.der.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                        nodo_aux.der = aux_2;
                        nodo_aux.refreshPrimerosUltimos();
                        pila.push(nodo_aux);
                    }else{
                        LinkedList<Integer> primeros = new LinkedList<>();
                    LinkedList<Integer> ultimos = new LinkedList<>();
                    nodo_aux.der = pila.pop();                    
                    //nodo_aux.setPrimeros(primeros);
                    //nodo_aux.setUltimos(ultimos);    
                    nodo_aux.refreshPrimerosUltimos();
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
                    nodo_aux.refreshPrimerosUltimos();
                    for(Integer i:nodo_aux.izq.getUltimos()){
                            for(Integer ii:nodo_aux.der.getPrimeros()){
                                addSiguiente((int)i,(int)ii);
                            }
                        }
                    pila.push(nodo_aux);
                }else{
                    //op_unaria.get(a).refreshPrimerosUltimos();
                    pila.push(op_unaria.get(a));
                }
            }
            System.out.println("\n");
            for(Nodo np:pila){
                System.out.println("op3: "+np.toString());
            }
            
            Nodo raiz = new Nodo(".",0,"PUNTO");
            Nodo aceptacion = new Nodo("#",identificador,"N");
            siguientes.add(new Siguiente(identificador));
            Integer last = identificador;
            aceptacion.getPrimeros().add(last);
            aceptacion.getUltimos().add(last);
            Arbol tree = new Arbol(raiz,nombre);
            //tree.setNombre(nombre);
            tree.raiz.izq = pila.pop();
            tree.raiz.der = aceptacion;
            raiz.refreshPrimerosUltimos();
            for(Integer i:raiz.izq.getUltimos()){
                for(Integer ii:raiz.der.getPrimeros()){
                    addSiguiente((int)i,(int)ii);
                }
            }
            tree.inOrder();
            tree.generarImagen_Tree();
            crearTablaSiguientes();
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
    
    public void addSiguiente(int id,int nodo){
        int in_id = id;
        int id_nodo = nodo;
        Siguiente aux = null;
        for(Siguiente sig:siguientes){
            if(sig.getId_nodo()==in_id){
                aux = sig;
            }
        }
        boolean flag = false;
        if(aux!=null){
            for(Integer i: aux.getSiguientes()){
                if(i==id_nodo){
                    flag = true;
                }
            }
            if(flag==false){
                aux.getSiguientes().add(id_nodo);
            }
        }
    }
    
    public String getSiguientesTexto(){
        String a = nombre+"\n";
        
        for(Siguiente s:siguientes){
            a += s.siguientesToString()+"\n";
        }
        a += "\n";
        a += "\n";
        a += "\n";
        return a;
    }
    
    public void printSiguientes(){
        String a = nombre+"\n";
        for(Siguiente s:siguientes){
            a += s.siguientesToString()+"\n";
        }
        System.out.println(a);
    }
    
    public void generarImagen_Siguientes(){
        String cuerpo = createDotCode();
        Graficador graph = new Graficador();
        graph.crearDot(nombre+"Siguientes", cuerpo);
        graph.generarImagen(nombre+"Siguientes.dot", nombre+"Siguientes.jpg");
    }
    
    public void crearTablaSiguientes(){
        
    }
    
    private String createDotCode(){
        String cuerpo = "digraph{\n";
        cuerpo += "node[shape=record];\n";
        
        String columnas1 = "";
        for(Siguiente s:siguientes){
            columnas1 += s.getId_nodo()+"|";
        }
        
        String columnas2 = "";
        for(Siguiente s:siguientes){
            columnas2 += s.getSiguintesListString()+"|";
        }
        cuerpo += "nodeSiguientes[label =\"{"+columnas1+"}|{"+columnas2+"}\"]";
        
        
        cuerpo += "\n}";
        return cuerpo;
    }
    
    
    
}
