package servidor;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import libro.Libro;

//Abrimos un hilo por cada petición que reciba el servidor (así puede procesar varias peticiones simultáneas de diferentes clientes)

public class SocketServidor {
	
	public static final int PUERTO = 2018;
	
	//Creo mi biblioteca
	public static ArrayList<Libro> biblioteca = new ArrayList<Libro>(); 

	
	public static void main(String[] args) {
		
	
	//Creo los 5 libros predefinidos
	biblioteca.add(new Libro("1001", "Don Quijote", "Miguel de Cervantes", 19.90));
	biblioteca.add(new Libro("1002", "Caperucita Roja", "Anónimo", 10.90));
	biblioteca.add(new Libro("1003", "El lazarillo de Tormes", "Anónimo", 12.90));
	biblioteca.add(new Libro("1004", "El buscón", "Quevedo", 9.90));
	biblioteca.add(new Libro("1005", "La Celestina", "Fernando de Rojas", 14.90));
		
		
		System.out.println("      APLICACIÓN DE SERVIDOR DE UNA BIBLIOTECA     ");
		System.out.println("------------------------------------------------------------");		
		
		int peticion = 0;
		
		try (ServerSocket servidor = new ServerSocket()){			
			InetSocketAddress direccion = new InetSocketAddress(PUERTO);
			servidor.bind(direccion);

			System.out.println("SERVIDOR: Esperando peticion por el puerto " + PUERTO);
			
			while (true) {
				//Por cada peticion de cliente aceptada se me crea un objeto socket diferente
				Socket socketAlCliente = servidor.accept();
				System.out.println("SERVIDOR: peticion numero " + ++peticion + " recibida");
				//Hilo nuevo --> libero al hilo principal (multi cliente). 
				new HiloLibros(socketAlCliente);
			}		
			
		//Capturo los posibles errores
		} catch (IOException e) {
			System.err.println("SERVIDOR: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("SERVIDOR: Error");
			e.printStackTrace();
		}
	}
}

