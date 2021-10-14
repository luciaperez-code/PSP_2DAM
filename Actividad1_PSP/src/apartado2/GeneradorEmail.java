package apartado2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GeneradorEmail {

	
	public Email generarEmail() {
		Email email = new Email();
		email.setId(generarId());
		email.setDestinatario(generarDestinatario());
		email.setRemitente(generarRemitente());
		email.setAsunto(generarAsunto());
		email.setCuerpoMensaje(generarCuerpoMensaje());
		return email;
	}
	
	//Genero de forma automática todos los campos del Email
	public String generarId() {
		List<String> listaId= new ArrayList<String>();
		listaId.add("123A");
		listaId.add("456B");
		listaId.add("789C");
		listaId.add("135D");
		listaId.add("246E");
		listaId.add("789F");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		return listaId.get(numero);
	}
	
	public String generarDestinatario() {
		List<String> listaNombres = new ArrayList<String>();
		listaNombres.add("asterix@gmail.com");
		listaNombres.add("lorem@ipsum.es");
		listaNombres.add("mortadelo@filemon.es");
		listaNombres.add("felix@elgato.com");
		listaNombres.add("pikachu@gmail.com");
		listaNombres.add("chiquito@delacalzada.com");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		return listaNombres.get(numero);
	}
	
	public String generarRemitente() {
		List<String> listaNombres = new ArrayList<String>();
		listaNombres.add("Asterix");
		listaNombres.add("Obelix");
		listaNombres.add("Mortadelo");
		listaNombres.add("Felix del gato");
		listaNombres.add("Nino Bravo");
		listaNombres.add("Chiquito de la Calzada");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		return listaNombres.get(numero);
	}
	
	public String generarAsunto() {
		List<String> listaAsuntos = new ArrayList<String>();
		listaAsuntos.add("Trabajo");
		listaAsuntos.add("Incidencia");
		listaAsuntos.add("Urgente");
		listaAsuntos.add("Proyecto 2");
		listaAsuntos.add("Entrevista");
		listaAsuntos.add("Petición");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		return listaAsuntos.get(numero);
	}
	
	public String generarCuerpoMensaje() {
		List<String> listaCuerpos = new ArrayList<String>();
		listaCuerpos.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
		listaCuerpos.add("Nulla sit amet augue aliquet, sagittis velit vel, rhoncus nisl. ");
		listaCuerpos.add("Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
		listaCuerpos.add("Duis tristique lacus ac eleifend rutrum.");
		listaCuerpos.add("Vivamus tristique libero orci, id posuere erat posuere vitae.");
		listaCuerpos.add("Praesent eleifend tincidunt venenatis. ");
		
		int numero = ThreadLocalRandom.current().nextInt(0, 6);
		return listaCuerpos.get(numero);
	}


	
	
}
