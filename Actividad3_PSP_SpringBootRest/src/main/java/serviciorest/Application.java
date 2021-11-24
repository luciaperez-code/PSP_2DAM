package serviciorest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Clase: punto de arranque de nuestra aplicacion. 
@SpringBootApplication
public class Application {	
	
	public static void main(String[] args) {
		System.out.println("Servicio Rest -> Cargando el contexto de Spring...");
		
		//Metodo "run": arranca el contexto de Spring, da de alta objetos y sus dependencias
		SpringApplication.run(Application.class, args);
		System.out.println("Servicio Rest -> Contexto de Spring cargado!");
	}
}
