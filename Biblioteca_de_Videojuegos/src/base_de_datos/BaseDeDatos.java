package base_de_datos;

import java.sql.Connection;
import java.sql.Date;

import datos.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDeDatos {

	public static ResultSet rs;
	private static Statement statement;
	private static Connection connection = null;
	private static Logger logger = null;
	
	/**
	 * El servidor se conecta a la base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	public static void conectarBD() {
		try {
			Class.forName("org.postgresql.Driver"); 
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/VideoJuegos", "postgres", "postgres");
			Statement statement = connection.createStatement();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuario (nombreUsuario VARCHAR(35) PRIMARY KEY NOT NULL, apellido CHAR(20), password VARCHAR(30) NOT NULL, fecha_naci DATE, email CHAR(20),"
					+ " pais CHAR(35), numero_juegos INT, saldo REAL, admin BOOLEAN);");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS juego (nombreJuego VARCHAR(120) PRIMARY KEY NOT NULL, edad_necesaria INT, categoria CHAR(20) NOT NULL, precio INT,"
					+ " prestamo BOOLEAN);");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS amigos (nombre VARCHAR(35) NOT NULL, amigo VARCHAR(120) NOT NULL, FOREIGN KEY (nombre) REFERENCES usuario (nombreUsuario), FOREIGN KEY (amigo) REFERENCES usuario (nombreUsuario));");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS biblioteca (nombreUsuario VARCHAR(35), nombreJuego VARCHAR(120), FOREIGN KEY (nombreUsuario) REFERENCES usuario (nombreUsuario), FOREIGN KEY (nombreJuego) REFERENCES juego (nombreJuego));");
			
			log(Level.INFO, "Base de datos conectada", null);
		} catch(ClassNotFoundException | SQLException e) {
			log(Level.SEVERE, "Error en conexión de base de datos", e);
		}
	}

	/**
	 * Se cierra la conexion con la base de datos
	 * @throws Exception
	 */
	public static void cerrarConexion() {
		if (rs!=null) {
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		log(Level.INFO, "Base de datos cerrada", null);
	}
	
	/**
	 * Inserta un usuario en la base de datos
	 * @param usuario
	 * @return True si ha sido insertado y false si no se ha conseguido
	 * @throws ParseException 
	 * @throws SQLException
	 */
	public static boolean insertarUsuario(Usuario usuario) throws ParseException {
		try {
		    Date date = Date.valueOf(usuario.getFecha_naci());
			
			String consulta = "INSERT INTO usuario (nombreUsuario, apellido, password, fecha_naci, email, pais, numero_juegos, saldo, admin)";
			consulta = consulta+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, usuario.getNombre());
		    ps.setString(2, usuario.getApellido());
		    ps.setString(3, usuario.getPassword());
		    ps.setDate(4, date);
		    ps.setString(5, usuario.getEmail());
		    ps.setString(6, usuario.getPais());
		    ps.setInt(7, usuario.getNumeroDeJuegos());
		    ps.setDouble(8, usuario.getSaldo());
		    ps.setBoolean(9, usuario.isAdmin());
			
			ps.setQueryTimeout(30);
			ps.executeUpdate();
			ps.close();
			log(Level.INFO, usuario.getNombre() + " añadido a la base de datos", null);
			return true;
		}catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de insertar al usuario " + usuario.getNombre(), e);
			return false;
		}		
	}
	
	/**
	 * Añade dinero a un usuario en la base de datos
	 * @param usuario al que se le añade el saldo
	 * @param saldo que se le añade
	 */
	public static void ainadirDinero(Usuario u, double saldo) {
		try {
			statement = connection.createStatement();
			
			statement.executeUpdate("UPDATE usuario SET saldo = "+(u.getSaldo()+saldo)+" WHERE nombreUsuario = '"+u.getNombre()+"';");
			statement.close();
			log(Level.INFO, "Saldo = "+saldo+" añadido a "+u.getNombre(), null);
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de añadir el saldo a " + u.getNombre(), e);
		}
	}
	
	/**
	 * Resta dinero a un usuario en la base de datos
	 * @param usuario al que se le resta el saldo
	 * @param saldo que se le resta
	 */
	public static void restarDinero(Usuario u, double saldo) {
		try {
			statement = connection.createStatement();
			double nuevoSaldo = u.getSaldo()-saldo;
			statement.executeUpdate("UPDATE usuario SET saldo = "+nuevoSaldo+" WHERE nombreUsuario = '"+u.getNombre()+"';");
			log(Level.INFO, "Saldo = "+saldo+" restado a "+u.getNombre(), null);
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de restar el saldo a " + u.getNombre(), e);
		}
	}
	
	public static ArrayList<Usuario> verTodosUsuarios() {
		try {
			ArrayList<Usuario> lista= new ArrayList<Usuario>();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM usuario;");
			while(rs.next()) {
				// Se saca por consola info de la tabla
				System.out.println("Nombre = " + rs.getString(1));
				System.out.println("Apellido = " + rs.getString(2));
				System.out.println("Password = " + rs.getString(3));
				System.out.println("Fecha nacimiento = " + rs.getString(4));
				System.out.println("Email = " + rs.getString(5));
				System.out.println("Pais = " + rs.getString(6));
				System.out.println("Numero de juegos = " + rs.getInt(7));
				System.out.println("Saldo = " + rs.getDouble(8));
				System.out.println("Admin = " + rs.getBoolean(9));
				lista.add(new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
			rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getBoolean(9)));
			}
			rs.close();
			log(Level.INFO, "Seleccionados todos los usuario", null);
			return lista;
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de seleccionar todos los usuarios", e);
			return null;
		}
	}
	
	/**
	 * Para poder ver la información que hay en la tabla de usuario
	 * @param Usuario
	 * @return Nombre, apellido, password, fecha_nacimiento, email, pais, numero de juegos, saldo y juegos comprados 
	 * @throws SQLException
	 */
	public static Usuario verUsuario(Usuario usuario) {
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM usuario WHERE nombreUsuario = '"+usuario.getNombre()+"';");
			Usuario visto = null;
			while(rs.next()) {
				// Se saca por consola info de la tabla
				System.out.println("Nombre = " + rs.getString(1));
				System.out.println("Apellido = " + rs.getString(2));
				System.out.println("Password = " + rs.getString(3));
				System.out.println("Fecha nacimiento = " + rs.getString(4));
				System.out.println("Email = " + rs.getString(5));
				System.out.println("Pais = " + rs.getString(6));
				System.out.println("Numero de juegos = " + rs.getInt(7));
				System.out.println("Saldo = " + rs.getDouble(8));
				System.out.println("Admin = " + rs.getBoolean(9));
				visto = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getBoolean(9));
			}
			rs.close();
			log(Level.INFO, "Seleccionado el usuario " + usuario.getNombre(), null);
			return visto;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de seleccionar al usuario "+usuario.getNombre(), e);
			return null;
		}
	}
	
	/**
	 * Visualiza toda la información guardada que hay del juego
	 * @param Juego
	 * @return Juego
	 * @throws SQLException
	 */
	public static Juego verJuego(Juego juego) {
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM juego WHERE nombreJuego = '"+juego.getNombre()+"';");
			Juego juegoCompleto = null;
			while (rs.next()){
				System.out.println("Nombre = "+rs.getString(1));
				System.out.println("Edad necesaria = "+rs.getInt(2));
				System.out.println("Categoria = "+rs.getString(3));
				System.out.println("Precio = "+rs.getDouble(4));
				System.out.println("Prestamo = "+rs.getBoolean(5));
				juegoCompleto = new Juego(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), null);
			}
			rs.close();
			log(Level.INFO, "Seleccionado el juego " + juego.getNombre(), null);
			return juegoCompleto;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de seleccionar el juego "+juego.getNombre(), e);
			return null;
		}
	}

	/**
	 * Visualiza todos los juegos que hay en la base de datos
	 * @return devuelve en modo de objetos todos los juegos de la base de datos
	 */
	public static ArrayList<Juego> verTodosJuegos() {
		try {
			ArrayList<Juego> juegos = new ArrayList<Juego>();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM juego;");
			while(rs.next()) {
				System.out.println("Nombre = "+rs.getString(1));
				System.out.println("Edad necesaria = "+rs.getInt(2));
				System.out.println("Categoria = "+rs.getString(3));
				System.out.println("Precio = "+rs.getDouble(4));
				System.out.println("Prestamo = "+rs.getBoolean(5));
				juegos.add(new Juego(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), null));
			}
			rs.close();
			log(Level.INFO, "Seleccionados todos los juegos", null);
			return juegos;
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de seleccionar todos los juegos", e);
			return null;
		}
	}
	
	/**
	 * Elimina un juego de la base de datos
	 * @param juego
	 * @return True si se ha eliminado y false si no se ha conseguido borrar
	 * @throws SQLException
	 */
	public static boolean eliminarJuego(Juego juego) {	
		try {
			statement = connection.createStatement();
			String sql ="DELETE FROM juego WHERE nombreJuego = '"+juego.getNombre()+"';";
			statement.executeUpdate(sql);
			
			log(Level.INFO, juego + "eliminado de la base de datos", null);
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de eliminar el juego seleccionado", e);
			return false;
		}
	}

	/**
	 * Inserta un juego en la base de datos.
	 * @param juego
	 * @return True si ha sido insertado y false si no se ha conseguido.
	 * @throws SQLException
	 */
	public static boolean insertarJuego(Juego juego) {
		try {
			String consulta = "INSERT INTO juego (nombreJuego, edad_necesaria, categoria, precio, prestamo)";
			consulta = consulta+" VALUES (?, ?, ?, ?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, juego.getNombre());
		    ps.setInt(2, juego.getEdadnecesaria());
		    ps.setString(3, juego.getCategoria());
		    ps.setDouble(4, juego.getPrecio());
		    ps.setBoolean(5, juego.isPrestamo());
		    
			ps.executeUpdate();
			log(Level.INFO, juego.getNombre()+" añadido a la base de datos", null);
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de insertar el juego "+juego.getNombre(), e);
			return false;
		}
	}

	/**
	 * Inserta un nuevo juego al usuario pasado por parametro en la tabla biblioteca
	 * @param u Usuario
	 * @param j Juego
	 * @return true o false si se ha podido ejecutar bien la operación
	 */
	public static boolean nuevoJuego(Usuario u, Juego j) {
		try {
			String consulta = "INSERT INTO biblioteca (nombreUsuario, nombreJuego) VALUES (?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, u.getNombre());
		    ps.setString(2, j.getNombre());
		    
		    ps.executeUpdate();
			log(Level.INFO,"El nuevo juego "+j.getNombre()+" de "+u.getNombre()+" añadido a la base de datos", null);
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de añadir el juego "+j.getNombre()+" a "+u.getNombre(), e);
			return false;
		}
	}
	
	/**
	 * Lista de los juegos de un usuario en concreto
	 * @param u Usuario
	 * @return Lista de los juegos que tiene el usuario
	 */
	public static ArrayList<Juego> juegos(Usuario u) {
		try {
			ArrayList<Juego> juegos = new ArrayList<Juego>();
			String nombre = "";
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM biblioteca WHERE nombreUsuario = '"+u.getNombre()+"';");
			while(rs.next()) {
				nombre = rs.getString(2);
			}
			rs = statement.executeQuery("SELECT * FROM juego WHERE nombreJuego = '"+nombre+"';");
			while(rs.next()) {
				juegos.add(new Juego(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), null));
			}
			rs.close();
			log(Level.INFO,"Lista de juegos del usuario "+u.getNombre(), null);
			return juegos;
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de visualizar los juegos de "+u.getNombre(), e);
			return null;
		}
	}
	
	/**
	 * Lista de amigos del usuario que se pasa como parametro
	 * @param u Usuario
	 * @return la lista de amigos del usuario
	 */
	public static ArrayList<Usuario> listaDeAmigos(Usuario u) {
		try {
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM amigos WHERE nombre = '"+u.getNombre()+"';");
			ArrayList<String> amigos = new ArrayList<String>();
			while(rs.next()) {
				amigos.add(rs.getString(2));
			}
			for(int i=0; i<amigos.size();i++) {
				rs = statement.executeQuery("SELECT * FROM usuario WHERE nombreUsuario = '"+amigos.get(i)+"';");	
			}
			while(rs.next()) {
				lista.add(new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getBoolean(9)));
			}
			
			rs.close();
			log(Level.INFO, "Lista de amigos de "+u.getNombre(), null);
			return lista;
		} catch (SQLException e) {
			// TODO: handle exception
			log(Level.SEVERE, "Error a la hora de visualizar la lista de amigos de "+u.getNombre(), e);
			return null;
		}
	}
	
	/**
	 * Hace amigos a los dos usuarios que se pasan por paramtro
	 * @param u1 Usuario
	 * @param u2 Usuario
	 * @return Si es correcta la operación true sino false
	 */
	public static boolean amigos(Usuario u1, Usuario u2) {
		try {
			String consulta = "INSERT INTO amigos (nombre, amigo) VALUES (?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, u1.getNombre());
		    ps.setString(2, u2.getNombre());

			ps.executeUpdate();
			log(Level.INFO, u1.getNombre()+" es amigo de "+u2.getNombre(), null);
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de añadir amigo a "+u2.getNombre(), e);
			return false;
		}
	}
	
	/**
	 * Comprueba si existe el usuario en la base de datos
	 * @return Devuelve el objeto usuario
	 * @throws SQLException
	 */
	public static Usuario comprobarLogin(Usuario usuario) {
		try {
			String consulta ="SELECT * FROM usuario WHERE nombreUsuario = ? AND password = ?;";
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getPassword());
			
			Usuario registrado = null;
			rs = ps.executeQuery();
			while(rs.next()) {
				// Se saca por consola info de la tabla
				System.out.println("COMPROBAR LOGIN");
				System.out.println("Nombre = " + rs.getString("nombreUsuario"));
				System.out.println("Apellido = " + rs.getString("apellido"));
				System.out.println("Password = " + rs.getString("password"));
				System.out.println("Fecha nacimiento = " + rs.getString("fecha_naci"));
				System.out.println("Email = " + rs.getString("email"));
				System.out.println("Pais = " + rs.getString("pais"));
				System.out.println("Numero de juegos = " + rs.getInt("numero_juegos"));
				System.out.println("Saldo = " + rs.getDouble("saldo"));
				System.out.println("Admin = " + rs.getBoolean("admin"));
				registrado = new Usuario(rs.getString("nombreUsuario"), rs.getString("apellido"), rs.getString("password"), rs.getString("fecha_naci"), 
						rs.getString("email"), rs.getString("pais"), rs.getInt("numero_juegos"), rs.getDouble("saldo"), rs.getBoolean("admin"));
			}
			rs.close();
			ps.close();
			log(Level.INFO, "Nombre y contraseña de "+usuario.getNombre()+" correctos", null);
			return registrado;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en nombre y contraseña " + usuario.getNombre(), e);
			return null;
		}
	}
	
	public static String convertir(char[] password) {
		String contra = "";
		for(int i = 0; i < password.length; i++){
			contra+=password[i];
		}
		return contra;
	}
	
	private static void log(Level level, String msg, Throwable excepcion) {
		if (logger==null) {  
			logger = Logger.getLogger(BaseDeDatos.class.getName());  
			logger.setLevel(Level.ALL);  
		}
		if (excepcion==null)
			logger.log(level, msg);
		else
			logger.log(level, msg, excepcion);
	}
	
}