package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class V_JuegoInfo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_JuegoInfo frame = new V_JuegoInfo(null, null);
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
	 * @param j 
	 */
	public V_JuegoInfo(Juego j, Usuario u) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel precio = new JLabel(j.getNombre());
		contentPane.add(precio, BorderLayout.CENTER);
		
		JButton comprar = new JButton("Comprar");
		comprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.setSaldo(u.getSaldo()-j.getPrecio());
				//V_Tienda().repaint();
			}
		});
		contentPane.add(comprar, BorderLayout.SOUTH);
		
		JLabel nombre = new JLabel(Double.toString(j.getPrecio()));
		contentPane.add(nombre, BorderLayout.EAST);
	}

	
}
