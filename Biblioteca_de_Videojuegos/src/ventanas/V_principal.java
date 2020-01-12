package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base_de_datos.BaseDeDatos;
import datos.Juego;
import datos.Usuario;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class V_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public V_principal(Usuario u, List<Usuario> users, List<Juego> j) {
		setTitle("Principal de "+u.getNombre());
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		/////////CARGAR LAS IMAGENES DE LOS JUEGOS/////////
		ArrayList<Juego> juegoSinLabel = new ArrayList<Juego>();
		ArrayList<Juego> juegoConLabel = new ArrayList<Juego>();
		for(int i = 0; i < BaseDeDatos.verTodosJuegos().size(); i++) {
			juegoSinLabel.add(BaseDeDatos.verTodosJuegos().get(i));
		}
		for(int z = 0; z < juegoSinLabel.size(); z++) {
			JLabel label = juegoConImagen(juegoSinLabel.get(z).getNombre());
			juegoConLabel.add(new Juego(juegoSinLabel.get(z).getNombre(), juegoSinLabel.get(z).getEdadnecesaria(),
					juegoSinLabel.get(z).getCategoria(), juegoSinLabel.get(z).getPrecio(),
					juegoSinLabel.get(z).isPrestamo(), label));
		}
		/////////CARGAR LAS IMAGENES DE LOS JUEGOS/////////
		
		JButton btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_Tienda(u, juegoConLabel).setVisible(true);
				V_principal.this.setVisible(false);
			}
		});
		contentPane.add(btnTienda);

		JButton btnBiblioteca = new JButton("Biblioteca");
		btnBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_Biblioteca(u, BaseDeDatos.verTodosUsuarios(), juegoConLabel).setVisible(true);
				V_principal.this.setVisible(false);
			}
		});
		contentPane.add(btnBiblioteca);

		JButton btnAmigos = new JButton("Amigos");
		btnAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_Amigos(u, BaseDeDatos.verTodosUsuarios(), BaseDeDatos.verTodosJuegos()).setVisible(true);
				V_principal.this.setVisible(false);
			}
		});
		contentPane.add(btnAmigos);

		JButton btnNewButton = new JButton("Mis Juegos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (u.getJuegosComprados().size() == 0) {
					System.out.println("No hay");
				} else {
					for (int i = 0; i < u.getJuegosComprados().size(); i++) {
						System.out.println(u.getJuegosComprados().get(i).getNombre());
					}
				}
			}
		});
		contentPane.add(btnNewButton);

		JButton btnAdmin = new JButton("Administrar");
		btnAdmin.setVisible(false);
		if (u.isAdmin() == true) {
			btnAdmin.setVisible(true);
		}

		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_Admin(u, BaseDeDatos.verTodosUsuarios(), BaseDeDatos.verTodosJuegos());
				V_principal.this.dispose();
			}
		});

		contentPane.add(btnAdmin);

		JLabel lblSaldo = new JLabel("Saldo: "+u.getSaldo());
		contentPane.add(lblSaldo);
	}

	public static ArrayList<Juego> juegosDePrueba() {
		ArrayList<Juego> todosLosJuegos = new ArrayList<Juego>();
//		BaseDeDatos.insertarJuego(juegoConImagen("Assasins Creed Origins", 16, "Aventura", 50.00, false));
//		BaseDeDatos.insertarJuego(juegoConImagen("Fifa 20", 12, "Deportes", 60.00, false));
//		BaseDeDatos.insertarJuego(juegoConImagen("Total War Shogun 2", 16, "Estrategia", 30.00, false));
//		BaseDeDatos.insertarJuego(juegoConImagen("Resident Evil", 18, "Acción", 30.00, false));
//		BaseDeDatos.insertarJuego(juegoConImagen("Devil May Cry", 16, "Aventura", 40.00, false));
//		BaseDeDatos.insertarJuego(juegoConImagen("Battlefield 1", 18, "Acción", 70.00, false));
		
		todosLosJuegos.add(new Juego("Assasins Creed Origins", 16, "Aventura", 50.00, false));
		todosLosJuegos.add(new Juego("Fifa 20", 12, "Deportes", 60.00, false));
		todosLosJuegos.add(new Juego("Total War Shogun 2", 16, "Estrategia", 30.00, false));
		todosLosJuegos.add(new Juego("Resident Evil", 18, "Acción", 30.00, false));
		todosLosJuegos.add(new Juego("Devil May Cry", 16, "Aventura", 40.00, false));
		todosLosJuegos.add(new Juego("Battlefield 1", 18, "Acción", 70.00, false));
		
		return todosLosJuegos;
	}
	
	/**
	 * Devuelve el componente JLabel de el nombre de la imagen que se le pase
	 * @param nombre de la foto
	 * @return JLabel de la imagen
	 */
	public JLabel juegoConImagen(String nombre) {
		JLabel resultado = new JLabel("");

		ImageIcon imageIcon = new ImageIcon("./img/" + nombre + ".jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		resultado.setIcon(imageIcon);
//		Juego resultJuego = new Juego(nombre, edadN, cat, precio, prest, resultado);

		return resultado;
	}

}