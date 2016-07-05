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

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ExtraeJavascript {
	
	public ExtraeJavascript (){
	}

	public static void ObtenerJavascript(String URL, String navegador){
			BrowserVersion nav;
			final WebClient webClient;
			try {
			
				if (navegador == "Chrome"){
					nav = BrowserVersion.CHROME;
					webClient = new WebClient(nav);
				}else if(navegador == "Firefox"){
					nav = BrowserVersion.FIREFOX_24;
					webClient = new WebClient(nav);
				}else if(navegador == "Internet Explorer"){
					nav = BrowserVersion.INTERNET_EXPLORER_11;
					webClient = new WebClient(nav);
				}else{
					webClient = new WebClient();
				}        
	            
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
	            Logger.getLogger(ExtraeJavascript.class.getName()).log(Level.SEVERE, null, ex);
	        }
			
		}
		
	}
