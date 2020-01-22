package ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import baseDeDatos.BaseDeDatos;
import datos.Biblioteca;
import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class VJuegoInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VJuegoInfo(Juego j, Usuario u, VTienda t, List<Usuario> users) throws IOException {
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
				if ((BaseDeDatos.juegos(u).get(i).equals(j.getNombre()))) {
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
				try {
					if(Biblioteca.comprarJuego(u, j)) {
						t.fondos.setText(Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		if (j.isPrestamo() == true) {
			prestamo.setText("En prestamo");
			prestamo.setEnabled(false);
		}

		prestamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				prestamo.setText("En prestamo");
				prestamo.setEnabled(false);
			}
		});
	}

}