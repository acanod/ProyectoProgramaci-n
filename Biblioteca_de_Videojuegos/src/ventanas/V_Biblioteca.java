package ventanas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class V_Biblioteca extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public V_Biblioteca(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setBounds(100, 100, 612, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Atras");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_principal(u);
				V_Biblioteca.this.dispose();
			}
		});

		JPanel panelJuegos = new JPanel();
		panelJuegos.setLayout(new GridLayout(1, 0, 0, 0));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(panelJuegos, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addComponent(panelJuegos, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(btnNewButton))
				);
		contentPane.setLayout(gl_contentPane);

		if(u.getJuegosComprados().size() != 0) {
			for(int i=0; i<u.getJuegosComprados().size();i++) {
				panelJuegos.add(u.getJuegosComprados().get(i).getCaratula());
			}
		} else {
			JOptionPane.showMessageDialog(null,"No tienes juegos comprados");
		}
	}
}
