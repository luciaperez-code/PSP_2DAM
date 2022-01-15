package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Actividad1Application {

	public static void main(String[] args) {
		
		
		System.out.println("Cargando contexto Spring------->");
		SpringApplication.run(Actividad1Application.class, args);
		
		System.out.println("Contexto Spring cargado.");
	
	}

}
