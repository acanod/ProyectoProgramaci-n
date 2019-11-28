package ventanas;

import java.awt.*;
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
		panelLista.setBorder(BorderFactory.createTitledBorder("Detalles"));

		JButton btnAinadir = new JButton("Añadir");
		JButton btnAtras = new JButton("Atras");

		c.fill=GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0; c.weighty = 0.0;
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER; c.gridheight = 1;
		container.add(new JToolBar(), c);

		c.fill = GridBagConstraints. NONE;
		c.insets = new Insets(2,2,6,2);

		c.gridx=0; c.gridy = 1;
		c.gridwidth= 1; c.gridheight = 1;
		c.anchor=GridBagConstraints.EAST;
		container.add(new JLabel("Servidor"),c);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor= GridBagConstraints. WEST;
		container.add(new JTextField(), c);

		c.gridx = 4;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = GridBagConstraints.RELATIVE;
		c.anchor = GridBagConstraints.EAST;
		container.add(new JLabel("Estado:"), c);

		c.gridx = 5;
		c.gridwidth = GridBagConstraints. REMAINDER;
		c.anchor = GridBagConstraints.WEST;
		container.add(new JLabel("Desconectado"), c);

		c.gridx = 0; c.gridy = 2;
		c.weighty= 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2; c.gridheight = GridBagConstraints.RELATIVE;
		container.add(panelArbol, c);

		c.gridx = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		container.add(panelLista, c);

		c.weighty = 0.0;
		c.gridx = 0; c.gridy = 3;
		c.gridheight = GridBagConstraints.RELATIVE;
		container.add(btnAinadir, c);
		
		c.weighty = 0.0;
		c.gridx = 2; c.gridy = 3;
		c.gridheight = GridBagConstraints.REMAINDER;
		container.add(btnAtras, c);
	}
	
}
