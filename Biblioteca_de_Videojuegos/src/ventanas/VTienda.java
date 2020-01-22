package ventanas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDeDatos.BaseDeDatos;
import datos.Gestion;
import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VTienda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField comp;
	public JLabel fondos = new JLabel("");

	public VTienda(Usuario u, List<Juego> j) {
		setTitle("Tienda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 421);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		for (int i = 0; i < j.size(); i++) {
			Gestion.funcionalidadCaratulas(i, j, u, VTienda.this);
		}

		String[] categorias = { "Todos", "Deportes", "Aventura", "Estrategia", "Acción" };

		JPanel panel_3 = new JPanel();
		JPanel panel_2 = new JPanel();
		JPanel panelSaldo = new JPanel();

		comp = new JTextField();
		comp.setColumns(10);

		JLabel lblSaldoDisponible = new JLabel("Saldo disponible: ");

		fondos.setText(Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));

		JButton btnAadirFondos = new JButton("A\u00F1adir Fondos");
		GroupLayout gl_panelSaldo = new GroupLayout(panelSaldo);
		gl_panelSaldo.setHorizontalGroup(gl_panelSaldo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSaldo.createSequentialGroup().addGap(42).addComponent(lblSaldoDisponible)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(fondos).addGap(12)
						.addComponent(btnAadirFondos).addGap(27)));
		gl_panelSaldo.setVerticalGroup(gl_panelSaldo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSaldo.createSequentialGroup().addGap(9)
						.addGroup(gl_panelSaldo.createParallelGroup(Alignment.BASELINE).addComponent(lblSaldoDisponible)
								.addComponent(btnAadirFondos).addComponent(fondos))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelSaldo.setLayout(gl_panelSaldo);
		btnAadirFondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(u.getSaldo() < 400) {
					BaseDeDatos.ainadirDinero(u, 100);
					fondos.setText(Double.toString(BaseDeDatos.verUsuario(u).getSaldo()));
					JOptionPane.showMessageDialog(new JFrame(), "Has añadido 100 euros");
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "No puedes añadir mas de 350 euros");
				}
			}
		});
		JPanel panelj = new JPanel();
		panelj.setLayout(new GridLayout(0, 5));

		for (int i = 0; i < j.size(); i++) {
			panelj.add(j.get(i).getCaratula());
		}

		JPanel panelBuscar = new JPanel();

		JButton btnBuscarJuego = new JButton("Buscar Juego");
		btnBuscarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> gamesList = new ArrayList<String>();
				for (int i = 0; i < j.size(); i++) {
					gamesList.add(j.get(i).getNombre().toUpperCase());
					j.get(i).getCaratula().setVisible(false);
				}
				String searching = comp.getText();
				for (int i = 0; i < gamesList.size(); i++) {
					if (gamesList.get(i).contains(searching.toUpperCase())) {
						j.get(i).getCaratula().setVisible(true);
					}
				}
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(5)
						.addComponent(comp, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(btnBuscarJuego).addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(6)
						.addGroup(gl_panel_2
								.createParallelGroup(Alignment.BASELINE).addComponent(comp, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscarJuego))));
		panel_2.setLayout(gl_panel_2);

		JComboBox<String> comboBox = new JComboBox<String>(categorias);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> gamesList = new ArrayList<String>();
				for (int i = 0; i < j.size(); i++) {
					gamesList.add(j.get(i).getCategoria().toUpperCase());
					j.get(i).getCaratula().setVisible(false);
				}
				String searching = comboBox.getSelectedItem().toString();
				if(comboBox.getSelectedItem().equals("Todos")) {
					for(int i = 0; i < j.size(); i++) {
						j.get(i).getCaratula().setVisible(true);
					}
				}
				for (int i = 0; i < gamesList.size(); i++) {
					if (gamesList.get(i).contains(searching.toUpperCase())) {
						j.get(i).getCaratula().setVisible(true);
					}
				}
			}

		});

		JLabel lblBuscarPorCategoria = new JLabel("Categoria");
		lblBuscarPorCategoria.setVerticalAlignment(SwingConstants.TOP);
		
		GroupLayout gl_panelBuscar = new GroupLayout(panelBuscar);
		gl_panelBuscar.setHorizontalGroup(gl_panelBuscar.createParallelGroup(Alignment.LEADING).addGroup(gl_panelBuscar
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelBuscar.createParallelGroup(Alignment.LEADING).addComponent(lblBuscarPorCategoria)
						.addComponent(comboBox, 0, 104, Short.MAX_VALUE).addComponent(btnNewButton))
				.addContainerGap()));
		gl_panelBuscar.setVerticalGroup(gl_panelBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscar.createSequentialGroup().addGap(13)
						.addComponent(lblBuscarPorCategoria, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGap(46).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(57).addComponent(btnNewButton).addGap(90)));
		panelBuscar.setLayout(gl_panelBuscar);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel_3,
				GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JButton btnAtras_1 = new JButton("Atras");
		btnAtras_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VTienda.this.dispose();
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addGap(7)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(panelj, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_3.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(panelBuscar,
												GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING,
										gl_panel_3.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(btnAtras_1).addGap(17))))
						.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(panelSaldo,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(18, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addGap(7)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panelSaldo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panelj, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addContainerGap())
						.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panelBuscar, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
								.addComponent(btnAtras_1).addGap(27)))));

		panel_3.setLayout(gl_panel_3);
		contentPane.setLayout(gl_contentPane);

	}

	/**
	 * Diseño de la ventana tienda
	 * @param numero de juegos
	 * @return dimension para la ventana
	 */
	public static Dimension calcularPanel(int numJ) {
		Dimension resultado = new Dimension(500, 0);

		if (numJ % 5 == 0) {
			resultado.setSize(resultado.getWidth(), (numJ / 5) * 100);
		} else {
			int resto;
			int rows;
			resto = numJ % 5;
			rows = numJ - resto;
			resultado.setSize(resultado.getWidth(), (rows / 5) * 100);
		}
		return resultado;
	}
	
}
