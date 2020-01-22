package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import baseDeDatos.BaseDeDatos;
import datos.Juego;
import datos.Usuario;

public class VAmigos extends JFrame {

	private static final long serialVersionUID = 1L;

	public VAmigos(Usuario u, List<Usuario> users, List<Juego> j) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		setLocationRelativeTo(null);
		setTitle("Amigos");
		setResizable(true);
		componentes(u, BaseDeDatos.verTodosUsuarios(), BaseDeDatos.verTodosJuegos());
		setVisible(true);
	}

	public void componentes(Usuario u, List<Usuario> users, List<Juego> j) {
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		((JPanel) container).setBorder(BorderFactory.createTitledBorder(u.getNombre()));
		GridBagConstraints c = new GridBagConstraints();

		JList<String> listamigos = new JList<String>();
		listamigos.setModel(inicializarListaA(BaseDeDatos.listaDeAmigos(u)));

		JScrollPane panelArbol = new JScrollPane(listamigos);
		listamigos.getSelectedValue();

		JList<String> juegos = new JList<String>();
		listamigos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario elegido=null;
				for(int i=0; i<users.size();i++) {
					if(listamigos.getSelectedValue().equals(users.get(i).getNombre())) {
						elegido=users.get(i);
					}
				}
				juegos.setModel(inicializarListaJ(BaseDeDatos.juegos(elegido)));
			}
		});

		panelArbol.setBorder(BorderFactory.createTitledBorder("Lista de amigos"));
		JScrollPane panelLista = new JScrollPane(juegos);
		panelLista.setBorder(BorderFactory.createTitledBorder("Lista de juegos"));

		JButton btnAinadir = new JButton("Solicitar Prestamo");
		JButton btnAtras = new JButton("Atras");
		JPanel botonera = new JPanel();

		JButton buscarA = new JButton("Buscar amigos");
		botonera.add(btnAinadir);
		botonera.add(buscarA);
		botonera.add(btnAtras);

		buscarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VListaUsuarios(u, BaseDeDatos.verTodosUsuarios(), j).setVisible(true);
				VAmigos.this.setVisible(false);
			}
		});

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VAmigos.this.setVisible(false);
			}
		});

		btnAinadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame f;
				f = new JFrame();
				if (BaseDeDatos.juegos(u).size() != 0) {
					for (int i = 0; i < BaseDeDatos.juegos(u).size(); i++) {
						if (!(BaseDeDatos.juegos(u).get(i).equals(juegos.getSelectedValue().toString()))) {
							int seleccion = JOptionPane.showOptionDialog(f,
									"Deseas solicitar " + juegos.getSelectedValue().toString() + " a "
											+ listamigos.getSelectedValue().toString() + "?",
									"Solicitar " + juegos.getSelectedValue().toString(),
									JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									new Object[] { "Si", "No" }, // null para YES, NO y CANCEL
									"opcion 1");

							if (seleccion != -1 && seleccion == 0) {
								JOptionPane.showMessageDialog(f, "Has solicitado a "+listamigos.getSelectedValue().toString()+" "
												+juegos.getSelectedValue().toString());
								for (int q = 0; q < j.size(); q++) {
									if (j.get(q).getNombre().equals(listamigos.getSelectedValue().toString())) {
										try {
											BaseDeDatos.nuevoJuego(u, nuevoJuego(juegos.getSelectedValue().toString(), j));
										} catch (SQLException e) {
											e.printStackTrace();
										}
										//new Biblioteca().ainadirJuego(u, juegos.getSelectedIndex());
									}
								}

							}
						} else {
							JOptionPane.showMessageDialog(f, "Ya tienes " + juegos.getSelectedValue().toString());
						}
					}

				} else {
					int seleccion = JOptionPane.showOptionDialog(f,
							"Deseas solicitar " + juegos.getSelectedValue().toString() + " a "
									+ listamigos.getSelectedValue().toString() + "?",
							"Solicitar " + juegos.getSelectedValue().toString(), JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, // null para YES, NO y
																								// CANCEL
							"opcion 1");

					if (seleccion != -1 && seleccion == 0) {
						JFrame x = new JFrame();
						JOptionPane.showMessageDialog(x, "Has solicitado a " + listamigos.getSelectedValue().toString()
								+ " " + juegos.getSelectedValue().toString());
						for (int z = 0; z < j.size(); z++) {
							if (juegos.getSelectedValue().toString().equals(j.get(z).getNombre())) {
								try {
									BaseDeDatos.nuevoJuego(u, nuevoJuego(juegos.getSelectedValue().toString(), j));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					}

				}
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		container.add(new JToolBar(), c);

		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(2, 2, 6, 2);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.EAST;
		container.add(new JLabel("Servidor"), c);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		container.add(new JTextField(), c);

		c.gridx = 4;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.EAST;
		container.add(new JLabel("Estado:"), c);

		c.gridx = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.WEST;
		container.add(new JLabel("Desconectado"), c);

		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridheight = GridBagConstraints.RELATIVE;
		container.add(panelArbol, c);

		c.gridx = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		container.add(panelLista, c);

		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = GridBagConstraints.RELATIVE;
		container.add(botonera, c);

	}

	public static DefaultListModel<String> inicializarListaA(List<String> list) {
		DefaultListModel<String> listaBien = new DefaultListModel<String>();

		for (int i = 0; i < list.size(); i++) {
			listaBien.add(i, list.get(i));
		}
		return listaBien;
	}

	public static DefaultListModel<String> inicializarListaJ(List<String> list) {
		DefaultListModel<String> listaBien = new DefaultListModel<String>();

		for (int i = 0; i < list.size(); i++) {
			listaBien.add(i, list.get(i));
		}
		return listaBien;
	}

	public static Juego nuevoJuego(String nombre, List<Juego> listJuegos) {
		Juego resultado = null;
		for (int i = 0; i < listJuegos.size(); i++) {
			if (nombre.equals(listJuegos.get(i).getNombre())) {
				resultado = listJuegos.get(i);
			}

		}

		return resultado;
	}
}