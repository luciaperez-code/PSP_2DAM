package com.example.demo.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class Fichero {

	private final String NOMBRE_FICHERO = "memoria.txt";
	//private String[]listaFrases = new String[40];
	private File fichero;
	public Fichero() {
		fichero = new File(NOMBRE_FICHERO);
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
				System.out.println("Fichero creado con éxito");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Metodo que devuelve todos los String almacenados. Para pruebas
	 * @param frase 
	 * @return devuelve un array con todas las frases leidas
	 * @throws FileNotFoundException
	 */
	public List<String> leerFichero() {
		List<String> listaFrases = new ArrayList();

		try(FileReader fr = new FileReader(NOMBRE_FICHERO);
				 BufferedReader br = new BufferedReader(fr);) {
				String frase = br.readLine();
				int i = 0;
				
				
				while(frase != null){
					listaFrases.add(frase);
					frase = br.readLine();
					}
					System.out.println("Fichero leido correctamente");
			
			return listaFrases;
		
		}catch ( IOException ioe) {
			ioe.printStackTrace();
			return null;
		} 
	}
	/**
	 * MÃ©todo que escribe la frase que pasamos como parÃ¡metro en el fichero
	 * @param frase Frase que recibe para escrbirla
	 * @return Devuelve true si pudo escrbir en el fichero y false si ocurriÃ³ un error
	 * @throws FileNotFoundException
	 */
	public boolean escribir(String frase) throws FileNotFoundException {
		try(FileWriter fw = new FileWriter(NOMBRE_FICHERO, true);
				BufferedWriter pw = new BufferedWriter(fw);) {
			//Si ponemos (nombreFichero,true) add en vez de borrar
			pw.write(frase);
			pw.newLine();
			//pw.flush();
			System.out.println("Frase escrita: "+frase);
			return true;
		}catch(IOException ioe) {
			ioe.printStackTrace();
			System.out.println("No se pudo escribir la frase");
			return false;
		}
	}
	/**
	 * MÃ©todo que cuenta el nÃºmero de frases en las que aparece una palabra 
	 * @param palabra la palabra a buscar
	 * @return devuelve el nÃºmero de frases en las que aparece dicha palabra. TambiÃ©n devuelve 0 si hay una excepciÃ³n
	 */
	public int contar(String palabra) {
		try(FileReader fr = new FileReader(fichero);
				BufferedReader br = new BufferedReader(fr)){
			
			String linea="";
			int contador=0;
			
			
			//Se ejecuta mientras haya una lÃ­nea que leer
			while((linea=br.readLine()) != null) {
				
				String sinMayusculas = linea.toLowerCase();
				//Sustituye los caracteres con acentos por los mismos sin acentos
				String normalize = Normalizer.normalize(sinMayusculas	, Normalizer.Form.NFD);
				String sinAcentos = normalize.replaceAll("[^\\p{ASCII}]","");
				
				if(sinAcentos.contains(palabra)) {
					contador++;
				}
			}
			System.out.println("Numero de ocurrencias: " + contador);
			
			return contador;
			
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("No se pudo leer el archivo");
			return 0;
			}
		}
	
	
	
	
	
}


















