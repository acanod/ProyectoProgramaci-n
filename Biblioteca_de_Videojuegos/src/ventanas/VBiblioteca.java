package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDeDatos.BaseDeDatos;
import datos.Gestion;
import datos.Juego;
import datos.Usuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class VBiblioteca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VBiblioteca(Usuario u, List<Usuario> users, List<Juego> j) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		for (int i = 0; i < j.size(); i++) {
			funcionalidadCaratulas(i, j, u, Gestion.todosLosUsuarios());
		}

		JButton btnNewButton = new JButton("Atras");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VBiblioteca.this.setVisible(false);
			}
		});

		JPanel panelJuegos = new JPanel();
		panelJuegos.setLayout(new GridLayout(0, 4));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton)
						.addComponent(panelJuegos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
				.addContainerGap()));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addComponent(panelJuegos, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE).addGap(18)
								.addComponent(btnNewButton)));
		contentPane.setLayout(gl_contentPane);

		ArrayList<Juego> juegosUsuario = new ArrayList<Juego>();
		for(int i = 0; i < BaseDeDatos.juegos(u).size(); i++) {
			juegosUsuario.add(BaseDeDatos.verJuego(BaseDeDatos.juegos(u).get(i)));
		}
		for(int i = 0; i < juegosUsuario.size(); i++) {
			panelJuegos.add(Gestion.listaJuegosConImagen(juegosUsuario).get(i).getCaratula());
		}

	}

	public static void funcionalidadCaratulas(int numero, List<Juego> juego, Usuario u, List<Usuario> users) {
		for (MouseListener al : juego.get(numero).getCaratula().getMouseListeners()) {
			juego.get(numero).getCaratula().removeMouseListener(al);
		}
		juego.get(numero).getCaratula().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					new VJuegoInfo(juego.get(numero), u, null, Gestion.todosLosUsuarios()).setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

}
