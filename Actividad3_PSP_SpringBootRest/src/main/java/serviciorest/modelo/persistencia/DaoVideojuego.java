package serviciorest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import serviciorest.modelo.entidad.Videojuego;

/**
 * Patron DAO (Data Access Object): objeto que se encarga de hacer las consultas
 * a algun motor de persistencia (BBDD, Ficheros, etc). Simulo una BBDD
 * 
 */
@Component
public class DaoVideojuego {

	public List<Videojuego> listaVideojuegos;
	public int contador;
	
	/**
	 * Cuando se cree el objeto dentro del contexto de Spring, se ejecuta este constructor que
	 * crea los 5 videojuegos precargados en una lista
	 */
	public DaoVideojuego() {
		System.out.println("DaoVideojuego -> Creando la lista de videojuegos!");
		listaVideojuegos = new ArrayList<Videojuego>();
		Videojuego p1 = new Videojuego(contador++,"Mario Kart", "Ferrari", 10);//ID: 0
		Videojuego p2 = new Videojuego(contador++,"F1", "Fernando Alonso", 9);//ID: 1
		Videojuego p3 = new Videojuego(contador++,"Nintendogs", "Animalistas", 6);//ID: 2
		Videojuego p4 = new Videojuego(contador++,"Animal Crossing", "Tom Nook", 8);//ID:3
		Videojuego p5 = new Videojuego(contador++,"Sims", "EA", 7);//ID:4
		listaVideojuegos.add(p1);
		listaVideojuegos.add(p2);
		listaVideojuegos.add(p3);
		listaVideojuegos.add(p4);
		listaVideojuegos.add(p5);
	}
	
	/**
	 * Devuelve un videojuego a partir de su posicion del array
	 * @param posicion la posicion del array que buscamos
	 * @return el videojuego que ocupe en la posicion del array (no existe o fuera = null)
	 */
	public Videojuego get(int posicion) {
		try {
			return listaVideojuegos.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Videojuego fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que devuelve todos los videojuegos del array
	 * @return una lista con todos los videojuegos del array
	 */
	public List<Videojuego> list() {
		return listaVideojuegos;
	}
	
	/**
	 * Metodo que introduce un videojuego al final de la lista
	 * El id no puede ser el mismo porque lo asigno yo y va por contador
	 * @param v el videojuego que queremos introducir
	 */
	public boolean add(Videojuego v) {
		List<Videojuego> listaVideojuegosAux = new ArrayList<Videojuego>();
		for(int i=0; i<listaVideojuegos.size();i++){
			if(listaVideojuegos.get(i).getNombre().equalsIgnoreCase(v.getNombre())) {//contains()
				listaVideojuegosAux.add(v);
			}
		}
		if (listaVideojuegosAux.isEmpty() == true) {
			v.setId(contador++);
			listaVideojuegos.add(v);
			return true;
		}else{
			return false;
		}

	}
	
	/**
	 * Borramos un videojuego de una posicion del array
	 * @param posicion la posicion a borrar
	 * @return devuelvo el videojuego que he eliminado del array (no existe = null)
	 */
	public Videojuego delete(int posicion) {
		try {
			return listaVideojuegos.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Delete -> Videojuego fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que modifica un videojuego de una posicion del array
	 * @param p contiene todos los datos que queremos modificar, pero 
	 * p.getId() contiene la posicion del array que queremos eliminar
	 * @return videojuego modificado (no existe = null)
	 */
	public Videojuego update(Videojuego v) {
		try {
			Videojuego vAux = listaVideojuegos.get(v.getId());
			vAux.setNombre(v.getNombre());
			vAux.setCompañia(v.getCompañia());
			vAux.setNota(v.getNota());

			return vAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Update -> Videojuego fuera de rango");
			return null;
		}
	}
	
}
