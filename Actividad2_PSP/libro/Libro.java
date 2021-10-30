package libro;

public class Libro {
	public String isbn;
	public String titulo;
	public String autor;
	public double precio;
	
	public Libro(String isbn, String titulo, String autor, double precio) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor=autor;
		this.precio=precio;
	}
	
	
	//Getter and setter
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	//toString
		@Override
	public String toString() {
		return "Libro --> su ISBN es " + isbn + ", su titulo " + titulo + ", su autor " + autor + " y su precio es " + precio + "€";
	}
		
	
}
