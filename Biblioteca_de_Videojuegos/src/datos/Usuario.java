package datos;

import java.util.List;

public class Usuario {

	private String nombre;
	private String apellido;
	private String password;
	private String fecha_naci;
	private String email;
	private String pais;
	private int numeroDeJuegos;
	private double saldo;
	private List<Juego> juegosComprados;
	
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
	}
	
	public Usuario(String nombre, String apellido, String password, String fecha_naci, 
			String email, String pais, int numeroDeJuegos, double saldo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.fecha_naci = fecha_naci;
		this.email = email;
		this.pais = pais;
		this.numeroDeJuegos = numeroDeJuegos;
		this.saldo = saldo;
	}   
	
	public Usuario(String nombre, String apellido, String password, String fecha_naci, 
			String email, String pais, int numeroDeJuegos, double saldo, List<Juego> juegosComprados) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.fecha_naci = fecha_naci;
		this.email = email;
		this.pais = pais;
		this.numeroDeJuegos = numeroDeJuegos;
		this.saldo = saldo;
		this.juegosComprados= juegosComprados;
	}   

	public int getNumeroDeJuegos() {
		return numeroDeJuegos;
	}

	public void setNumeroDeJuegos(int numeroDeJuegos) {
		this.numeroDeJuegos = numeroDeJuegos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Juego> getJuegosComprados() {
		return juegosComprados;
	}
	
	public void setJuegosComprados(int index, Juego juegosComprados) {
		this.juegosComprados.add(index, juegosComprados);
	}
	
	public void addJuegosComprados(Juego juegosComprados) {
		this.juegosComprados.add(juegosComprados);
	}

	public String getFecha_naci() {
		return fecha_naci;
	}

	public void setFecha_naci(String fecha_naci) {
		this.fecha_naci = fecha_naci;
	}
}
