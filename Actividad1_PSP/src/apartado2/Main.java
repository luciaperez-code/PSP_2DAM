package apartado2;

public class Main {
	public static void main(String[] args) {
		
		Cola cola = new Cola();
		
		Productor p1 = new Productor("Productor 1",cola);
		Productor p2 = new Productor("Productor 2", cola);
		Productor p3 = new Productor("Productor 3", cola);
		
		Consumidor c1 = new Consumidor("Consumidor 1", cola);
		Consumidor c2 = new Consumidor("Consumidor 2", cola);
		
		p1.start();
		p2.start();
		p3.start();
		
		c1.start();
		c2.start();
		
	}
}
