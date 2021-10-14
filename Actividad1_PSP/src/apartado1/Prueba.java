package apartado1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Prueba extends Thread{
		
	private long numeroConsola;
	
	public Prueba(long numeroConsola) {
		this.numeroConsola = numeroConsola;
		Thread t1 = new Thread(this);
		t1.start();
		}
		

	
	@Override 
	public void run() {
		System.out.println("Arrancando hilo: " + Thread.currentThread().getName());
		Date date = new Date();
		int contador = 0;
		
		while (contador < 2) {
			for (int i = 1; i <=numeroConsola;i++) {
				if (numeroConsola%i == 0) {
					contador++;
				}			
			}
		}
		
		if (contador <=2) {
			//System.out.println("El número " + numeroConsola + " es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + date.getTime() + "segundos");
			System.out.println("El número " + numeroConsola + " es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + TimeUnit.NANOSECONDS.toSeconds(date.getTime()) + "segundos");
		}else {
		System.out.println("El número " + numeroConsola + " es NO es un número primo que ha sido procesado por el hilo: " + Thread.currentThread().getName() + " ha tardado " + date.getTime() + "segundos");	
		}
	}



}




