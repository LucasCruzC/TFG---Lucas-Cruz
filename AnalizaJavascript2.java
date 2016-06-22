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


