package datos;

public class Juego {
	
	private String nombre;
	private int edadnecesaria;
	private String categoria;
	private double precio;
	private boolean prestamo;
	
	public Juego() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Juego(String nombre, int edadnecesaria, String categoria, double precio, boolean prestamo) {
		super();
		this.nombre = nombre;
		this.edadnecesaria = edadnecesaria;
		this.categoria = categoria;
		this.precio = precio;
		this.prestamo = prestamo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdadnecesaria() {
		return edadnecesaria;
	}
	
	public void setEdadnecesaria(int edadnecesaria) {
		this.edadnecesaria = edadnecesaria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public boolean isPrestamo() {
		return prestamo;
	}
	
	public void setPrestamo(boolean prestamo) {
		this.prestamo = prestamo;
	}

}