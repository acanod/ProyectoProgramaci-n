package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class V_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_principal frame = new V_principal();
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
	public V_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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