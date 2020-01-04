package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class V_Tienda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField comp;
	private String[] categorias = { "Todos", "Deportes", "Aventura", "Estrategia", "Acción" };
	public JLabel fondos = new JLabel("");

	public V_Tienda(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Tienda");
		setVisible(true);
		setSize(640, 421);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		List<Juego> juegos = new ArrayList<Juego>();

		JPanel panel_3 = new JPanel();

		JPanel panel_2 = new JPanel();

		comp = new JTextField();
		comp.setColumns(10);

		JLabel lblJuego = new JLabel("");
		lblJuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(0), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon = new ImageIcon("./img/1.jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		lblJuego.setIcon(imageIcon);

		JLabel lblJuego2 = new JLabel("");
		lblJuego2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(1), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon2 = new ImageIcon("./img/2.jpg");
		Image image2 = imageIcon2.getImage();
		Image newimg2 = image2.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon2 = new ImageIcon(newimg2);
		lblJuego2.setIcon(imageIcon2);

		JLabel lblJuego3 = new JLabel("");
		lblJuego3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(2), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon3 = new ImageIcon("./img/3.jpg");
		Image image3 = imageIcon3.getImage();
		Image newimg3 = image3.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon3 = new ImageIcon(newimg3);
		lblJuego3.setIcon(imageIcon3);

		JLabel lblJuego4 = new JLabel("");
		lblJuego4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(3), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon4 = new ImageIcon("./img/4.jpg");
		Image image4 = imageIcon4.getImage();
		Image newimg4 = image4.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon4 = new ImageIcon(newimg4);
		lblJuego4.setIcon(imageIcon4);

		JLabel lblJuego5 = new JLabel("");
		lblJuego5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(4), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon5 = new ImageIcon("./img/5.jpg");
		Image image5 = imageIcon5.getImage();
		Image newimg5 = image5.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon5 = new ImageIcon(newimg5);
		lblJuego5.setIcon(imageIcon5);

		JLabel lblJuego6 = new JLabel("");
		lblJuego6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new V_JuegoInfo(juegos.get(5), u, V_Tienda.this).setVisible(true);

			}
		});
		ImageIcon imageIcon6 = new ImageIcon("./img/6.jpg");
		Image image6 = imageIcon6.getImage();
		Image newimg6 = image6.getScaledInstance(100, 120, java.awt.Image.SCALE_SMOOTH);
		imageIcon6 = new ImageIcon(newimg6);
		lblJuego6.setIcon(imageIcon6);

		juegos.add(new Juego("Assasins Creed Origins", 16, "Aventura", 50.00, false, lblJuego));
		juegos.add(new Juego("Fifa 20", 12, "Deportes", 60.00, false, lblJuego2));
		juegos.add(new Juego("Total War Shogun 2", 16, "Estrategia", 30.00, false, lblJuego3));
		juegos.add(new Juego("Battlefield 1", 18, "Acción", 70.00, false, lblJuego4));
		juegos.add(new Juego("Resident Evil", 18, "Acción", 30.00, false, lblJuego5));
		juegos.add(new Juego("Devil May Cry", 18, "Aventura", 40.00, false, lblJuego6));

		JPanel panelSaldo = new JPanel();

		JLabel lblSaldoDisponible = new JLabel("Saldo disponible: ");

		fondos.setText(Double.toString(u.getSaldo()));

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
				if (u.getSaldo() < 500) {
					u.setSaldo(u.getSaldo() + 10);
					fondos.setText(Double.toString(u.getSaldo()));
				} else {
					JFrame f;
					f = new JFrame();
					JOptionPane.showMessageDialog(f, "No puedes añadir mas de 500 euros");
				}

			}
		});

		JPanel panelJuegos = new JPanel();
		panelJuegos.setLayout(new GridLayout(0, 4));

		panelJuegos.setAutoscrolls(false);
		panelJuegos.setAutoscrolls(true);

		for (int i = 0; i < juegos.size(); i++) {
			panelJuegos.add(juegos.get(i).getCaratula());
		}

		JPanel panelBuscar = new JPanel();

		JButton btnBuscarJuego = new JButton("Buscar Juego");
		btnBuscarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<String> gamesList = new ArrayList<String>();
				for (int i = 0; i < juegos.size(); i++) {
					gamesList.add(juegos.get(i).getNombre().toUpperCase());
					juegos.get(i).getCaratula().setVisible(false);
				}
				String searching = comp.getText();
				for (int i = 0; i < gamesList.size(); i++) {
					if (gamesList.get(i).contains(searching.toUpperCase())) {
						juegos.get(i).getCaratula().setVisible(true);

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
				for (int i = 0; i < juegos.size(); i++) {
					juegos.get(i).getCaratula().setVisible(true);

				}
				if (!comboBox.getSelectedItem().equals("Todos")) {
					for (int i = 0; i < juegos.size(); i++) {

						if (!juegos.get(i).getCategoria().equals(comboBox.getSelectedItem().toString())) {
							juegos.get(i).getCaratula().setVisible(false);

						}

					}
				}
			}
		});

		JLabel lblBuscarPorCategoria = new JLabel("Categoria");
		lblBuscarPorCategoria.setVerticalAlignment(SwingConstants.TOP);

		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_principal(u).setVisible(true);
				V_Tienda.this.dispose();
			}
		});
		GroupLayout gl_panelBuscar = new GroupLayout(panelBuscar);
		gl_panelBuscar.setHorizontalGroup(
			gl_panelBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBuscar.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBuscarPorCategoria, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
						.addComponent(btnNewButton)
						.addComponent(comboBox, 0, 69, Short.MAX_VALUE)
						.addComponent(btnAtras, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelBuscar.setVerticalGroup(
			gl_panelBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscar.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuscarPorCategoria, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnNewButton)
					.addGap(115)
					.addComponent(btnAtras)
					.addGap(24))
		);
		panelBuscar.setLayout(gl_panelBuscar);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel_3,
				GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3
				.createSequentialGroup().addGap(7)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_3.createSequentialGroup()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(panelSaldo,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
								.addComponent(panelJuegos, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelBuscar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(7, Short.MAX_VALUE)));
		gl_panel_3
				.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup().addGap(7)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(panelSaldo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addComponent(panelBuscar, 0, 0, Short.MAX_VALUE)
										.addComponent(panelJuegos, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
								.addGap(54)));
		panel_3.setLayout(gl_panel_3);
		contentPane.setLayout(gl_contentPane);

	}

}
