package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel botones;
	private JPanel labels;
	private JButton btnTienda;

	/**
	 * Create the frame.
	 * @param usuario
	 */
	public V_principal(Usuario u) {
		setTitle("Pagina principal "+u.getNombre());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/3, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.NORTH);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		labels = new JPanel();
		getContentPane().add(labels, BorderLayout.CENTER);
		
		JLabel lNombre = new JLabel("Nombre "+u.getNombre());
		JLabel lApellido = new JLabel("Apellido "+u.getApellido());
		JLabel lEmail = new JLabel("Email "+u.getEmail());
		JLabel lFecha = new JLabel("Fecha "+u.getFecha_naci());
		JLabel lPass = new JLabel("Password "+u.getPassword());
		JLabel lSaldo = new JLabel("Saldo "+u.getSaldo());
		JLabel lJuegos = new JLabel("Numero de Juegos "+u.getNumeroDeJuegos());
		
		labels.add(lNombre);
		labels.add(lApellido);
		labels.add(lEmail);
		labels.add(lFecha);
		labels.add(lPass);
		labels.add(lSaldo);
		labels.add(lJuegos);
		
		botones = new JPanel();
		getContentPane().add(botones, BorderLayout.SOUTH);
		
		JButton btnAtras = new JButton("Atras");
		botones.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new V_inicio();
				V_principal.this.dispose();
			}
		});
		
		btnTienda = new JButton("Tienda");
		btnTienda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new V_Tienda(u);
				V_principal.this.dispose();
			}
		});
		contentPane.add(btnTienda);
		
		JButton btnBiblioteca = new JButton("Biblioteca");
		btnBiblioteca.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new V_Biblioteca(u);
				V_principal.this.setVisible(false);
			}
		});
		contentPane.add(btnBiblioteca);
		
		JButton btnAmigos = new JButton("Amigos de "+u.getNombre());
		contentPane.add(btnAmigos);
		btnAmigos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new V_Amigos(u);
				V_principal.this.dispose();
			}
		});
		
		JLabel lblSaldo = new JLabel("Saldo: ");
		contentPane.add(lblSaldo);
		
		JLabel label = new JLabel(Double.toString(u.getSaldo()));
		contentPane.add(label);
		
		System.out.println(u.getSaldo()+" - "+u.getNumeroDeJuegos());
	}

}
