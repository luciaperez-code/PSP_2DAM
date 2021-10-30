package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class SocketCliente {
	
	// IP y Puerto a la que nos vamos a conectar
	public static final int PUERTO = 2018;
	public static final String IP_SERVER = "localhost";
	
	public static void main(String[] args) {
		System.out.println("        APLICACIÓN CLIENTE         ");
		System.out.println("-----------------------------------");

		InetSocketAddress direccionServidor = new InetSocketAddress(IP_SERVER, PUERTO);
		
		try (Scanner sc = new Scanner(System.in)){
						
			System.out.println("CLIENTE: Esperando a que el servidor acepte la conexión");
			Socket socketAlServidor = new Socket();
			socketAlServidor.connect(direccionServidor);
			System.out.println("CLIENTE: Conexion establecida... a " + IP_SERVER + 
					" por el puerto " + PUERTO);

			InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
			BufferedReader entradaBuffer = new BufferedReader(entrada);
			
			PrintStream salida = new PrintStream(socketAlServidor.getOutputStream());
			
			String opcionMenu = "";
			String texto = "";
			boolean continuar = true;
			
			do {
				System.out.println("CLIENTE: Escriba el número de la opción que desee realizar: "
						+ "\n1 = Consultar libro por ISBN \n2 = Consulta libro por título \n3 = Consulta libro por autor \n4 = Salir de la aplicación");
				opcionMenu = sc.nextLine();//Número de opción que ha elegido el cliente
				
				if (opcionMenu.equalsIgnoreCase("1")) {
					System.out.println("Escriba el ISBN a buscar: ");
					texto = sc.nextLine();
					
				}else if(opcionMenu.equalsIgnoreCase("2")) {
					System.out.println("Escriba el título del libro a buscar: ");
					texto = sc.nextLine();
					
				} else if(opcionMenu.equalsIgnoreCase("3")) {
					System.out.println("Escriba el autor a buscar: ");
					texto = sc.nextLine();
					
				} else if(opcionMenu.equalsIgnoreCase("4")) {
					texto = null;
					
				}else {
					System.out.println("Número erróneo, vuelva a escribir un número válido");
				}
				
				
				salida.println(opcionMenu+"-"+texto); //Envío el número al servidor
				
				System.out.println("CLIENTE: Esperando respuesta ...... ");				
				String respuesta = entradaBuffer.readLine();
								
				if("FIN".equalsIgnoreCase(respuesta)) {
					continuar = false;
				}else {
					System.out.println("Gestión procesada con éxito, continuamos");
				}				
			}while(continuar);
			//Cerramos la conexion
			socketAlServidor.close();
			
		//Capturo las posibles excepciones
		} catch (UnknownHostException e) {
			System.err.println("CLIENTE: No encuentro el servidor en la dirección" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("CLIENTE: Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
		System.out.println("CLIENTE: Fin del programa");
	}
	
}
