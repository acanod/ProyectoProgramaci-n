package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import datos.Usuario;

public class V_Amigos extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public V_Amigos(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/3);
		setLocationRelativeTo(null);
		setTitle("Amigos");
		setResizable(true);
		componentes(u);
		setVisible(true);
	}
	
	public void componentes(Usuario u) {
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		((JPanel)container).setBorder(BorderFactory.createTitledBorder(u.getNombre()));
		GridBagConstraints c = new GridBagConstraints ();

		JToolBar toolbar = new JToolBar();
		for (int i = 0; i<10; i++)
		toolbar.add(new JButton("<" + i + ">"));

		JScrollPane panelArbol = new JScrollPane(new JList<Usuario>());
		panelArbol.setBorder(BorderFactory.createTitledBorder("Lista de amigos"));

		JScrollPane panelLista = new JScrollPane(new JList<Usuario>());
		panelLista.setBorder(BorderFactory.createTitledBorder("Usuarios"));

		JButton btnAinadir = new JButton("Añadir");
		JButton btnAtras = new JButton("Atras");

		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0; c.weighty = 0.0;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER; c.gridheight = 1;
		

		c.fill = GridBagConstraints. NONE;
		c.insets = new Insets(2,2,6,2);

		c.gridx=0; c.gridy = 1;
		c.gridwidth= 1; c.gridheight = 1;
		c.anchor=GridBagConstraints.EAST;
		container.add(new JLabel("Buscar nombre de amigo:"), c);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor= GridBagConstraints. WEST;
		container.add(new JTextField(), c);

		c.gridx = 0; c.gridy = 2;
		c.weighty= 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2; c.gridheight = GridBagConstraints.RELATIVE;
		container.add(panelArbol, c);

		c.gridx = 2;
		c.gridwidth = GridBagConstraints.RELATIVE;
		container.add(panelLista, c);

		c.gridx = 0; c.gridy = 3;
		c.gridwidth = GridBagConstraints.BOTH;;
		c.gridheight = GridBagConstraints.BOTH;
		container.add(btnAinadir, c);
		
		c.gridx = 2; 
		container.add(btnAtras, c);
		
		btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				V_Amigos.this.dispose();
				new V_principal(u).setVisible(true);;
			}
		});
	}
	
}
