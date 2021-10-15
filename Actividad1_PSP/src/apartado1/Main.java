package apartado1;

import java.util.Scanner;

public class Main {

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
		
	NumerosPrimos prueba1 = new NumerosPrimos(numero1);
	NumerosPrimos prueba2 = new NumerosPrimos(numero2);
	NumerosPrimos prueba3 = new NumerosPrimos(numero3);
	NumerosPrimos prueba4 =new NumerosPrimos(numero4);
	
	
	sc.close();

	}
	
}
