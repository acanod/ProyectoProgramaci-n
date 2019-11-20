package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;
import datos.Usuario;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class V_principal extends JFrame {

	private JPanel contentPane;
//	public List<Usuario> users= new ArrayList<Usuario>(new Usuario("Ima","Ola","e@opp.es","ESP", "ERT", 0, 30.00));
//	public Usuario u=new Usuario("Ima","Ola","e@opp.es","ESP", "ERT", 0, 30.00);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_principal frame = new V_principal(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param u 
	 */
	public V_principal(Usuario u) {
		setTitle("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));

		
		
		
		JButton btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_Tienda(u).setVisible(true);
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
		
		JLabel label = new JLabel(Double.toString(u.getSaldo()));
		contentPane.add(label);
	}

}