import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AnalizaJavascript2 {
	
	//Constructor cuando haya que hacerlo todo de una vez
	public AnalizaJavascript2 (){
	}

	public static void main(String[] args) throws IOException {
	//Activar la siguiente línea y comentar la anterior para hacerlo todo de una vez
	//public static String ObtenerDatos(String archivo) {   	
    	
		String archivos[] = new String[40];
		archivos[0] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalculadora.js";
		archivos[1] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalculadoraO1.js";
		archivos[2] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalculadoraO2.js";
		archivos[3] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalculadoraO3.js";
		archivos[4] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalculadoraO4.js";
		
		archivos[5] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalendario.js";
		archivos[6] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalendarioO1.js";
		archivos[7] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalendarioO2.js";
		archivos[8] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalendarioO3.js";
		archivos[9] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosCalendarioO4.js";
		
		archivos[10] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosDiapositivas.js";
		archivos[11] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosDiapositivasO1.js";
		archivos[12] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosDiapositivasO2.js";
		archivos[13] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosDiapositivasO3.js";
		archivos[14] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosDiapositivasO4.js";
		
		archivos[15] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosGoogle.js";
		archivos[16] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosGoogleO1.js";
		archivos[17] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosGoogleO2.js";
		archivos[18] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosGoogleO3-nova.js";
		archivos[19] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosGoogleO4.js";
		
		archivos[20] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosReloj.js";
		archivos[21] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosRelojO1.js";
		archivos[22] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosRelojO2.js";
		archivos[23] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosRelojO3.js";
		archivos[24] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosRelojO4.js";
		
		archivos[25] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosTetris.js";
		archivos[26]= "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosTetrisO1.js";
		archivos[27] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosTetrisO2.js";
		archivos[28] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosTetrisO3.js";
		archivos[29] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosTetrisO4.js";
		
		archivos[30] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosWikipedia.js";
		archivos[31] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosWikipediaO1.js";
		archivos[32] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosWikipediaO2.js";
		archivos[33] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosWikipediaO3.js";
		archivos[34] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosWikipediaO4.js";
		
		archivos[35] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosYoutube.js";
		archivos[36] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosYoutubeO1.js";
		archivos[37] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosYoutubeO2.js";
		archivos[38] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosYoutubeO3.js";
		archivos[39] = "D:/Users/Lucas/Desktop/CodigosParaAnalizar/resultadosYoutubeO4.js";
    	
		try {
			File outFile = new File("D:/Users/Lucas/Desktop/datos.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			
		for(int t=0; t<40; t++){
			
			String archivo = archivos[t];
		
		String solution = "";
    	

    	
//------Tamaño del documento-----------------------------------------------------------------------------------------    	
    	
    	int numcaracter = archivolength(archivo);
    	System.out.println("El tamaño del archivo es de: " +numcaracter+ " caracteres");
    	writer.write(numcaracter + " ");
    	solution = solution + "El tamaño del archivo es de: " +numcaracter+ " caracteres" +"\n";
    	
//------Ratio of string definitions and string uses------------------------------------------------------------------

        int ss = cuentapalabra(archivo, "substring");
        int fcc = cuentapalabra(archivo, "fromCharCode");
        
        int wr = cuentapalabra(archivo, "write");
        int ev = cuentapalabra(archivo, "eval");
        
        double ratio;
        if ((wr+ev)!=0){
            ratio = (double)(ss+fcc)/(wr+ev);
        	System.out.println("Ratio of string definitions and string uses: " + ratio);
        	writer.write(ratio + " ");
        	solution = solution + "Ratio of string definitions and string uses: " + ratio +"\n";
        }else{
        	ratio = 0;
        	System.out.println("Ratio of string definitions and string uses: " + ratio);
        	writer.write(ratio + " ");
        	solution = solution + "Ratio of string definitions and string uses: " + ratio +"\n";
        }
        
//------Ratio of string definitions and string uses (Método 2)-------------------------------------------------------
        
        String [] ssarg = extraeargumentos(archivo, "substring");
        String [] fccarg = extraeargumentos(archivo, "fromCharCode");
        
        int i = 0;
        int sspositivos = 0;
        while ((i <= ssarg.length-1) && (ssarg[i] != null)){
        	int enc = cuentapalabra(archivo, ssarg[i]);
        	i++;
        	if (enc > 1){
        		sspositivos++;
        	}
        }
        
        i = 0;
        int fccpositivos = 0;
        while ((i <= fccarg.length-1) && (fccarg[i] != null)){
        	int enc = cuentapalabra(archivo, fccarg[i]);
        	i++;
        	if (enc > 1){
        		fccpositivos++;
        	}
        }
        
        double ratio2; 
        if ((wr+ev)!=0){
            ratio2 = (double)(sspositivos+fccpositivos)/(ss+fcc);
        	System.out.println("Ratio of string definitions and string uses (Metodo 2): " + ratio2);
        	writer.write(ratio2 + " ");
        	solution = solution + "Ratio of string definitions and string uses (Metodo 2): " + ratio2 +"\n";
        }else {
        	ratio2 = 0;
        	System.out.println("Ratio of string definitions and string uses (Metodo 2): " + ratio2);
        	writer.write(ratio2 + " ");
        	solution = solution + "Ratio of string definitions and string uses (Metodo 2): " + ratio2 +"\n";
        }
        
//------Number of dynamic code executions----------------------------------------------------------------------------
        
        int st = cuentapalabra(archivo, "setTimeout");
        int dw = cuentapalabra(archivo, "document.write");
        int dce = cuentapalabra(archivo, "document.createElement");
        
        int nodce = ev+st+dw+dce;
        System.out.println("Number of dynamic code executions: " + nodce);
        writer.write(nodce + " ");
        solution = solution + "Number of dynamic code executions: " + nodce +"\n";
        
//------Length of dynamically evaluated code-------------------------------------------------------------------------
        
        String [] argeval = extraeargumentos(archivo, "eval");
        double media = 0;
        double ldec;
        int j=0;
        
        while ((j <= argeval.length-1) && (argeval[j] != null)){
        	media=media+argeval[j].length();
        	j++;
        }
        
        if (argeval.length!=0){
            ldec = media/argeval.length;
        }else
        	ldec = 0;

        System.out.println("Length of dynamically evaluated code: " + ldec);
        writer.write(ldec + " ");
        solution = solution + "Length of dynamically evaluated code: " + ldec +"\n";
                
//------Cantidad comentarios-----------------------------------------------------------------------------------------
        
        int cm1 = cuentapalabra(archivo, "//");
        int cm2 = cuentapalabra(archivo, "/*");
        
        int cc = cm1+cm2;        
        System.out.println("Cantidad comentarios: " + cc);
        writer.write(cc + " ");
        solution = solution + "Cantidad comentarios: " + cc +"\n";
        
//------Cantidad espacios en blanco----------------------------------------------------------------------------------
        
        int eb1 = cuentapalabra(archivo, "\t");
        int eb2 = cuentapalabra(archivo, "\n");
        int eb3 = cuentapalabra(archivo, "\r");
        int eb4 = cuentapalabra(archivo, " ");
        
        int ceb = eb1+eb2+eb3+eb4;
        System.out.println("Cantidad espacios en blanco: " + ceb);
        writer.write(ceb + " ");
        solution = solution + "Cantidad espacios en blanco: " + ceb +"\n";
        
//------Divisiones de String-----------------------------------------------------------------------------------------
        
        //buscamos la cadena ["+"] que identificaría posibles strings formados por substrings
        String divstr = '\u0022'+"+"+'\u0022';
        int ds1 = cuentapalabra(archivo, divstr);
        int ds2 = cuentapalabra(archivo, "split()");
        
        //buscamos "" correspondiente a un string vacío
        String vacio = '\u0022'+""+'\u0022';
        int ds3 = cuentapalabra(archivo, vacio);
        
        int ds = ds1+ds2+ds3;
        System.out.println("Divisiones de String: " + ds);
        writer.write(ds + " ");
        solution = solution + "Divisiones de String: " + ds +"\n";
        
//------Cantidad operaciones-----------------------------------------------------------------------------------------
        
        int sum = cuentacaracter(archivo, '+');
        int res = cuentacaracter(archivo, '-');
        int mul = cuentacaracter(archivo, '*');
        int div = cuentacaracter(archivo, '/');
        
        int co = sum+res+mul+div;
        System.out.println("Cantidad operaciones: " + co);
        writer.write(co + " "); 
        solution = solution + "Cantidad operaciones: " + co +"\n";
        
//------Cantidad hexadecimales y unicode-----------------------------------------------------------------------------
        
        int hex = cuentapalabra(archivo, "0x");
        int uni = cuentapalabra(archivo, '\u0050'+"u");
        int hex2 = cuentapalabra(archivo, '\u0050'+"x");
        
        int chu = hex+uni+hex2;
        System.out.println("Cantidad hexadecimales y unicode: " + chu);
        writer.write(chu + "\n");
        solution = solution + "Cantidad hexadecimales y unicode: " + chu +"\n";
	
//------Finalizamos el escritor--------------------------------------------------------------------------------------
			
		}
        writer.close();
    	} catch (IOException e) {
    		System.err.println(e);
    		System.exit(1);
    	}
	//return solution;
	}
	
//-------------------------------------------------------------------------------------------------------------------
//----  MÉTODOS  ---------------------------------------------------------------------------------------------------- 
//-------------------------------------------------------------------------------------------------------------------
		
	public static int cuentapalabra(String archivo, String palabra) throws FileNotFoundException, IOException {
		
		BufferedReader b;
        FileReader f = new FileReader(archivo);
        b = new BufferedReader(f);
    	int j=0;
    	
    	//Opción por palabras: no funciona 
		/*String cadena;
        while((cadena = b.readLine())!=null) {
            //System.out.println(cadena);
            
            String[] palabras = cadena.split(" "); 
            for(int i = 0; i < palabras.length; i++){ 
            	
            	if(palabras[i].equals(palabra)){ 
            		j++;
            	}
            }
        }*/
    	
    	//Opción por carácteres
    	int caracter;
    	while((caracter = b.read()) != -1) {
        	
    		//"sigue" es un contador que cuantifica las coincidencias de letras    		
    		int sigue = 0;    		
    		for(int i=0; i<=palabra.length()-1; i++ ){		
    			if (caracter == palabra.charAt(i)){
    				sigue++;
    				caracter = b.read();
    			}
    		}
    		
    		if (sigue == palabra.length()){
    			j++;
    		}
    	}
    	
       	//System.out.println("Apariciones de [" + palabra+"]: " + j);
    	b.close();
    	return j;
    }
	
//-------------------------------------------------------------------------------------------------------------------	
	public static boolean buscapalabra(String archivo, String palabra) throws FileNotFoundException, IOException {
		
		BufferedReader b;
        FileReader f = new FileReader(archivo);
        b = new BufferedReader(f);
        boolean encontrada = false;
    	
		int caracter;
    	while((caracter = b.read()) != -1) {
    		
    		int sigue = 0;    		
    		for(int i=0; i<=palabra.length()-1; i++ ){   			
    			if (caracter == palabra.charAt(i)){
    				sigue++;
    				caracter = b.read();
    			}
    		}
    		
    		if (sigue == palabra.length()){
    			encontrada = true;
    		}
    	}
    	
    	b.close();
    	return encontrada;
	}
	
//-------------------------------------------------------------------------------------------------------------------	
	public static int cuentacaracter(String archivo, int caracter) throws FileNotFoundException, IOException {
		
		BufferedReader b;
        FileReader f = new FileReader(archivo);
        b = new BufferedReader(f);
    	int j=0;
    	
    	int caracter1;
    	
    	while((caracter1 = b.read()) != -1) {	    		
    		if (caracter1 == caracter){
    			j++;
    		}
    	}
    	
    	b.close();
    	return j;
	}
	
//-------------------------------------------------------------------------------------------------------------------	
	public static int archivolength(String archivo) throws FileNotFoundException, IOException {
		
		BufferedReader b;
        FileReader f = new FileReader(archivo);
        b = new BufferedReader(f);
    	
    	int numcaracter = 0;
    	int caracter;
    	
    	while((caracter = b.read()) != -1) {	
    		numcaracter++;
    	}
    	
    	b.close();
    	return numcaracter;
	}
	
//-------------------------------------------------------------------------------------------------------------------	
	public static String[] extraeargumentos(String archivo, String funcion) throws FileNotFoundException, IOException {

		BufferedReader b;
        FileReader f = new FileReader(archivo);
        b = new BufferedReader(f);
        
    	int caracter;
    	int apariciones = cuentapalabra(archivo, funcion);
    	String argumentos[] = new String[apariciones];
    	int posicion = 0;
    	
    	while((caracter = b.read()) != -1) {
    		int longi = 0;
    		
    		for(int i=0; i<=funcion.length()-1; i++ ){    			
    			if (caracter == funcion.charAt(i)){
    				longi++;
    				caracter = b.read();
    			}
    		}
    		
    		if (longi == funcion.length() && caracter == '('){
    	    	int parentesis = 1;
    			int fin = 0;
    			StringBuffer arg=new StringBuffer();
    			arg.append((char)caracter);
    			
    			while(fin != 1) {
    				caracter = b.read();
    				arg.append((char)caracter);
    				
    				if (caracter == '('){
    					parentesis++;
    				}
    				if (caracter == ')'){
    					parentesis--;
    				}
    				if (parentesis == 0){
    					argumentos[posicion]=arg.toString();
    					posicion++;
    					fin =1;
    				}
    			}
    		}
    	}
    	
    	b.close();
    	return argumentos;
	}
	
}	


