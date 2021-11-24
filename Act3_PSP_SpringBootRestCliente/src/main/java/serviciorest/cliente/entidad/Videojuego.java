package serviciorest.cliente.entidad;

import org.springframework.stereotype.Component;

@Component
public class Videojuego{
	
	private int id;
	private String nombre;
	private String compañia;
	private double nota;
	
	public Videojuego() {
		super();
	}	

	public Videojuego(int id, String nombre, String compañia, double nota) {
		this.id = id;
		this.nombre = nombre;
		this.compañia = compañia;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCompañia() {
		return compañia;
	}
	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}

	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", nombre=" + nombre + ", compañia=" + compañia + ", nota=" + nota + "]";
	}

	
}
