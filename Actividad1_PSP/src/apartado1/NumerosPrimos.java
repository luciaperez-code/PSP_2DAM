package apartado1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NumerosPrimos extends Thread{
		
	private long numeroConsola;
	
	public NumerosPrimos(long numeroConsola) {
		this.numeroConsola = numeroConsola;
		Thread t1 = new Thread(this);
		t1.start();
		}
		

	
	@Override 
	public void run() {
		System.out.println("Arrancando hilo: " + Thread.currentThread().getName());
//		Date date = new Date();
//		int contador = 0;
//		
//		while (contador < 2) {
//			for (int i = 1; i <=numeroConsola;i++) {
//				if (numeroConsola%i == 0) {
//					contador++;
//				}			
//			}
//		}
//		
//		if (contador <=2) {
//			//System.out.println("El número " + numeroConsola + " es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + date.getTime() + "segundos");
//			System.out.println("El número " + numeroConsola + " es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + TimeUnit.NANOSECONDS.toSeconds(date.getTime()) + "segundos");
//		}else {
//		System.out.println("El número " + numeroConsola + " es NO es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + date.getTime() + "segundos");	
//		}
		
		long startTime = System.currentTimeMillis();
		
		int comprobar=0;
		
		
		
		long endTime = System.currentTimeMillis() - startTime;
		
		
		if(numeroConsola == 2) { //Descartamos primero que el numero introducido sea el 2
			System.out.println("Tu número (" + numeroConsola+ ")  es primo. Número procesado por el " + currentThread().getName() + " y ha tardado " + (System.currentTimeMillis()-startTime) + " milisegundos");
		}else if(numeroConsola%2 == 0) { //Aquí descartamos todos los números pares
			System.out.println("Tu número (" + numeroConsola+ ")  no es primo. Número procesado por el " + currentThread().getName()+ " y ha tardado " + (System.currentTimeMillis()-startTime) + " milisegundos" );
		}else {
			long limite = (numeroConsola + 1) /2; //Para hallar un número "n" mediante muliplicación de la forma n=m*p y teniendo en cuenta de que usamos solo números enteros para el valor más pequeño de p (2) "m" toma el valor de n/2. Por lo tanto y puesto que ya hemos descartado que sea divisible por dos, solo debemos analizar los números impares hasta n/2
			for(int i = 3; i<limite; i+=2) {
				long resto = numeroConsola % i;
				
				if(resto== 0) {
					comprobar++;
				}
				
			}
			
		if( numeroConsola == 1) {
				System.out.println("Tu número (" + numeroConsola+ ") es primo. Número procesado por el " + Thread.currentThread().getName() + " y ha tardado " + (System.currentTimeMillis()-startTime) + " milisegundos");
		}else if(comprobar == 0) {
				System.out.println("Tu número (" + numeroConsola+ ")  es primo. Número procesado por el " + Thread.currentThread().getName() + " y ha tardado " + (System.currentTimeMillis()-startTime) + " milisegundos");
		}else {
				System.out.println("Tu número  (" + numeroConsola+ ")  no es primo. Número procesado por el " + Thread.currentThread().getName() + " y ha tardado " + (System.currentTimeMillis()-startTime) + " milisegundos");
			}
			
		}
		
		
		
		
		
	}



}