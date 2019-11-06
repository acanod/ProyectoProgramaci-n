package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class V_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public V_principal(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		setLocationRelativeTo(null);
		setTitle("Página principal de "+usuario.getNombre());
		setResizable(false);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		JButton btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_Tienda().setVisible(true);
				V_principal.this.setVisible(false);
			}
		});
		contentPane.add(btnTienda);
		
		JButton btnBiblioteca = new JButton("Biblioteca");
		contentPane.add(btnBiblioteca);
		
		JButton btnAmigos = new JButton("Amigos");
		contentPane.add(btnAmigos);
		
		JLabel lblSaldo = new JLabel("Saldo: ");
		contentPane.add(lblSaldo);
		
		JLabel label = new JLabel("00,00");
		contentPane.add(label);
	}

}