package apartado2;

import java.util.LinkedList;
import java.util.Queue;

public class Cola {
	
	//Numero maximo de strings que acepto en mi cola
	public final static int MAX_ELEMENTOS = 5;
	
	private Queue<Email> cola = new LinkedList<>();
	
	
	public synchronized void addMensaje(Email mensaje){
		if (mensaje.getDestinatario() == "pikachu@gmail.com") { //Apartado 3
			System.out.println("El email con id: " + mensaje.getId() + " y con destinatario: " + mensaje.getDestinatario() + " no es válido");
		} else {
			//Si la cola esta llena no debemos introducir ningun elemento más
		while(cola.size() == MAX_ELEMENTOS){//3
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		cola.offer(mensaje);
		notify();
		}
		
	}
	
	public synchronized Email getMensaje(){
		while(cola.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Sacamos un elemento de la cola
		Email s = cola.poll();
		notify();
		return s;
	}
	
}

