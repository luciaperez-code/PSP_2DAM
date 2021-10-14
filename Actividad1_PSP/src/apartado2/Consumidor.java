package apartado2;


public class Consumidor extends Thread{
	
		public String nombre;
		public Cola cola;
		
		public Consumidor(String nombre, Cola cola){
			super();
			this.nombre = nombre;
			this.cola = cola;
		}
		
		public void run(){
			while(true) {//leemos por siempre mensajes, el programa nunca se parara
			//for(int i = 1;i <= 10;i++){
				Email email = cola.getMensaje();
				System.out.println(nombre + " ha enviado el mensaje: " + email + ", que ha sido consumido por el hilo " + Thread.currentThread().getName());
			}
		}

	}


