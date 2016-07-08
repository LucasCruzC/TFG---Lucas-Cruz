import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.BrowserVersionFeatures;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import net.sourceforge.htmlunit.corejs.javascript.ScriptableObject;
import sun.net.www.URLConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.portable.InputStream;

public class Principal {

    public static void main(String[] args) throws FailingHttpStatusCodeException {
        
        try {
        	
            //Crear una nueva BrowserVersion
        	/*String applicationName = "Netscape";
            String applicationVersion = "5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.108 Safari/537.36";
            String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.108 Safari/537.36";
            int browserVersionNumeric = 46;

            BrowserVersion browser = new BrowserVersion(applicationName, applicationVersion, userAgent, browserVersionNumeric) {
                public boolean hasFeature(BrowserVersionFeatures property) {

                    // change features here
                    return BrowserVersion.CHROME.hasFeature(property);
                }
            };*/
        	
            //BrowserVersion navegador = BrowserVersion.CHROME;
        	//final WebClient webClient = new WebClient(navegador);
        	final WebClient webClient = new WebClient();
        	
            String URL = "file://D:/Users/Lucas/Desktop/pagina.html";
            //String URL = "http://htmlunit.sourceforge.net/";
            //String URL = "https://www.uv.es/jac/guia/jscript/calendario.html";
            //webClient.getOptions().setUseInsecureSSL(true);
            
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
          
    }
    
}
