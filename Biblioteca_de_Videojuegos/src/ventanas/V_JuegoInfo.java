package ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import base_de_datos.BaseDeDatos;
import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class V_JuegoInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public V_JuegoInfo(Juego j, Usuario u, V_Tienda t, List<Usuario> users) throws IOException {
		setTitle(j.getNombre());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel precio = new JLabel(Double.toString(j.getPrecio()));
		contentPane.add(precio, BorderLayout.EAST);

		JLabel nombre = new JLabel(j.getNombre());
		contentPane.add(nombre, BorderLayout.CENTER);

		JLabel descp = new JLabel();
		contentPane.add(descp, BorderLayout.NORTH);

		JPanel panelPostCompra = new JPanel();
		panelPostCompra.setLayout(new BorderLayout());

		JButton prestamo = new JButton("Prestamo");

		JButton jugar = new JButton("Jugar");

		panelPostCompra.add(jugar, BorderLayout.WEST);
		panelPostCompra.add(prestamo, BorderLayout.EAST);

		JButton comprar = new JButton("Comprar");
		contentPane.add(comprar, BorderLayout.SOUTH);

		if (!(BaseDeDatos.juegos(u).size() == 0)) {
			for (int i = 0; i < BaseDeDatos.juegos(u).size(); i++) {
				if ((BaseDeDatos.juegos(u).get(i).getNombre() == (j.getNombre()))) {
					comprar.setVisible(false);
					jugar.setEnabled(true);
					precio.setVisible(false);
					contentPane.add(panelPostCompra, BorderLayout.SOUTH);
				}
			}
		}

		JTextArea texto = new JTextArea("");
		new JScrollPane(texto);
		JPanel descripcionJuego = new JPanel();
		descripcionJuego.add(texto);

		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("descripciones/" + j.getNombre() + ".txt")))) {
			String text = null;
			while ((text = br.readLine()) != null) {
				texto.setText(" " + text);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		contentPane.add(descripcionJuego, BorderLayout.NORTH);

		comprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BaseDeDatos.juegos(u).isEmpty()) {
					if (u.getSaldo() < j.getPrecio()) {
						JOptionPane.showMessageDialog(new JFrame(), "No tienes suficiente dinero para comprar este juego");
					} else {
						u.setSaldo(u.getSaldo() - j.getPrecio());
						BaseDeDatos.restarDinero(u, j.getPrecio());
						BaseDeDatos.nuevoJuego(u, j);
						t.fondos.setText(Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));
						JOptionPane.showMessageDialog(new JFrame(), "Has comprado " + j.getNombre());
					}
				} else {
					for (int i = 0; i <= BaseDeDatos.juegos(u).size(); i++) {
						if ((BaseDeDatos.juegos(u).get(i).getNombre() == (j.getNombre()))) {
							JOptionPane.showMessageDialog(new JFrame(), "Ya tienes este juego.");
						} else {
							if (u.getSaldo() < j.getPrecio()) {
								JOptionPane.showMessageDialog(new JFrame(), "No tienes suficiente dinero para comprar este juego");
							} else {
								u.setSaldo(u.getSaldo() - j.getPrecio());
								BaseDeDatos.restarDinero(u, j.getPrecio());
								BaseDeDatos.nuevoJuego(u, j);
								t.fondos.setText(Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));
								JOptionPane.showMessageDialog(new JFrame(), "Has comprado " + j.getNombre());
							}
						}
					}
				}
			}
		});

		if (j.isPrestamo() == true) {
			prestamo.setText("En prestamo");
			prestamo.setEnabled(false);
		}

		prestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] listP = new String[BaseDeDatos.listaDeAmigos(u).size()];
				for (int i = 0; i < BaseDeDatos.listaDeAmigos(u).size(); i++) {
					listP[i] = BaseDeDatos.listaDeAmigos(u).get(i).getNombre();
				}
				do {
					// DefaultListModel<String> modelo = new DefaultListModel<>();
					// for (int i = 0; i < u.getAmigos().size(); i++) {
					// modelo.addElement(u.getAmigos().get(i).toString());
					// }
					// JList amigos = new JList(modelo);

					String seleccion = (String) JOptionPane.showInputDialog(new JFrame(), "Lista de Amigos: ", "Lista de Amigos",
							JOptionPane.QUESTION_MESSAGE, null, listP, null);

					if (seleccion == null) {
						JOptionPane.showMessageDialog(new JFrame(), "No tienes amigos para prestar el juego");
					} else {

						for (int z = 0; z < users.size(); z++) {
							if (users.get(z).getNombre() == seleccion) {
								int comprobar = 0;
								for (int e = 0; e < users.get(z).getJuegosComprados().size(); e++) {
									comprobar++;
									if (users.get(z).getJuegosComprados().get(e).getNombre() == j.getNombre()) {
										comprobar--;
										JFrame x = new JFrame();
										JOptionPane.showMessageDialog(x, seleccion + " ya tiene " + j.getNombre());
										break;

									}

								}
								if (comprobar == users.get(z).getJuegosComprados().size()) {
									JOptionPane.showMessageDialog(new JFrame(),
											"Le has prestado " + j.getNombre() + " a " + seleccion);
									users.get(z).getJuegosComprados().add(users.get(z).getNumeroDeJuegos(), j);
									BaseDeDatos.nuevoJuego(users.get(z), j);
									users.get(z).setNumeroDeJuegos(users.get(z).getNumeroDeJuegos() + 1);
									j.setPrestamo(true);
									prestamo.setText("En prestamo");
									prestamo.setEnabled(false);
									break;
								}
							}
						}
					}
				} while (listP == null);
			}
		});
	}

}