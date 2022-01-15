package com.example.demo.controlador;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Fichero;

@RestController
public class Controlador {

	@Autowired
	private Fichero fichero;
	
	
	//MÃ©todo Get de prueba
	@GetMapping (path= "fichero", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> listarFrase(){
		List<String> frase = null;
		frase= fichero.leerFichero();
		
		return new ResponseEntity<List<String>>(frase, HttpStatus.OK);
	}
	
	@PostMapping(path="fichero", consumes= MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> altaFrase(@RequestBody String frase){
		try {
			boolean respuesta = fichero.escribir(frase);
			if(respuesta == true) {
				return new ResponseEntity<String>(frase, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(frase, HttpStatus.NOT_ACCEPTABLE);
			}
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping (path="fichero/{palabra}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> buscar(@PathVariable("palabra") String palabra){
		
		System.out.println("Buscando la palabra: "+ palabra);
		int contador = fichero.contar(palabra);
		
		return new ResponseEntity<Integer>(contador, HttpStatus.OK);
			
	}
}


















