package base_de_datos;

import java.sql.Connection;

import datos.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	public static boolean conectarBD() {
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:videojuegos.db");
			Statement statement = connection.createStatement();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuario (nombre VARCHAR(35) NOT NULL PRIMARY KEY, apellido CHAR(20) NOT NULL, password VARCHAR(30) NOT NULL, fecha_naci DATE, email CHAR(20),"
					+ " pais CHAR(35), numero_juegos INT, saldo DOUBLE)");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS juego (cod_jue INTEGER PRIMARY KEY AUTOINCREMENT, nombre CHAR(20) NOT NULL, edad_necesaria INT, categoria CHAR(20) NOT NULL, precio INT,"
					+ " prestamo BOOLEAN, imagen BLOB)");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS amigos (nombre VARCHAR(35), amigo VARCHAR(35), FOREIGN KEY (nombre, amigo) REFERENCES usuario (nombre, nombre))");
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS biblioteca (nombre VARCHAR(35), cod_jue CHAR(5), FOREIGN KEY (nombre) REFERENCES usuario (nombre), FOREIGN KEY (cod_jue) REFERENCES juego (cod_jue))");
			
			log(Level.INFO, "Base de datos conectada", null);
			return true;
		} catch(SQLException e) {
			log(Level.SEVERE, "Error en conexión de base de datos", e);
			return false;
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
	 * @throws SQLException
	 */
	public static boolean insertarUsuario(Usuario usuario) {
		try {
			String consulta = "INSERT INTO usuario (nombre, apellido, password, fecha_naci, email, pais, numero_juegos, saldo)";
			consulta = consulta+" VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, usuario.getNombre());
		    ps.setString(2, usuario.getApellido());
		    ps.setString(3, usuario.getPassword());
		    ps.setString(4, usuario.getFecha_naci());
		    ps.setString(5, usuario.getEmail());
		    ps.setString(6, usuario.getPais());
		    ps.setInt(7, usuario.getNumeroDeJuegos());
		    ps.setDouble(8, usuario.getSaldo());
			
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
	 * Para poder ver la información que hay en la tabla de usuario
	 * @param Usuario
	 * @return Nombre, apellido, password, fecha_nacimiento, email, pais, numero de juegos, saldo y juegos comprados 
	 * @throws SQLException
	 */
	public static Usuario verUsuario(Usuario usuario) {
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT "+usuario.getNombre()+" FROM usuario");
			while(rs.next()) {
				// Se saca por consola info de la tabla
				System.out.println("Nombre = " + rs.getString("nombre"));
				System.out.println("Apellido = " + rs.getString("apellido"));
				System.out.println("Password = " + rs.getString("password"));
				System.out.println("Fecha nacimiento = " + rs.getString("fecha_naci"));
				System.out.println("Email = " + rs.getString("email"));
				System.out.println("Pais = " + rs.getString("pais"));
				System.out.println("Numero de juegos = " + rs.getInt("numero_juegos"));
				System.out.println("Saldo = " + rs.getDouble("saldo"));
			}
			rs.close();
			log(Level.INFO, "Seleccionado el usuario " + usuario.getNombre(), null);
			return new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(6), rs.getInt(7), rs.getDouble(8));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de seleccionar usuario", e);
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
			rs = statement.executeQuery("SELECT * FROM juego WHERE nombre = '"+juego.getNombre()+"';");
			Juego juegoCompleto = null;
			while (rs.next()){
				System.out.println("Codigo = "+rs.getInt(1));
				System.out.println("Nombre = "+rs.getString(2));
				System.out.println("Edad necesaria = "+rs.getInt(3));
				System.out.println("Categoria = "+rs.getString(4));
				System.out.println("Precio = "+rs.getDouble(5));
				System.out.println("Prestamo = "+rs.getBoolean(6));
				juegoCompleto = new Juego(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getBoolean(6));
			}
			rs.close();
			log(Level.INFO, "Seleccionado el entrenador " + juego.getNombre(), null);
			return juegoCompleto;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de seleccionar entrenador", e);
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
			String sql ="DELETE FROM juego WHERE nombre = '"+juego.getNombre()+"';";
			rs = statement.executeQuery(sql);
			rs.close();
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
			String consulta = "INSERT INTO juego (nombre, edad_necesaria, categoria, precio, prestamo)";
			consulta = consulta+" VALUES (?, ?, ?, ?, ?);";
		    PreparedStatement ps = connection.prepareStatement(consulta);
		    ps.setString(1, juego.getNombre());
		    ps.setInt(2, juego.getEdadnecesaria());
		    ps.setString(3, juego.getCategoria());
		    ps.setDouble(4, juego.getPrecio());
		    ps.setBoolean(5, juego.isPrestamo()); 

			ps.executeUpdate();
			log(Level.INFO, juego + " añadido a la base de datos", null);
			return true;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error a la hora de insertar el juego " + juego.getNombre(), e);
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
			String consulta ="SELECT * FROM usuario WHERE nombre = ? AND password = ?;";
			PreparedStatement ps = connection.prepareStatement(consulta);
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getPassword());
			
			rs = ps.executeQuery();
			Usuario registrado = new Usuario(usuario.getNombre(), usuario.getPassword());
			rs.close();
			log(Level.INFO, "Nombre y contraseña de "+usuario.getNombre()+" correctos", null);
			ps.close();
			return registrado;
		}catch (SQLException e) {
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
	
	/**
	 * Visualiza todos los usuarios que hay en la base de datos para crear una tabla
	 * @return Nombre y apellido de los usuarios guardados
	 */
	public static JTable creadorTabla() {
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM usuario");
			ResultSetMetaData metaDatos = rs.getMetaData();
			
			int numeroColumnas = metaDatos.getColumnCount();
			DefaultTableModel modelo = new DefaultTableModel();
			JTable tabla = new JTable(modelo);
			Object[] etiquetas = new Object[numeroColumnas];
			//Crea las cabeceras del JTable
			for (int i = 0; i < numeroColumnas; i++) {
				etiquetas[i] = metaDatos.getColumnLabel(i + 1);
				modelo.setColumnIdentifiers(etiquetas);
			}
			//Crea las filas para el JTable
			while(rs.next()) {
				Object[] fila = new Object[numeroColumnas];
				for (int i = 0; i < numeroColumnas; i++) {
					fila[i]=rs.getObject(i+1);
				}
				modelo.addRow(fila);
			}
			rs.close();
			log(Level.INFO, "Lista de usuarios", null);
			return tabla;
		} catch (SQLException e) {
			log(Level.SEVERE, "Error, no se han podidio visualizar los usuarios", e);
			return null;
		}
	}
	
	public static void borrarFilaDeTabla(JTable tabla) {
		DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
		if(tabla.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(null, "Debe elegir una fila", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			dtm.removeRow(tabla.getSelectedRow());
		}
	}

}
