package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import libro.Libro;

//Este hilo va a hacer las labores de la biblioteca
//Conexi�n abierta hasta que el cliente pulse 5


public class HiloLibros implements Runnable{
	private Thread hilo;
	private static int numCliente = 0;
	private Socket socketAlCliente;	
	
	public HiloLibros(Socket socketAlCliente) {
		numCliente++;
		hilo = new Thread(this, "Cliente_"+numCliente);
		this.socketAlCliente = socketAlCliente;
		hilo.start();
	}
	
	@Override
	public void run() {
		System.out.println("Estableciendo comunicacion con " + hilo.getName());
		PrintStream salida = null;
		InputStreamReader entrada = null;
		BufferedReader entradaBuffer = null;
		
		try {
			//Salida del servidor al cliente
			salida = new PrintStream(socketAlCliente.getOutputStream());
			 
			//Entrada del servidor al cliente
			entrada = new InputStreamReader(socketAlCliente.getInputStream());
			entradaBuffer = new BufferedReader(entrada);
			
			String textoRecibido = "";
			boolean continuar = true;
			
			//Procesaremos entradas hasta que el texto del cliente sea FIN
			while (continuar) {
				textoRecibido = entradaBuffer.readLine();
				
				//Separo el String que me ha llegado por gui�n
				String[] input = textoRecibido.split("-");
				String opcionMenu = input[0];
				String texto = input[1];
				
				//Primero compruebo si el cliente quiere salir de la aplicaci�n (opci�n 5 del men�)
				if (opcionMenu.trim().equalsIgnoreCase("5")) { 
					System.out.println(hilo.getName() + " ha cerrado la comunicacion");
					continuar = false;
					salida.println("FIN");
				
				}else if(opcionMenu.trim().equalsIgnoreCase("1")) {

				    for (int i = 0; i < SocketServidor.biblioteca.size(); i++) {
				    	
				    	if (texto.trim().equalsIgnoreCase(SocketServidor.biblioteca.get(i).getIsbn())){
				    		System.out.println("Libro encontrado con ese ISBN: " + SocketServidor.biblioteca.get(i));
							salida.println("Libro encontrado con ese ISBN: " + SocketServidor.biblioteca.get(i));

				    	}else if (i==SocketServidor.biblioteca.size()) {
				    		System.out.println("Libro no encontrado con ese ISBN");
							salida.println("Libro no encontrado con ese ISBN");

				    	}
				        
				    }					
				
				}else if(opcionMenu.trim().equalsIgnoreCase("2")) {
					
				    for (int i = 0; i < SocketServidor.biblioteca.size(); i++) {
				    	
				    	if (texto.trim().equalsIgnoreCase(SocketServidor.biblioteca.get(i).getTitulo())){
				    		System.out.println("Libro encontrado con ese t�tulo: " + SocketServidor.biblioteca.get(i));
				    		salida.println("Libro encontrado con ese t�tulo: " + SocketServidor.biblioteca.get(i));

				    	}else if (i==SocketServidor.biblioteca.size())  {
				    		System.out.println("Libro no encontrado con ese t�tulo");
						    salida.println("Libro no encontrado con ese t�tulo");
				    	}
				        
				      }	
				    
				    			
				}else if(opcionMenu.trim().equalsIgnoreCase("3")) {
					
		    		String librosAutor="";

				    for (int i = 0; i < SocketServidor.biblioteca.size(); i++) {
				    	
				    	if (texto.trim().equalsIgnoreCase(SocketServidor.biblioteca.get(i).getAutor())){
				    		if (librosAutor=="") {
				    			librosAutor = SocketServidor.biblioteca.get(i).toString();
				    		}else {
				    			librosAutor += "****" + SocketServidor.biblioteca.get(i).toString();
				    		}
				    	}
				    }
				    
				    if (librosAutor.equals("")) {
			    		System.out.println("No hay libros encontrados para este autor");
					    salida.println("No hay libros encontrados para este autor");
				    }else {
			    		System.out.println("Libros encontrados con ese autor: " + librosAutor);
					    salida.println("Libros encontrados con ese autor: " + librosAutor);
				    }
				
				    
				    
				}else if(opcionMenu.trim().equalsIgnoreCase("4")) {
					
					//Divido la segunda entrada con "/" para obtener los 4 campos del libro
					String[] a�adirLibro = input[1].split("/");
					String isbn = a�adirLibro[0];
					String titulo = a�adirLibro[1];
					String autor = a�adirLibro[2];
					Double precio = Double.parseDouble(a�adirLibro[3]);
					
					SocketServidor.biblioteca.add(new Libro(isbn, titulo, autor, precio));
	
					int i = SocketServidor.biblioteca.size();
					i--; //Si se queda en .size() da excepci�n outBound porque los �ndices empiezan en 0 y tama�o en 1.
					System.out.println("Libro a�adido: " + SocketServidor.biblioteca.get(i));
	 
					//Le mandamos la respuesta al cliente
				    salida.println("Libro a�adido: " + SocketServidor.biblioteca.get(i));			    
				
				}else {
				    salida.println("N�mero err�neo, escribe un nuevo n�mero");			    
				    
				}
				
			}
			
			System.out.println("Cerramos el socket");
			socketAlCliente.close();
		
		//Capturo las excepciones
		} catch (IOException e) {
			System.err.println("HiloContadorLetras: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("HiloContadorLetras: Error");
			e.printStackTrace();
		}
	}
}
