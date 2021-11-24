package serviciorest.cliente;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Videojuego;
import serviciorest.cliente.servicio.ServicioProxyVideojuego;

@SpringBootApplication
public class Application implements CommandLineRunner { // Implemento CommandLineRunner para sobrescribir el método run
														// y acceder a objetos dinámicos

	// Inyectaremos todos los objetos que necesitamos para acceder al ServicioRest
	@Autowired
	private ServicioProxyVideojuego spv;

	// Digo a Spring que nos inyecte su propio contexto
	@Autowired
	private ApplicationContext context;

	// Objeto de tipo RestTemplate, lo usarán el ServicioProxy para hacer peticiones HTTP al servicio Rest
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Metodo main que lanza la aplicacion
	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(Application.class, args);
	}

	// Método run es dinámico => puedo acceder a atributos dinámicos
	@Override
	public void run(String... args) throws Exception {
		System.out.println("****** Arrancando el cliente REST ******");

		try (Scanner sc = new Scanner(System.in)) {

			boolean continuar = true;

			do {
				System.out.println("Menú del servidor de videojuegos:\n" + "1- Dar de alta un videojuego\r\n"
					+ "2- Dar de baja un videojuego por ID\r\n" + "3- Modificar un videojuego por ID\r\n"
					+ "4- Obtener un videojuego por ID\r\n" + "5- Listar todos los videojuegos\r\n" + "6- Salir");
				String opcionMenu = sc.nextLine();// Numero de opción que hemos introducido por consola
				
				try{
					switch (opcionMenu) {
					case "1":
						Videojuego videojuego = new Videojuego();
		
						System.out.println("Escriba el nombre del videojuego a dar de alta: ");
						String nombre = sc.nextLine();
						videojuego.setNombre(nombre);
		
						System.out.println("Escriba la compañía del videojuego a dar de alta: ");
						String compañia = sc.nextLine();
						videojuego.setCompañia(compañia);
		
						System.out.println("Escriba la nota del videojuego a dar de alta: ");
						String nota = sc.nextLine();
						videojuego.setNota(Double.parseDouble(nota));
		
						Videojuego vAlta = spv.alta(videojuego);
						System.out.println("Run -> Videojuego dado de alta " + vAlta);
						break;
		
					case "2":
						System.out.println("Escriba el ID del videojuego a borrar: ");
						String idStringBor = sc.nextLine();
						spv.borrar(Integer.parseInt(idStringBor));
						break;
		
					case "3":
						Videojuego videojuegoMod = new Videojuego();
						System.out.println("Escriba el ID del videojuego a modificar: ");
						String idStringMod = sc.nextLine();
						videojuegoMod.setId(Integer.parseInt(idStringMod));
						
						System.out.println("Escriba el nombre del videojuego a modificar: ");
						String nombreMod = sc.nextLine();
						videojuegoMod.setNombre(nombreMod);
		
						System.out.println("Escriba la compañía del videojuego a modificar: ");
						String compañiaMod = sc.nextLine();
						videojuegoMod.setCompañia(compañiaMod);
		
						System.out.println("Escriba la nota del videojuego a modificar: ");
						String notaMod = sc.nextLine();
						videojuegoMod.setNota(Double.parseDouble(notaMod));
						spv.modificar(videojuegoMod);
						break;
		
					case "4":
						System.out.println("Escriba el ID del videojuego a leer: ");
						String idStringLeer = sc.nextLine();
							spv.obtener(Integer.parseInt(idStringLeer));
						break;

					case "5":
						spv.listar();
						List<Videojuego> listaVideojuegos = spv.listar();
						// Recorremos la lista y la imprimimos con funciones lambda
						// Tambien podriamos haber usado un for-each clasico de java
						listaVideojuegos.forEach((v) -> System.out.println(v));
						break;
						
					case "6":
						continuar=false;
						break;
					
					default:
						System.out.println("Petición incorrecta");
						break;
					}
					
				//Capturo posibles excepciones en la entrada de datos por consola
				}catch(NumberFormatException ex) {
			        System.out.println("Formato no válido, recuerde que ID y nota son números ");
			        System.out.println(ex.getMessage());
			    } catch (Exception e) {
					e.printStackTrace();
				}
				
			}while(continuar);
			
			
			System.out.println("******************************************");
			System.out.println("******** Parando el cliente REST *********");
			// Mandamos parar nuestra aplicacion Spring Boot
			pararAplicacion();
			

			// Capturo las posibles excepciones
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que para el servidor web cuando lo llamamos.
	 */
	public void pararAplicacion() {
		SpringApplication.exit(context, () -> 0);
	}

}
