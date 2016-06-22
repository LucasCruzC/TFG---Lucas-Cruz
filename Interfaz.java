import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.javafx.scene.paint.GradientUtils.Point;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Interfaz extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	JPanel panelBarra, panelArriba, panelAbajo;
	private JTextField campo;
	private JTextPane resultados;
	private JLabel introduce;
	private JLabel result;
	private JLabel imagen;
	private JButton botonA;
	String URL;
	int repintando = 0;
	private JComboBox navegador;
	
    public Interfaz(){
        construyePanelArriba();
        construyePanelAbajo();
        construyeVentana();
    }
	
	public static void main(String[] args){
    	//Elegimos estilo de la interfaz
		try {
    		//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

		new Interfaz();
    }
    
    public void construyeVentana(){
    	
        frame = new JFrame("Analizador Javascript");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));   
        frame.getContentPane().add(panelArriba);        
        frame.getContentPane().add(panelAbajo);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBounds(100, 100, 533, 504); 
    }
    
    public void construyePanelArriba(){
        panelArriba = new JPanel ();
        panelArriba.setLayout(new BorderLayout());
        panelArriba.setBackground(Color.white);
        panelArriba.setMaximumSize(new Dimension (450, 100));
        
        introduce = new JLabel("Introduce la URL completa de la página web:");
		panelArriba.add(introduce, BorderLayout.NORTH);
		
		campo = new JTextField();
		panelArriba.add(campo, BorderLayout.CENTER);
		campo.setColumns(10);
		
		botonA = new JButton("Aceptar");
		panelArriba.add(botonA, BorderLayout.EAST);
		botonA.addActionListener(this);
		botonA.setEnabled(false);
		
		navegador = new JComboBox();
		navegador.setModel(new DefaultComboBoxModel(new String[] {"--Selecciona el navegador a emular--", "Por defecto (recomendado)", "Chrome", "Firefox", "Internet Explorer"}));
		panelArriba.add(navegador, BorderLayout.SOUTH);
		navegador.addActionListener(this);
    }
    
    public void construyePanelAbajo(){
        panelAbajo = new JPanel ();
        panelAbajo.setLayout(new BorderLayout());
        panelAbajo.setBackground(Color.white);
        panelArriba.setMaximumSize(new Dimension (450, 700));
        
        result = new JLabel("Resultados: "); 
		panelAbajo.add(result, BorderLayout.NORTH);
        
		resultados = new JTextPane();
		panelAbajo.add(resultados, BorderLayout.CENTER);
		resultados.setBackground(Color.gray);
		
		ImageIcon fot = new ImageIcon("D:/Users/Lucas/Desktop/imagenes/pulgarmedio.jpg");
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        imagen = new JLabel("");
        imagen.setIcon(icono); 
		panelAbajo.add(imagen, BorderLayout.EAST);
    }
    
    public void cambiaImagen(int repintando){		
    	
    	panelAbajo.remove(imagen);
    	
    	ImageIcon fot;
		if (repintando == 1){
			fot = new ImageIcon("D:/Users/Lucas/Desktop/imagenes/pulgarabajo.jpg");
		}else if (repintando == 2){
			fot = new ImageIcon("D:/Users/Lucas/Desktop/imagenes/pulgararriba.jpg");
		}else {
			fot = new ImageIcon("D:/Users/Lucas/Desktop/imagenes/pulgarmedio.jpg");
		}
		
        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        imagen = new JLabel("");
        imagen.setIcon(icono); 
		panelAbajo.add(imagen, BorderLayout.EAST);
    }
    
	//------------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e){
        if (e.getSource()==botonA) {
        		
            URL = campo.getText();
            String emular = (String) navegador.getSelectedItem();
            
    		campo.setEnabled(false);
    		botonA.setEnabled(false);
    		navegador.setEnabled(false);
    		
    		ExtraeJavascript.ObtenerJavascript(URL,emular);
    		
    		String solucion [] = AnalizaJavascript.ObtenerDatos("D:/Users/Lucas/Desktop/resultados.js");
    		
    		resultados.setText(solucion[0]);
    		
    		if (solucion[1] == "Obfuscado"){
    			resultados.setBackground(Color.red);
    			cambiaImagen(1);
    		}else if(solucion[1] == "noObfuscado"){
    			resultados.setBackground(Color.green);
    			cambiaImagen(2);
    		}
        	
        }
        
        if (e.getSource()==navegador) {
        	if (navegador.getSelectedIndex()!= 0){
        		botonA.setEnabled(true);
        	}else{
        		botonA.setEnabled(false);
        	}
        }
	}
	
	/*public void ObtenerJavaScript(String URL){
        
		try {
			
        	//BrowserVersion navegador = BrowserVersion.FIREFOX_24;
        	//final WebClient webClient = new WebClient(navegador);
        	final WebClient webClient = new WebClient();         
            
            final HtmlPage page = webClient.getPage(URL);
    		webClient.waitForBackgroundJavaScript(3000);

    		
    		//Obtenemos los elementos tipo script    
    		DomNodeList <DomElement> nodeList = page.getElementsByTagName("script");
    		
    		//Inicializamos el escritor para obtener el .txt con los resultados
    		try {
    			File outFile = new File("D:/Users/Lucas/Desktop/resultados.js");
    			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            
            //Imprimimos el contenido de estos elementos
                     
         	int i;
            for (i=0; i<nodeList.size(); i++) {
            	
            	//Si el código no está contenido en el HTML, leemos el contenido del .js
            	if (nodeList.get(i).getTextContent().isEmpty()){
            		String enlace;
            		//enlace = "file:///D:/Users/Lucas/Desktop/codigo.js";
            		
            		//Comprobamos si el .js está dado por una URL completa o no y determinamos el enlace 
            		if (nodeList.get(i).getAttribute("src").startsWith("http")){
                		enlace = nodeList.get(i).getAttribute("src");
            		}	else{
            				enlace = URL + nodeList.get(i).getAttribute("src");
            		}
            		
            		//Imprimimos el enlace y el contenido del .js
            		//writer.write("Script " + (i+1) + " -> Contenido en: " + enlace + "\r\n\r\n");
            		System.out.println("Script " + (i+1) + " -> Contenido en: " + enlace + "\n");        		
            		try {
           	         	//Se abre la conexión
            			URL url = new URL(enlace);
           	         	java.net.URLConnection conexion = url.openConnection();
           	         	conexion.connect();
           	         
           	         	//Lectura
           	         	java.io.InputStream is = conexion.getInputStream();
           	         	BufferedReader br = new BufferedReader(new InputStreamReader(is));
           	         	char[] buffer = new char[1000];
           	         	int leido;
           	         	
           	         	//Escritura
           	         	while ((leido = br.read(buffer)) > 0) {
           	         		System.out.println(new String(buffer, 0, leido));
           	         		writer.write(new String(buffer, 0, leido));
           	         	}
           	         	System.out.println("\n");
           	         	writer.write("\r\n\r\n");
           	         	
           	      	} catch (MalformedURLException e) {
           	         e.printStackTrace();
           	      	} catch (IOException e) {
           	         e.printStackTrace();
           	      	}	
            		
            	}	
            	//Si el script no está vacío, quiere decir que el código se incluye en el HTML y directamente lo imprimimos
            	else{
            		System.out.println("Script " + (i+1) + "\n" + nodeList.get(i).getTextContent() + "\n");
            		//Las dos lineas comentadas a continuación es para que los resultados no tengan espacios artificiales
            		//writer.write("Script " + (i+1) + "\r\n\r\n");
            		writer.write(nodeList.get(i).getTextContent());
            		//writer.write("\r\n\r\n");
            	}
            }
        
        System.out.println ("Todo terminado OK " + page);
        //writer.write("Todo terminado OK " + page);
    		
        	//Finalizamos el escritor
        		writer.close();
    		} catch (IOException e) {
    			System.err.println(e);
    			System.exit(1);
    		}
    		
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}*/

}
