package com.example.demo.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

import org.springframework.stereotype.Component;


@Component
public class Fichero {

	private final String NOMBRE_FICHERO = "memoria.txt";
	private String[]listaFrases = new String[40];
	private File fichero;
	public Fichero() {
		fichero = new File(NOMBRE_FICHERO);
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
				System.out.println("El fichero ha sido creado.");
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
	public String[] leerFichero() {
		try(FileReader fr = new FileReader(NOMBRE_FICHERO);
				 BufferedReader br = new BufferedReader(fr);) {
				String frase = br.readLine();
				int i = 0;
				listaFrases[i] = frase;
				while(frase != null){
					System.out.println("Frase del fichero: " + frase);
					i++;
					frase = br.readLine();
					listaFrases[i] = frase;
					
				}
			
			return listaFrases;
			
		}catch ( IOException ioe) {
			ioe.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Método que escribe la frase que pasamos como parámetro en el fichero
	 * @param frase Frase que recibe para escrbirla
	 * @return Devuelve true si pudo escrbir en el fichero y false si ocurrió un error
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
	 * Método que cuenta el número de frases en las que aparece una palabra 
	 * @param palabra la palabra a buscar
	 * @return devuelve el número de frases en las que aparece dicha palabra. También devuelve 0 si hay una excepción
	 */
	public int contar(String palabra) {
		try(FileReader fr = new FileReader(fichero);
				BufferedReader br = new BufferedReader(fr)){
			
			String linea="";
			int contador=0;
			
			
			//Se ejecuta mientras haya una línea que leer
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












