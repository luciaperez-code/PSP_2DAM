package apartado1;

import java.util.Scanner;

public class PruebaMain {

	public static void main(String[] args) {

		
	Scanner sc = new Scanner(System.in);
	
	//Pido los 4 números por consola
	System.out.println("Introduzca el numero1:");
	long numero1 = sc.nextLong();
	System.out.println("Introduzca el numero2:");
	long numero2 = sc.nextLong();
	System.out.println("Introduzca el numero3:");
	long numero3 = sc.nextLong();
	System.out.println("Introduzca el numero4:");
	long numero4 = sc.nextLong();
		
	Prueba prueba1 = new Prueba(numero1);
	Prueba prueba2 = new Prueba(numero2);
	Prueba prueba3 = new Prueba(numero3);
	Prueba prueba4 =new Prueba(numero4);
	
	
	sc.close();

	}
	
}
