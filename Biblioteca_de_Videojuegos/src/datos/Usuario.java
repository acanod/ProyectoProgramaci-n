package datos;

public class Usuario {

	private String nombre;
	private String apellido;
	private String password;
	private String email;
	private String pais;
	private int numeroDeJuegos;
	private double saldo;
	
	public Usuario(String nombre, String apellido, String password, String email, String pais, int numeroDeJuegos, double saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
		this.pais = pais;
		this.numeroDeJuegos = numeroDeJuegos;
		this.saldo = saldo;
	}  
	
	public Usuario(String nombre, String password) {
		this.nombre = nombre;
		this.password = password;
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
	
}
