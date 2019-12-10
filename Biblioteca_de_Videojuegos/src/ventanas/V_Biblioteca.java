package ventanas;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class V_Biblioteca extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_Biblioteca frame = new V_Biblioteca(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public V_Biblioteca(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_principal(u).setVisible(true);
				V_Biblioteca.this.setVisible(false);
			}
		});
		
		JPanel panelJuegos = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton))
				.addComponent(panelJuegos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panelJuegos, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		GridBagLayout gbl_panelJuegos = new GridBagLayout();
		gbl_panelJuegos.columnWidths = new int[]{195, 33, 0};
		gbl_panelJuegos.rowHeights = new int[]{14, 0};
		gbl_panelJuegos.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelJuegos.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelJuegos.setLayout(gbl_panelJuegos);
		JLabel nh = new JLabel("No hay");
		GridBagConstraints gbc_nh = new GridBagConstraints();
		gbc_nh.anchor = GridBagConstraints.NORTHWEST;
		gbc_nh.gridx = 1;
		gbc_nh.gridy = 0;
		panelJuegos.add(nh, gbc_nh);
		contentPane.setLayout(gl_contentPane);
		
		if (u.getJuegosComprados().size() == 0) {
			System.out.println("No hay");
		} else {
			for (int i = 1; i <= u.getJuegosComprados().size(); i++) {
		//		JLabel juegos[] = new JLabel[];
		//		juegos[i].setText(u.getJuegosComprados().get(i).getNombre());
				panelJuegos.add(u.getJuegosComprados().get(i).getCaratula());
				System.out.println(u.getJuegosComprados().get(i).getNombre());
			}
		}
	}
}