package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;
import datos.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	 */
	public V_JuegoInfo(Juego j, Usuario u) {
		setTitle(j.getNombre());
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
				for(int i=0;i<u.getJuegosComprados().length;i++) {
					if((u.getJuegosComprados()[i]==(j.getNombre()))) {
						JFrame f;   
					    f=new JFrame();  
					    JOptionPane.showMessageDialog(f,"Ya tienes este juego.");
					    break;
					}else {
					if(u.getSaldo()<j.getPrecio()) {
					JFrame f;   
				    f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"No tienes suficiente dinero para comprar este juego"); 
				    break;
				}else {
					u.setSaldo(u.getSaldo()-j.getPrecio());
					u.setJuegosComprados(i,j.getNombre());
					JFrame f;   
				    f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"Has comprado "+j.getNombre());
				    break;
					
				}
				
				    }
					 }
			}
		});
		contentPane.add(comprar, BorderLayout.SOUTH);
		
		JLabel nombre = new JLabel(Double.toString(j.getPrecio()));
		contentPane.add(nombre, BorderLayout.EAST);
	}

}
