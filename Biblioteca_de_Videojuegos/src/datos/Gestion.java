package datos;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import baseDeDatos.BaseDeDatos;
import ventanas.VJuegoInfo;
import ventanas.VTienda;

public class Gestion {

	/**
	 * Comprueba el login del usuario
	 * @param nombre del usuario a comprobar
	 * @param contraseina del usuario a comprobar
	 * @return usuario registrado
	 * @throws SQLException 
	 */
	public static Usuario comprobarLogin(JTextField nombre, JPasswordField contraseina) throws SQLException {
		char[] contra = contraseina.getPassword();
		String contr = new String(contra);
		Usuario inicio = null;
		if(nombre.getText().isBlank() || contr.length()==0) {
			JOptionPane.showMessageDialog(null, "Escriba en todos los parametros", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Usuario iniciar = new Usuario(nombre.getText(), contr);
			inicio = BaseDeDatos.comprobarLogin(iniciar);
		}
		return inicio;
	}
	
	/**
	 * Registra al usuario comprobando que no hay otro igual en la base de datos
	 * @param usuario a registrar
	 * @return true en caso de de poder registrarlo, false en caso de no poder registrarlo
	 */
	public static boolean registrarUsuario(Usuario usuario) {
		ArrayList<Usuario> lista = todosLosUsuarios();
		if(lista.isEmpty()) {
			if(BaseDeDatos.insertarUsuario(usuario)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null,"Error a la hora de añadir el usuario");
				return false;
			}
		} else {
			for(int i = 0; i < lista.size(); i++) {
				if(lista.get(i).getNombre() == usuario.getNombre()) {
					JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Atencion", JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
			if(BaseDeDatos.insertarUsuario(usuario)) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null,"Error a la hora de añadir el usuario");
				return false;
			}
		}
	}
	
	/**
	 * Devuelve el componente JLabel de el nombre de la imagen que se le pase
	 * @param nombre de la foto
	 * @return JLabel de la imagen
	 */
	public static JLabel juegoConImagen(String nombre) {
		JLabel resultado = new JLabel("");

		ImageIcon imageIcon = new ImageIcon("./img/" + nombre + ".jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		resultado.setIcon(imageIcon);

		return resultado;
	}
	
	/**
	 * Lista de juegos con su imagen correspondiente
	 * @param juegoSinLabel lista de juegos sacada de la base de datos, no tienen imagen
	 * @return lista de juegos con su imagen correspondiente
	 */
	public static ArrayList<Juego> listaJuegosConImagen(List<Juego> juegoSinLabel) {
		ArrayList<Juego> juegoConLabel = new ArrayList<Juego>();
		
		for(int z = 0; z < juegoSinLabel.size(); z++) {
			JLabel label = juegoConImagen(juegoSinLabel.get(z).getNombre());
			juegoConLabel.add(new Juego(juegoSinLabel.get(z).getNombre(), juegoSinLabel.get(z).getEdadnecesaria(),
					juegoSinLabel.get(z).getCategoria(), juegoSinLabel.get(z).getPrecio(),
					juegoSinLabel.get(z).isPrestamo(), label));
		}
		
		return juegoConLabel;
	}
	
	/**
	 * Abre la ventana de informacion del juego al que se le ha dado clic
	 * @param numero que recorre la lista de juegos
	 * @param Lista de juego
	 * @param u 
	 * @param t 
	 */
	public static void funcionalidadCaratulas(int numero, List<Juego> juego, Usuario u, VTienda t) {
		for (MouseListener al : juego.get(numero).getCaratula().getMouseListeners()) {
			juego.get(numero).getCaratula().removeMouseListener(al);
		}
		juego.get(numero).getCaratula().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new VJuegoInfo(BaseDeDatos.verJuego(juego.get(numero).getNombre()), BaseDeDatos.verUsuario(u), t, BaseDeDatos.verTodosUsuarios()).setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
	
	/**
	 * Añade a una lista todos los usuarios que hay registrados en la base de datos
	 * @return una lista de todos los usuarios registrados
	 */
	public static ArrayList<Usuario> todosLosUsuarios() {
		ArrayList<Usuario> lista = BaseDeDatos.verTodosUsuarios();
		return lista;
	}
	
	/**
	 * Añade a una lista todos los juegos que hay guardados en la base de datos
	 * @return una lista de todos los juegos guardados
	 */
	public static ArrayList<Juego> todosLosJuegos() {
		ArrayList<Juego> lista = BaseDeDatos.verTodosJuegos();
		return lista;
	}

}
