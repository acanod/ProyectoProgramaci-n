package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import baseDeDatos.BaseDeDatos;
import datos.Juego;
import datos.Usuario;

public class VAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JTextField edad_necesaria;
	private JComboBox<String> categoria;
	private JTextField precio;
	private String[] categorias = { "Todos", "Deportes", "Aventura", "Estrategia", "Acción" };
	private final ArrayList<String> aCategorias = new ArrayList<String>();

	public VAdmin(Usuario u, List<Usuario> users, List<Juego>j ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		setLocationRelativeTo(null);
		setTitle("Administrar");
		setResizable(false);
		componentes(u, users, j);
		setVisible(true);
	}

	public void componentes(Usuario u, List<Usuario> users, List<Juego> j) {

		GridBagConstraints constraints = new GridBagConstraints();
		this.getContentPane().setLayout(new GridBagLayout());
		
		JButton ainadir = new JButton("Añadir");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.weighty = 0.05;
		this.getContentPane().add(ainadir, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 0.0;

		JButton atras = new JButton("Atras");
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.weighty = 0.05;
		this.getContentPane().add(atras, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 0.0;

		JLabel lnombre = new JLabel("Nombre");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lnombre, constraints);
		constraints.weighty = 0.0;

		nombre = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(nombre, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;

		JLabel lEdad_necesaria = new JLabel("Edad necesaria");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lEdad_necesaria, constraints);
		constraints.weighty = 0.0;
		
		edad_necesaria = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(edad_necesaria, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;

		JLabel lCategoria = new JLabel("Categoria");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lCategoria, constraints);
		constraints.weighty = 0.0;

		for (int i = 0; i < categorias.length; i++) {
			aCategorias.add(categorias[i]);
		}
		categoria = new JComboBox<String>();

		for (int i = 0; i < aCategorias.size(); i++) {
			categoria.addItem(aCategorias.get(i));
		}
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0.05;
		getContentPane().add(categoria, constraints);
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;

		JLabel lPrecio = new JLabel("Precio");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lPrecio, constraints);
		constraints.weighty = 0.0;

		precio = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(precio, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.CENTER;
		
		ainadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int edad = Integer.parseInt(edad_necesaria.getText());
				double valor = Double.parseDouble(precio.getText());

				if (BaseDeDatos.insertarJuego(new Juego(nombre.getText(), edad, categoria.getSelectedItem().toString(),
						valor, false, null))) {
					JOptionPane.showMessageDialog(null, "Juego añadido", "Atencion", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Problemas a la hora de añadir el juego", "Atencion",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VAdmin.this.dispose();
			}
		});
	}

}