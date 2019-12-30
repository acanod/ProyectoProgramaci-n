package ventanas;

import java.awt.BorderLayout;

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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public V_JuegoInfo(Juego j, Usuario u, V_Tienda t) {
		setTitle(j.getNombre());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel precio = new JLabel(j.getNombre());
		contentPane.add(precio, BorderLayout.CENTER);
		
		JButton jugar = new JButton("Jugar");
		jugar.setEnabled(false);
		contentPane.add(jugar, BorderLayout.SOUTH);
		
		JButton comprar = new JButton("Comprar");
		comprar.setEnabled(true);
		contentPane.add(comprar, BorderLayout.SOUTH);
		
		if(!(u.getJuegosComprados().size()==0)) {
			for (int i = 0; i < u.getJuegosComprados().size(); i++) {
			if ((u.getJuegosComprados().get(i).getNombre()== (j.getNombre()))) {
				comprar.setVisible(false);
				jugar.setEnabled(true);
				contentPane.add(jugar, BorderLayout.SOUTH);
			} 
			}
			}
		
		comprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < u.getNumeroDeJuegos(); i++) {
					if ((u.getJuegosComprados().size()!=0 && u.getJuegosComprados().get(i).getNombre()== (j.getNombre()))) {
						JFrame f;
						f = new JFrame();
						JOptionPane.showMessageDialog(f, "Ya tienes este juego.");
						
						break;	
					} else {
						if (u.getSaldo() < j.getPrecio()) {
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "No tienes suficiente dinero para comprar este juego");
							break;
						} else {
							u.setSaldo(u.getSaldo() - j.getPrecio());
							u.setJuegosComprados(i, j);
							int ndjc= u.getNumeroDeJuegos();
							u.setNumeroDeJuegos(ndjc++);
							t.fondos.setText(Double.toString(u.getSaldo()));
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "Has comprado " + j.getNombre());
							break;

						}

					}
				}
			}
		});
		
		JLabel nombre = new JLabel(Double.toString(j.getPrecio()));
		contentPane.add(nombre, BorderLayout.EAST);
		
	}

}
