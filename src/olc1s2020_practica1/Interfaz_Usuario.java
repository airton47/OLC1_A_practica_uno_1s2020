/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package olc1s2020_practica1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author aiyel
 */


public class Interfaz_Usuario extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz_Usuairo
     */
    JFileChooser chooserFile = new JFileChooser();
    File document;
    FileInputStream inFile;
    FileOutputStream outFile;
    private LinkedList<ExpresionRegular> list_exp_reg;
    private LinkedList<Conjunto> conjuntos;
    private LinkedList<Expresion> lista_expresiones;
    private String contenido;
    private LinkedList<String> comandos;
    public String body;
    //private LinkedList<Entrada> entradas;
    
    public Interfaz_Usuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.contenido = "";
        this.list_exp_reg = new LinkedList<>();
        this.conjuntos = new LinkedList<>();
        this.comandos = new LinkedList<>();
        this.lista_expresiones = new LinkedList<>();
        body = "";
    }
    
    public String abrirArchivo(File document){
        String cuerpo = "";
        try {
                inFile = new FileInputStream(document);
                int ascci;
                while((ascci=inFile.read())!=-1){
                    char caracter = (char)ascci;
                    cuerpo += caracter;
                }
            } catch (IOException ex) {
                Logger.getLogger(Interfaz_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        return cuerpo;
    }
    
    public void guardarComo(){
        
    }
    
    
   
    
    private void analizarLista(LinkedList<Token> lista){
        
        try {
            lista.remove(0);
            lista.remove(lista.size()-1);
        } catch (Exception e) {
            System.err.println("Se intento remover elementos de lista en indices inexistentes");
        }
        //Ahora se remueven todos los tokens de comentarios que no son utiles
        //Junto con los tokens de erroes  lexicos
        System.out.println("Numero inicial en la lista: "+lista.size());
        if(lista.removeIf((Token i)->"COMENTARIO_ML".equals(i.getTipo())) | lista.removeIf((Token i)->"COMENTARIO_SL".equals(i.getTipo())) | lista.removeIf((Token i)->"ERROR".equals(i.getTipo()))){
            System.out.println("Elementos fueron removidos de la lista");
        }else{
            System.out.println("Ningun elemento fue removido de la lista");
        }
        System.out.println("Numero final en la lista: "+lista.size());
        //Ahora se analiza la lista para obtener los datos que nos interesan: conjuntos
        //expresiones regulares y exprexiones a analizar
        //Ahora se dividira en dos listas, la primera tendra todos los tokens de definciones
        //de conjuntos y expresiones regulares y la otra las expresiones a evaluar
        //para ello se encontrara la posicion en donde se encuentran los tokens %%%%
        LinkedList<Token> definiciones = new LinkedList<>();
        LinkedList<Token> expresiones = new LinkedList<>();
        /*Aqui tenemos que encontrar el indice en donde se encuentran los primeros
        cuatro %%%%
        */
        int contador = getIndex(lista);
        System.out.println("el indice es:" + contador);
        
        /*Aqui incluiremos todos los token que estan en 'lista' antes de encontrar
        el simbolo de ASCCI: % cuatro veces ya que todos esos token son de definiciones
        
        (lista.get(idx+1).getLexema().equals("%"))&(lista.get(idx+2).getLexema().equals("%"))&(lista.get(idx+3).getLexema().equals("%")) 
        
        **/
        for(int a = 0;a<contador;a++){
            definiciones.add(lista.get(a));
        }
        /*Esta parte es para finalmente extraer las definiciones de conjuntos
        y expresiones regulares en la entrada
        */
        if(!definiciones.isEmpty()){
            boolean flat = false;
            String nombre = "";
            String tipo = "";
            String nombre_exp;
            LinkedList<String> elementos_expreg;
            for(Token tt: definiciones){
                    int ind = definiciones.indexOf(tt);
                    //Incicio-A: intruccion para identificar un conjunto en la lista
                    //de tokens de definiciones
                    if(tt.getTipo().equals("KW_CONJ")){
                        if(definiciones.get(ind+2).getTipo().equals("IDENTIFICADOR")){
                            nombre = definiciones.get(ind+2).getLexema();
                            LinkedList<String> elementos = new LinkedList<>();
                            if(definiciones.get(ind+6).getTipo().equals("TILDE_BARRA")){
                                tipo = "INTERVALO";
                                String inicio = definiciones.get(ind+5).getLexema();
                                String fin = definiciones.get(ind+7).getLexema();
                                elementos.add(inicio);
                                elementos.add(fin);
                                conjuntos.add(new Conjunto(nombre,tipo,elementos));
                            }else if(definiciones.get(ind+6).getTipo().equals("ASCII") & definiciones.get(ind+6).getLexema().equals(",")){
                                tipo = "LISTA";
                                boolean flag1 = true;
                                int cont = ind+5;
                                while(flag1){
                                    if(Character.isLetterOrDigit(definiciones.get(cont).getLexema().charAt(0))){
                                        elementos.add((definiciones.get(cont).getLexema()));
                                    }else if((definiciones.get(cont).getLexema().equals(";"))){
                                        conjuntos.add(new Conjunto(nombre,tipo,elementos));
                                        flag1=false;
                                    }
                                    cont++;
                                }
                                /*
                                
                                */
                            }
                    }/*Inicio-b: intrucciones para identificar a las definiciones de 
                        expresiones regulares
                    */
                }else if(!tt.getTipo().equals("KW_CONJ") & tt.getTipo().equals("IDENTIFICADOR") & ind<definiciones.size()/*&(definiciones.get(ind+1).getLexema().equals("-"))*/){
                    //System.out.println("Entro en seccion de exp_regulares");
                    if((definiciones.get(ind+1).getLexema().equals("-"))&!(definiciones.get(ind-1).getLexema().equals(":"))){
                        elementos_expreg = new LinkedList();
                        nombre_exp = tt.getLexema();
                        int cont1 = ind+3;
                        elementos_expreg = new LinkedList<>();
                        while(!definiciones.get(cont1).getLexema().equals(";")){
                            if(definiciones.get(cont1).getTipo().equals("ASCII") | definiciones.get(cont1).getTipo().equals("CADENA") | definiciones.get(cont1).getTipo().equals("IDENTIFICADOR")){
                                
                                elementos_expreg.add(definiciones.get(cont1).getLexema());
                            }
                            cont1++;
                        }
                        list_exp_reg.add(new ExpresionRegular(nombre_exp,elementos_expreg));
                    }
                    /*
                    elementos_expreg = new LinkedList();
                    nombre_exp = tt.getLexema();
                    int cont1 = ind+1;
                    while(!definiciones.get(cont1).getLexema().equals(";")){
                        if(definiciones.get(cont1).getTipo().equals("ASCII") | definiciones.get(cont1).getTipo().equals("CADENA")){
                            elementos_expreg = new LinkedList<>();
                            elementos_expreg.add(definiciones.get(cont1).getLexema());
                        }
                        cont1++;
                    }
                    list_exp_reg.add(new ExpresionRegular(nombre_exp,elementos_expreg));
                    */
                }
            }
        }
        
        if(!conjuntos.isEmpty()){
            for(Conjunto con:conjuntos){
                System.out.println(con.toString());
            }
        }
        
        if(!list_exp_reg.isEmpty()){
            for(ExpresionRegular er:list_exp_reg){
                System.out.println(er.toString());
            }
        }
        /*Ahora extraeremos todos los tokens despues de la sepracion de %% %%*/
        int inicio = contador+4;
        for(int c = inicio;c<lista.size();c++){
            expresiones.add(lista.get(c));
        }
        int j = expresiones.size()-1;
               
        /*
        System.out.println("ultimos lexemas: "+expresiones.get(j-3).getLexema());
        System.out.println("ultimos lexemas: "+expresiones.get(j-2).getLexema());
        System.out.println("ultimos lexemas: "+expresiones.get(j-1).getLexema());
        System.out.println("ultimos lexemas: "+expresiones.get(j).getLexema());
        System.out.println("Primer lexema en lista de expresiones: "+expresiones.get(0).getLexema());
        */
        String expresion_regular = "";
        String entrada = "";
        for(Token tt:expresiones){
            if(tt.getTipo().equals("IDENTIFICADOR")){
                expresion_regular = tt.getLexema();
            }else if(tt.getLexema().equals(":") & tt.getTipo().equals("ASCII")){
                //Aqui viene el punto y coma que se esperaba
            }else if(tt.getTipo().equals("CADENA")){
                entrada = tt.getLexema();
            }else if(tt.getLexema().equals(";") & tt.getTipo().equals("ASCII")){
                lista_expresiones.add(new Expresion(entrada,expresion_regular));
                entrada = "";
                expresion_regular = "";
            }
        }
        
        if(!lista_expresiones.isEmpty()){
            for(Expresion ex : lista_expresiones){
                System.out.println("Expresion-> "+ex.toString());
            }
        }
        /*
        System.out.println("Lista de entradas de expresiones: ");
        if(!lista_expresiones.isEmpty()){
            for(Expresion ex : lista_expresiones){
                System.out.println("ExpresoinRegular: "+ex.getExpresion_regular()+", cadena de entrada: "+ex.getEntrada());
            }
        }
        */
        list_exp_reg.get(3).armarArbol(conjuntos);
        realizarMetodoTree();
    }
    
    public void realizarMetodoTree(){
        if(!list_exp_reg.isEmpty()){
            
        }
    }
    
    public int getIndex(LinkedList<Token> lista){
        int contador = 0;
        int limit = lista.size();
        for(Token tk:lista){
            int idx = lista.indexOf(tk);
            if(tk.getLexema().equals("%") & ((lista.indexOf(tk)+3)<limit)){
                if((lista.get(idx+1).getLexema().equals("%"))&(lista.get(idx+2).getLexema().equals("%"))&(lista.get(idx+3).getLexema().equals("%")) ){
                    System.out.println("lex: "+lista.get(idx-3).getLexema());
                    System.out.println("lex: "+lista.get(idx-2).getLexema());
                    System.out.println("lex: "+lista.get(idx-1).getLexema());
                    return lista.indexOf(tk);
                    
                }
                
            }
        }
        
        return contador;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_area_entrada = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_area_salida = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        generarAutomatas = new javax.swing.JButton();
        analizarEntradas = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Análisis Léxico");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Contenido archivo de entrada");

        tf_area_entrada.setColumns(20);
        tf_area_entrada.setRows(5);
        jScrollPane1.setViewportView(tf_area_entrada);

        tf_area_salida.setColumns(20);
        tf_area_salida.setRows(5);
        jScrollPane2.setViewportView(tf_area_salida);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Salida");

        generarAutomatas.setText("Generar Automatas");
        generarAutomatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarAutomatasActionPerformed(evt);
            }
        });

        analizarEntradas.setText("Analizar Entradas");
        analizarEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analizarEntradasActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem4.setText("Abrir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Guardar");
        jMenu1.add(jMenuItem3);

        jMenuItem2.setText("Guardar Como");
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Generar Salida");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(generarAutomatas, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(analizarEntradas))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarAutomatas)
                    .addComponent(analizarEntradas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if(chooserFile.showDialog(null, "Abrir")==JFileChooser.APPROVE_OPTION){
            document=chooserFile.getSelectedFile();
            if(document.canRead()){
                analizarEntradas.setEnabled(true);
                if(document.getName().endsWith("er")){
                    String texto = abrirArchivo(document);
                    //jLabel2.setText("Archivo:  "+document.getName().toString());
                    body = texto;
                    tf_area_entrada.setText(body);
                                        
                }else{
                    JOptionPane.showMessageDialog(null,"Archivo invalido!");
                }
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void generarAutomatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarAutomatasActionPerformed
        // TODO add your handling code here:
        Analizador lexico = new Analizador();
        if(!tf_area_entrada.getText().equals("")){
            lexico.analisis(tf_area_entrada.getText());
            tf_area_salida.setText(lexico.imprimirLista());
            analizarLista(lexico.tokens);
        }else{
            JOptionPane.showMessageDialog(null,"No hay archivo cargado en el area de entrada!");
        }
    }//GEN-LAST:event_generarAutomatasActionPerformed

    private void analizarEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analizarEntradasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_analizarEntradasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analizarEntradas;
    private javax.swing.JButton generarAutomatas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea tf_area_entrada;
    private javax.swing.JTextArea tf_area_salida;
    // End of variables declaration//GEN-END:variables
}
