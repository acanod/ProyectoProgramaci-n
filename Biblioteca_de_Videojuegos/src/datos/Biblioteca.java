package datos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import baseDeDatos.BaseDeDatos;

public class Biblioteca {

	/**
	 * Añade un nuevo juego en la biblioteca del usuario
	 * @param usuario con el nuevo juego
	 * @param juego nuevo comprado
	 * @throws SQLException
	 */
	public void ainadirJuego(Usuario usuario, Juego juego) throws SQLException {
		if(BaseDeDatos.nuevoJuego(usuario, juego)) {
			JOptionPane.showMessageDialog(null, "El juego "+juego.getNombre()+" se ha añadido", "Información", JOptionPane.INFORMATION_MESSAGE);
			usuario.setNumeroDeJuegos(usuario.getNumeroDeJuegos() + 1);
		}
	}
	
	/**
	 * Comprar un nuevo juego, teniendo en cuenta el saldo del usuario, el valor del juego y si lo tiene o no ya en la biblioteca
	 * @param usuario
	 * @param juego
	 * @return
	 * @throws SQLException 
	 */
	public static boolean comprarJuego(Usuario usuario, Juego juego) throws SQLException {
		List<String> listaDeJuegos = BaseDeDatos.juegos(usuario);
		for(int i = 0; i < listaDeJuegos.size(); i++) {
			if((listaDeJuegos.get(i).equals(juego.getNombre()))) {
				JOptionPane.showMessageDialog(new JFrame(), "Ya tienes este juego.");
				return false;
			}
			if(BaseDeDatos.verUsuario(usuario).getSaldo() < juego.getPrecio()) {
				JOptionPane.showMessageDialog(new JFrame(), "No tienes suficiente dinero para comprar este juego");
				return false;
			} else {
				new Biblioteca().ainadirJuego(usuario, juego);
				BaseDeDatos.restarDinero(usuario, juego.getPrecio());
				JOptionPane.showMessageDialog(new JFrame(), "Has comprado " + juego.getNombre());
				return true;
			}

		}
		return false;
	}
	
	public static boolean prestamo(Usuario usuario, Juego juego, List<Usuario> listaUsuarios) throws SQLException {
		List<String> listaDeAmigos = BaseDeDatos.listaDeAmigos(usuario);
		String[] listP = new String[listaDeAmigos.size()];
		for(int i = 0; i < listaDeAmigos.size(); i++) {
			listP[i] = listaDeAmigos.get(i);
		}
		do {
			String seleccion = (String) JOptionPane.showInputDialog(new JFrame(), "Lista de Amigos: ", "Lista de Amigos",
					JOptionPane.QUESTION_MESSAGE, null, listP, null);

			if (seleccion == null) {
				JOptionPane.showMessageDialog(new JFrame(), "No tienes amigos para prestar el juego");
			} else {
				for(int z = 0; z < listaUsuarios.size(); z++) {
					if(listaUsuarios.get(z).getNombre() == seleccion) {
						int comprobar = 0;
						for(int e = 0; e < listaUsuarios.get(z).getJuegosComprados().size(); e++) {
							comprobar++;
							if(listaUsuarios.get(z).getJuegosComprados().get(e).getNombre() == juego.getNombre()) {
								comprobar--;
								JOptionPane.showMessageDialog(new JFrame(), seleccion + " ya tiene " + juego.getNombre());
								return false;
							}
						}
						if(comprobar == listaUsuarios.get(z).getJuegosComprados().size()) {
							JOptionPane.showMessageDialog(new JFrame(),
									"Le has prestado " + juego.getNombre() + " a " + seleccion);
							listaUsuarios.get(z).getJuegosComprados().add(listaUsuarios.get(z).getNumeroDeJuegos(), juego);
							new Biblioteca().ainadirJuego(listaUsuarios.get(z), juego);
							listaUsuarios.get(z).setNumeroDeJuegos(listaUsuarios.get(z).getNumeroDeJuegos() + 1);
							juego.setPrestamo(true);
							return true;
						}
					}
				}
			}
		} while(listP == null);
		return false;
	}
	
	/**
	 * Metodo recursivo para sacar a los usuarios
	 * @param usuarios
	 * @param indice
	 * @return 
	 */
	public static ArrayList<Usuario> listaUsuarios(ArrayList<Usuario> usuarios, int indice) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		if(usuarios.size() == indice) {
			lista.add(usuarios.get(indice-1));
			return lista;
		} else {
			lista.add(usuarios.get(indice));
			indice++;
			return listaUsuarios(usuarios, indice);
		}
	}
}
