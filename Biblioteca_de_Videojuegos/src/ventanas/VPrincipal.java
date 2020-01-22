package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baseDeDatos.BaseDeDatos;
import datos.Biblioteca;
import datos.Gestion;
import datos.Juego;
import datos.Sesion;
import datos.Usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class VPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;

	public static int hora = 0, min = 0, seg = 0;
	public static boolean iniciarHilo = false;
	
	public VPrincipal(Usuario u, List<Usuario> users, List<Juego> j) {
		setTitle("Principal de "+u.getNombre());
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.NORTH);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		/////////CARGAR LAS IMAGENES DE LOS JUEGOS/////////
		ArrayList<Juego> juegoConLabel = Gestion.listaJuegosConImagen(j);
		/////////CARGAR LAS IMAGENES DE LOS JUEGOS/////////
		
		JButton btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VTienda(u, juegoConLabel).setVisible(true);
			}
		});
		contentPane.add(btnTienda);

		JButton btnBiblioteca = new JButton("Biblioteca");
		btnBiblioteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VBiblioteca(u, Gestion.todosLosUsuarios(), juegoConLabel).setVisible(true);
			}
		});
		contentPane.add(btnBiblioteca);

		JButton btnAmigos = new JButton("Amigos");
		btnAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VAmigos(u, Gestion.todosLosUsuarios(), Gestion.todosLosJuegos()).setVisible(true);
			}
		});
		contentPane.add(btnAmigos);

		JButton btnAdmin = new JButton("Administrar");
		btnAdmin.setVisible(false);
		if (u.isAdmin() == true) {
			btnAdmin.setVisible(true);
		}

		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new VAdmin(u, Gestion.todosLosUsuarios(), Gestion.todosLosJuegos());
			}
		});
		contentPane.add(btnAdmin);

		JLabel lblSaldo = new JLabel("Saldo: "+Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));
		contentPane.add(lblSaldo);
		
		DefaultTableModel modelo = new DefaultTableModel();
		for(int i = 0; i < Gestion.todosLosUsuarios().size(); i++) {
			modelo.addColumn(Gestion.todosLosUsuarios().get(i));
		}
		
		JPanel nombres = new JPanel();
		getContentPane().add(nombres, BorderLayout.CENTER);
		ArrayList<Usuario> usuarios = Biblioteca.listaUsuarios(Gestion.todosLosUsuarios(), 0);
		for(int i = 0; i < usuarios.size(); i++) {
			nombres.add(new JLabel(usuarios.get(i).getNombre()));
		}
		
		JPanel sesion = new JPanel();
		getContentPane().add(sesion, BorderLayout.SOUTH);
		JLabel tiempoSesion = new JLabel("Tiempo de sesion ");
		label = new JLabel("00:00:00");
		sesion.add(tiempoSesion);
		sesion.add(label);

		Sesion se = new Sesion(label);
		se.iniciarCronometro();
		
	}
	
}
