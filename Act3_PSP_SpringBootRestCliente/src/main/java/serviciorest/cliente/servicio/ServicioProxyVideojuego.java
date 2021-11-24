package serviciorest.cliente.servicio;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import serviciorest.cliente.entidad.Videojuego;

//Doy de alta un objeto ServicioProxyVideojuego en el contexto de Spring
@Service
public class ServicioProxyVideojuego {

	//La URL base del servicio REST de videojuegos
	public static final String URL = "http://localhost:8080/videojuegos/";
	
	//Inyectamos el objeto de tipo RestTemplate (peticiones HTTP al servicio REST)
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Metodo que recibe un id y obtiene un videojuego del servicio REST
	 * @param id que queremos obtener
	 * @return retorna el videojuego encontrado y si no lo encuentra 404
	 */
	public Videojuego obtener(int id){
		try {
			//Como el servicio trabaja con objetos ResponseEntity, nosotros tambien podemos hacerlo en el cliente
			//Ej http://localhost:8080/videojuegos/1 GET
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs= re.getStatusCode();
			if(hs == HttpStatus.OK) {
				//Si el videojuego existe, vendrá en el body del HTTP en formato JSON
				//Objeto ResponseEntity de tipo Videojuego => Spring convierte el body a tipo Videojuego 
				System.out.println("El videojuego encontrado con id: " + id + " es: " + re.getBody());
				return re.getBody();
			}else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("Obtener -> No se ha encontrado ningun videojuego con ID: " + id);
		    System.out.println("Obtener -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	/**
	 * Metodo que da de alta un videojuego en el servicio REST
	 * @param v el videojuego que vamos a dar de alta
	 * @return el videojuego con el id actualizado que se ha dado de alta en servicio Rest. No alta = null.
	 */
	public Videojuego alta(Videojuego v){
		try {
			//Metodo postForEntity (URL, videojuego del body, tipo de objeto que nos envia)
			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, v, Videojuego.class);
			System.out.println("Alta -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("Alta -> Videojuego NO dado de alta, id: " + v);
		    System.out.println("Alta -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	/**
	 * Modifica un videojuego en el servicio REST
	 * @param v el videojuego que queremos modificar a partir del ID
	 * @return modificado = true, no modificado = false
	 */
	public boolean modificar(Videojuego v){
		try {
			//PUT: si modifica no devuelve nada y si salta una excepción sale el error
			restTemplate.put(URL + v.getId(), v, Videojuego.class);
			System.out.println("Modificar -> El videojuego con ID " + v.getId() +" ha sido modificado con éxito --> " + v);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("Modificar -> El videojuego NO se ha modificado, id: " + v.getId());
		    System.out.println("Modificar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	/**
	 * Borra un videojuego en el servicio REST
	 * @param id el id del videojuego que quiero borrar.
	 * @return si puedo borrar el videojuego true, si no puedo false
	 */
	public boolean borrar(int id){
		try {
			//El metodo delete tampoco devuelve nada, si no borra = excepcion
			restTemplate.delete(URL + id);
			System.out.println("Borrar -> El videojuego con ID " + id +" ha sido borrado con éxito");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("Borrar -> El videojuego NO con ID " + id +" no se ha borrado :(");
		    System.out.println("Borrar -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	/**
	 * Metodo que devuelve todas los videojuegos
	 * @return el listado de los videojuegos segun el parametro de entrada o null si hay error.
	 */
	public List<Videojuego> listar(){

		try {
			ResponseEntity<Videojuego[]> response =
					  restTemplate.getForEntity(URL,Videojuego[].class);
			Videojuego[] arrayVideojuegos = response.getBody();
			return Arrays.asList(arrayVideojuegos);//convertimos el array en un arraylist
		} catch (HttpClientErrorException e) {
			System.out.println("Listar -> Error al obtener la lista de videojuegos");
		    System.out.println("Listar -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
}
