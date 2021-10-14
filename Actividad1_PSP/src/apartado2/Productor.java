package apartado2;


public class Productor extends Thread{

	public String nombre;
	public Cola cola;

	
	public Productor(String nombre, Cola cola){
		super();
		this.nombre = nombre;
	 	this.cola = cola;
	}
	
	public void run(){
		GeneradorEmail ge = new GeneradorEmail();
		
		for(int i = 1;i <= 10;i++){ //Genero 10 email máximo
			Email email = ge.generarEmail();
		
			try {
				Thread.sleep(500); //Tardo en añadir el mensaje a la cola 0,5 segundos
				cola.addMensaje(email); 				
				System.out.println(nombre + " ha enviado un mensaje con id: " + email.getId());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
