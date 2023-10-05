/*
    JframeForm que contiene la interfaz gráfica del proyecto. así como también toda su funcionabilidad.
 */
package traductor.gui;

import java.awt.Color;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import traductor.Traductor;
import traductor.readCSV;
import traductor.saveConfig;

/**
 *
 * @author David
 */

/*  Al agregar el botón de borrar optamos por usar un Hilo el cuál serviria para verificar si los JtextAreaDe y JtextAreaA
    Si estos campos son diferentes a nulo o "", entonces se procedera a mostrar el botón de borrar para que el usuario elimine los campos
    Si los JtextArea's son nulos o vacios el botón se procederia a ocultar. Por ende, al querer aplicar ese dinamismo al botón de borrar
    A la clase le aplicamos la implementación de ejectuable para que, se poder crear una sub-ejecución.
*/

public final class main extends javax.swing.JFrame implements Runnable {
        
    Thread hilo;
    //Constructor de la clase
    public main() {
        //método que es llamado por el constructor para inicializar todos los componentes gráficos. 
        initComponents();
        //método que sirve para deshabilitar el botón de maximizar ventana.
        this.setResizable(false);
        //método que sirve para darle un titulo a la ventana del programa.
        this.setTitle("IDION Translate");
        //método que sirve para inicializar la ventana del programa en el centro de la pantalla.
        this.setLocationRelativeTo(null);
        //método que sirve para colocarle un icono al programa.
        this.setIconImage(new ImageIcon(getClass().getResource("/img/traductor.png")).getImage());
        //Ocultamos el botón de borrar al iniciar el programa.
        jButtonBorrar.setVisible(false);
        //Ocultamos la etiqueta de error de conexión, solo será visible cuando no se detecte conexión a internet.
        jLabelErrorConnection.setVisible(false);
        //función la cual sirve para obtener todos los idiomas de la clase readCSV para añadirlos a los jComboBox.
        getIdiomas();
        //variable de tipo file que sirve para verificar si el fichero xml que contiene la configuración existe; de ser así, 
        //se deserializara el fichero y se cargara la configuración guardada
        File archivo = new File(System.getProperty("user.dir")+"\\saves"+"\\config.xml");
        if (archivo.exists()) {
            try {
                des_serializar();
                jToggleButtonModo.doClick();
            } catch (Exception ex) {
            }
        }
        
        AutoCompleteDecorator.decorate(jComboBoxA);
        AutoCompleteDecorator.decorate(jComboBoxDe);
        
        hilo = new Thread(this);
        hilo.start();
    }
    
    //variable lógica que sirve para cambiar entre el modo claro y oscuro del programa, porque sí le programa cuenta con modo oscuro y claro.
    private boolean mode = false;
    //objeto de tipo readCSV para llamar a su metodo obtener idiomas.
    private readCSV read = new readCSV();
    //objeto de tipo saveConfig para guardar los cambios aplicados para su posterios serializaicón.
    private saveConfig m = new saveConfig();
    //variable arraylist que permite almacenar los codigos de los diomas en una lista.
    private ArrayList<String> codigos = new ArrayList<>();
    
    //función que permite asignar las opciones de idiomas a los combo box
    private void getIdiomas() {
        //arraylist que obtiene los datos que retonara la función readCsv();
        ArrayList<String> idiomas = read.readCsv();
        //ciclo que permite asignar las opciones de los idiomas a los jcombobox y almacena los codigos de los idiomas al arraylist codigo
        for (int i = 3; i < idiomas.size(); i+=2) {
            jComboBoxDe.addItem(idiomas.get(i));
            jComboBoxA.addItem(idiomas.get(i));
            codigos.add(idiomas.get(i-1));
        }
    }
    
    //método de implementación Runnable el cuál permite ejecutar un proceso independiente al proceso principal
    //Logrando así que se valide la opción del botón y se proceda a mostrar o no
    public void run() {
        while (true) {  
            try {
                if (jTextAreaDe.getText() != null && !jTextAreaDe.getText().equals("")){
                    //jButtonTraducir.doClick();
                    jButtonBorrar.setVisible(true);
                } else if (jTextAreaA.getText() != null && !jTextAreaA.getText().equals("")) {
                    jButtonBorrar.setVisible(true);
                }
                else {
                    jButtonBorrar.setVisible(false);
                }
                
                //Se comprueba si hay o no conexión a internet.
                jLabelErrorConnection.setVisible(checkConnection());
                
                //hilo.sleep(250);
            } catch (Exception ex) {
            }
        }
    }
    
    //metodo en el cual se comprueba si hay conexión a Internet.
    private boolean checkConnection() throws UnknownHostException {
        if("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString())) return true;
        else return false;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToggleButtonModo = new javax.swing.JToggleButton();
        jToggleButtonInvertir = new javax.swing.JToggleButton();
        jLabelLogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBoxA = new javax.swing.JComboBox<>();
        jComboBoxDe = new javax.swing.JComboBox<>();
        jButtonBorrar = new javax.swing.JButton();
        jButtonTraducir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaA = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDe = new javax.swing.JTextArea();
        jLabelErrorConnection = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 47, 74));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToggleButtonModo.setFont(new java.awt.Font("Agency FB", 1, 11)); // NOI18N
        jToggleButtonModo.setForeground(new java.awt.Color(72, 150, 189));
        jToggleButtonModo.setText("Light");
        jToggleButtonModo.setBorder(null);
        jToggleButtonModo.setBorderPainted(false);
        jToggleButtonModo.setContentAreaFilled(false);
        jToggleButtonModo.setFocusPainted(false);
        jToggleButtonModo.setFocusable(false);
        jToggleButtonModo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonModo.setRequestFocusEnabled(false);
        jToggleButtonModo.setRolloverEnabled(false);
        jToggleButtonModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonModoActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonModo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 33, 9));

        jToggleButtonInvertir.setFont(new java.awt.Font("Agency FB", 1, 11)); // NOI18N
        jToggleButtonInvertir.setForeground(new java.awt.Color(72, 150, 189));
        jToggleButtonInvertir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/circulo.png"))); // NOI18N
        jToggleButtonInvertir.setBorder(null);
        jToggleButtonInvertir.setBorderPainted(false);
        jToggleButtonInvertir.setContentAreaFilled(false);
        jToggleButtonInvertir.setFocusPainted(false);
        jToggleButtonInvertir.setFocusable(false);
        jToggleButtonInvertir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonInvertir.setRequestFocusEnabled(false);
        jToggleButtonInvertir.setRolloverEnabled(false);
        jToggleButtonInvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonInvertirActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonInvertir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 40, 30));

        jLabelLogo.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jLabelLogo.setForeground(new java.awt.Color(92, 102, 123));
        jLabelLogo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/TextLogo.png"))); // NOI18N
        jPanel1.add(jLabelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 281, 380, 90));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 10, 250));

        jComboBoxA.setBackground(new java.awt.Color(30, 47, 74));
        jComboBoxA.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jComboBoxA.setForeground(new java.awt.Color(126, 147, 150));
        jComboBoxA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione su idioma" }));
        jPanel1.add(jComboBoxA, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 460, 30));

        jComboBoxDe.setBackground(new java.awt.Color(30, 47, 74));
        jComboBoxDe.setFont(new java.awt.Font("Agency FB", 0, 18)); // NOI18N
        jComboBoxDe.setForeground(new java.awt.Color(126, 147, 150));
        jComboBoxDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione su idioma" }));
        jPanel1.add(jComboBoxDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 460, 30));

        jButtonBorrar.setFont(new java.awt.Font("Agency FB", 0, 17)); // NOI18N
        jButtonBorrar.setForeground(new java.awt.Color(236, 248, 244));
        jButtonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-Palid-B.png"))); // NOI18N
        jButtonBorrar.setText("DEL");
        jButtonBorrar.setBorderPainted(false);
        jButtonBorrar.setFocusPainted(false);
        jButtonBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBorrar.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonBorrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-Palid-B.png"))); // NOI18N
        jButtonBorrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-N.png"))); // NOI18N
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, 50, 50));

        jButtonTraducir.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jButtonTraducir.setForeground(new java.awt.Color(236, 248, 244));
        jButtonTraducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-N-Result.png"))); // NOI18N
        jButtonTraducir.setText("Traducir");
        jButtonTraducir.setBorderPainted(false);
        jButtonTraducir.setFocusPainted(false);
        jButtonTraducir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTraducir.setPreferredSize(new java.awt.Dimension(50, 50));
        jButtonTraducir.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-N-Result.png"))); // NOI18N
        jButtonTraducir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Botton-Pressed-N-Result.png"))); // NOI18N
        jButtonTraducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTraducirActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonTraducir, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 130, -1));

        jTextAreaA.setBackground(new java.awt.Color(18, 38, 68));
        jTextAreaA.setColumns(20);
        jTextAreaA.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextAreaA.setForeground(new java.awt.Color(126, 147, 150));
        jTextAreaA.setLineWrap(true);
        jTextAreaA.setRows(5);
        jTextAreaA.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextAreaA);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 460, 220));

        jTextAreaDe.setBackground(new java.awt.Color(18, 38, 68));
        jTextAreaDe.setColumns(20);
        jTextAreaDe.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextAreaDe.setForeground(new java.awt.Color(126, 147, 150));
        jTextAreaDe.setLineWrap(true);
        jTextAreaDe.setRows(5);
        jTextAreaDe.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextAreaDe);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 460, 220));

        jLabelErrorConnection.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jLabelErrorConnection.setForeground(new java.awt.Color(255, 0, 0));
        jLabelErrorConnection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelErrorConnection.setText("<html>Parece que hay un problema con la conexión a Internet.<html>");
        jPanel1.add(jLabelErrorConnection, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 280, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //método del botón traducir el cual permite traductir el texto digitado. 
    private void jButtonTraducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTraducirActionPerformed
        try {
            //Se verifica que los datos que retornan los jcombobox sean mayores a -1 de ser así se procede con la traducción
            //caso contrario se advertira al usuario que elija los idiomas de la traducción
            if ((jComboBoxDe.getSelectedIndex()-1) > -1 && (jComboBoxA.getSelectedIndex()-1) > -1)
                //Se le asina al texarea la cadena de caracteres que retonarnara la función getTranslate()
                jTextAreaA.setText(Traductor.getTranslate(
                        codigo((jComboBoxDe.getSelectedIndex()-1)), 
                        codigo((jComboBoxA.getSelectedIndex()-1)), 
                        jTextAreaDe.getText()));
            else JOptionPane.showMessageDialog(this, "Eliga los idiomas a traducir.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jButtonTraducirActionPerformed
    
    //función el cual permite obtener el codigo del idioma de origen y el idioma final a traducir ejemplo es = español y en = inglés
    private String codigo (int x) {
        return codigos.get(x);
    }
    
    //función el cual permite invertir los idiomas y la traducción, ejemplo inglés -> español a español -> inglés.
    private void jToggleButtonInvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonInvertirActionPerformed
        //Se utiliza intercambio de variables para intercambiar los valores las posteriores variables.
        String aux = "";
        aux = (String) jComboBoxDe.getSelectedItem();
        jComboBoxDe.setSelectedItem(jComboBoxA.getSelectedItem());
        jComboBoxA.setSelectedItem(aux);
        //Se comprueba que el textarea de texto traducido sea diferente a "", de ser así se procedera a realizar tmb intercambio de variables
        //entre los textarea para traducir el texto.
        if (!jTextAreaA.getText().equals("")) {
            jTextAreaDe.setText(jTextAreaA.getText());
            jButtonTraducir.doClick();
        }
    }//GEN-LAST:event_jToggleButtonInvertirActionPerformed
    
    //método que permite cambiar entre los distintos modos del programa, modo oscuro a modo claro y viceversa.
    private void jToggleButtonModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonModoActionPerformed
        //Lo que hace este metodo en pocas palabras es cambiarle las propiedades a los objetos graficos desde el propio codigo.
        if (!mode) {
            m.setModo(false);
            jPanel1.setBackground(Color.decode("#FFFFFF"));
            jTextAreaDe.setBackground(Color.decode("#F2F2F2"));
            jTextAreaDe.setForeground(Color.decode("#4896BD"));
            jTextAreaA.setBackground(Color.decode("#F2F2F2"));
            jTextAreaA.setForeground(Color.decode("#4896BD"));
            jComboBoxDe.setBackground(Color.decode("#FFFFFF"));
            jComboBoxDe.setForeground(Color.decode("#4896BD"));
            jComboBoxA.setBackground(Color.decode("#FFFFFF"));
            jComboBoxA.setForeground(Color.decode("#4896BD"));
            jToggleButtonModo.setIcon(new ImageIcon(getClass().getResource("/img/MC.png")));
            jLabelLogo.setIcon(new ImageIcon(getClass().getResource("/img/TextLogoB.png")));
            
            jButtonTraducir.setIcon(new ImageIcon(getClass().getResource("/img/Botton Pressed B Result.png")));
            jButtonTraducir.setRolloverIcon(new ImageIcon(getClass().getResource("/img/Botton Pressed B Result Pressed.png")));
            jButtonTraducir.setPressedIcon(new ImageIcon(getClass().getResource("/img/Botton Pressed B Result.png")));
            jButtonTraducir.setForeground(Color.decode("#ECF8F4"));
            
            jButtonBorrar.setIcon(new ImageIcon(getClass().getResource("/img/Botton B.png")));
            jButtonBorrar.setRolloverIcon(new ImageIcon(getClass().getResource("/img/Botton Pressed B.png")));
            jButtonBorrar.setPressedIcon(new ImageIcon(getClass().getResource("/img/Botton B.png")));
            jButtonBorrar.setForeground(Color.decode("#4896BD"));
            
            jToggleButtonModo.setText("Dark");
            jToggleButtonModo.setForeground(Color.decode("#4896BD"));
            mode = true;
        } else {
            m.setModo(true);
            jPanel1.setBackground(Color.decode("#1E2F4A"));
            jTextAreaDe.setBackground(Color.decode("#122644"));
            jTextAreaDe.setForeground(Color.decode("#7E9396"));
            jTextAreaA.setBackground(Color.decode("#122644"));
            jTextAreaA.setForeground(Color.decode("#7E9396"));
            jComboBoxDe.setBackground(Color.decode("#1E2F4A"));
            jComboBoxDe.setForeground(Color.decode("#7E9396"));
            jComboBoxA.setBackground(Color.decode("#1E2F4A"));
            jComboBoxA.setForeground(Color.decode("#7E9396"));
            jToggleButtonModo.setIcon(new ImageIcon(getClass().getResource("/img/MD.png")));
            jLabelLogo.setIcon(new ImageIcon(getClass().getResource("/img/TextLogo.png")));
            
            jButtonTraducir.setIcon(new ImageIcon(getClass().getResource("/img/Botton-N-Result.png")));
            jButtonTraducir.setRolloverIcon(new ImageIcon(getClass().getResource("/img/Botton-Pressed-N-Result.png")));
            jButtonTraducir.setPressedIcon(new ImageIcon(getClass().getResource("/img/Botton-N-Result.png")));
            jButtonTraducir.setForeground(Color.decode("#212121"));
            
            jButtonBorrar.setIcon(new ImageIcon(getClass().getResource("/img/Botton-N.png")));
            jButtonBorrar.setRolloverIcon(new ImageIcon(getClass().getResource("/img/Botton-Palid-B.png")));
            jButtonBorrar.setPressedIcon(new ImageIcon(getClass().getResource("/img/Botton-N.png")));
            jButtonBorrar.setForeground(Color.decode("#7E9396"));
            
            jToggleButtonModo.setText("Light");
            jToggleButtonModo.setForeground(Color.decode("#7E9396"));
            mode = false;
        }
    }//GEN-LAST:event_jToggleButtonModoActionPerformed
    
    //método del jframe el cual verifica si se preisiono el boton de cerrar programa de ser así se procede a guardar los cambios hechos por el usuario
    //y dichos cambios se serializan en el fichero xml para su futura ejeción restablecer la configuración aplicada.
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            m.setDe(jComboBoxDe.getSelectedIndex());
            m.setA(jComboBoxA.getSelectedIndex());
            serializar();
        } catch (Exception ex) {
        }
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing
    
    
    //método del jButtonBorrar el cuál borrar los datos de los JtextArea's
    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        jTextAreaDe.setText("");
        jTextAreaA.setText("");
    }//GEN-LAST:event_jButtonBorrarActionPerformed
    
    //función para serializar los valores almcenados en los atributos de la clase saveConfig
    private void serializar() throws Exception {
        //Se crea el fichero xml
        FileOutputStream archivoSalida = new FileOutputStream(System.getProperty("user.dir")+"\\saves"+"\\config.xml");
        
        //Utilizando XMLEncoder
        XMLEncoder xe = new XMLEncoder(archivoSalida);
        xe.writeObject(m);
        
        xe.close();
    }
    
    //función para deserializar el fichero xml y para aplicar los varoles alcamencados a sus respectivas variables
    private void des_serializar() throws Exception {
        //se abre el fichero xml
        FileInputStream archivoEntrada = new FileInputStream(System.getProperty("user.dir")+"\\saves"+"\\config.xml");
        saveConfig copia = null;
        
        //Utilizando XMLDecoder
        XMLDecoder xd = new XMLDecoder(archivoEntrada);
        copia = (saveConfig) xd.readObject();
        xd.close();
        // se asigna el valor del metodo isModo() del objeto copia de la clase saveConfig ese metodo guarda que modo tenia el usuario si el claro u oscuro
        mode = copia.isModo();
        // el metodo getDe() obtiene el idioma de origen
        jComboBoxDe.setSelectedIndex(copia.getDe());
        // el metodo getA() obtiene el idioma a traducir
        jComboBoxA.setSelectedIndex(copia.getA());
    }
    
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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonTraducir;
    private javax.swing.JComboBox<String> jComboBoxA;
    private javax.swing.JComboBox<String> jComboBoxDe;
    private javax.swing.JLabel jLabelErrorConnection;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaA;
    private javax.swing.JTextArea jTextAreaDe;
    private javax.swing.JToggleButton jToggleButtonInvertir;
    private javax.swing.JToggleButton jToggleButtonModo;
    // End of variables declaration//GEN-END:variables
}
