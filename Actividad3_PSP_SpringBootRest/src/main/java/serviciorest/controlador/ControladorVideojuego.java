package serviciorest.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import serviciorest.modelo.entidad.Videojuego;
import serviciorest.modelo.persistencia.DaoVideojuego;

//En este ejemplo vamos a realizar un CRUD completo contra la entidad Videojuego

@RestController
public class ControladorVideojuego {
	
	//Inyecto la dependencia con daoVideojuego. 
	@Autowired
	private DaoVideojuego daoVideojuego;
	
	//GET VIDEOJUEGO POR ID
	//Busco un videojuego por el ID (identificador que me pasan)
	//La URL para acceder a este metodo: "http://localhost:8080/videojuegos/ID" y  metodo GET
	@GetMapping(path="videojuegos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Videojuego> getVideojuego(@PathVariable("id") int id) {
		System.out.println("Buscando videojuego con id: " + id);
		Videojuego v = daoVideojuego.get(id);
		if(v != null) {
			System.out.println("****************Ha buscado****************");
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
		}else {
			System.out.println("******************ELSE --> No ha encontrado**********************");
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//POST --> doy de alta un videojuego (y añado yo el ID)
	//Me envían el videojuego por @RequestBody y Spring deserializa el JSON (body)
	
	//Return: videojuego creada (yo asigno ID) y el codigo de respuesta 201 CREATED
	
	//La URL para acceder a este metodo:"http://localhost:8080/videojuegos" y metodo POST
	//Pasandole el videojuego dentro del body del HTTP request
	@PostMapping(path="videojuegos",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> altaVideojuego(@RequestBody Videojuego p) {
		try {
			System.out.println("Dando de alta el objeto videojuego: " + p);
			boolean response = daoVideojuego.add(p);
			if (response==true) {
				return new ResponseEntity<Videojuego>(p,HttpStatus.CREATED);//201 CREATED
			}else {
				return new ResponseEntity<Videojuego>(p, HttpStatus.NOT_ACCEPTABLE);
			}

	    }catch(NumberFormatException ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	//GET LISTA VIDEOJUEGOS
	//Pido todos los videojuegos que tenemos almacenadas	
	//La URL para acceder: "http://localhost:8080/videojuegos" y el metodo a usar seria GET
	@GetMapping(path="videojuegos",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Videojuego>> listarVideojuegos() {
		List<Videojuego> listaVideojuegos = null;
		System.out.println("Listando los videojuegos");
		listaVideojuegos = daoVideojuego.list();			

		System.out.println(listaVideojuegos);
		return new ResponseEntity<List<Videojuego>>(listaVideojuegos,HttpStatus.OK);
	}
	
	//PUT
	//Modifico el videojuego por ID (recibido por PATH y datos en el body del HTTP)	
	//Todo correcto, devuelvo el código 200 OK y si no existe 404 NOT FOUND
	//La URL para acceder:"http://localhost:8080/videojuegos/ID" y el metodo PUT
	@PutMapping(path="videojuegos/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> modificarVideojuego(
			@PathVariable("id") int id, 
			@RequestBody Videojuego p) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + p);
		p.setId(id); //Asi evito que modifique el ID (se pone el del contador)
		Videojuego pUpdate = daoVideojuego.update(p);
		if(pUpdate != null) {
			return new ResponseEntity<Videojuego>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//DELETE
	//Borro una videojuego a traves de un ID que le pasemos en el PATH.
	//Bien: devuelvo 200 OK + videojuego borrado
	//La URL para acceder:"http://localhost:8080/videojuegos/ID" y metodo DELETE
	@DeleteMapping(path="videojuegos/{id}")
	public ResponseEntity<Videojuego> borrarVideojuego(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Videojuego p = daoVideojuego.delete(id);
		if(p != null) {
			return new ResponseEntity<Videojuego>(p,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
}
