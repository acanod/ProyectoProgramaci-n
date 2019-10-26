package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class V_registro extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public V_registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/3, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setLocationRelativeTo(null);
		setTitle("Inicio");
		setResizable(false);
		componentes();
		setVisible(true);
	}
	
	private void componentes() {
		this.getContentPane().setLayout(new GridBagLayout());
		JLabel lnombre = new JLabel("Nombre");
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; // El área de texto empieza en la columna cero.
		constraints.gridy = 0; // El área de texto empieza en la fila cero
		constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.WEST;
		this.getContentPane().add (lnombre, constraints);
		
		JTextArea nombre = new JTextArea ("nombre");
		constraints.gridx = 1; // El área de texto empieza en la columna uno.
		constraints.gridy = 0; // El área de texto empieza en la fila cero
		constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0.5; // La fila 0 debe estirarse, le ponemos un 1.0
		this.getContentPane().add (nombre, constraints);
		constraints.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
		
		JLabel lapellido = new JLabel("Apellido");
		constraints.gridx = 0; // El área de texto empieza en la columna cero.
		constraints.gridy = 1; // El área de texto empieza en la fila cero
		constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.WEST;
		this.getContentPane().add (lapellido, constraints);
		
		JTextArea apellido = new JTextArea ("apellido");
		constraints.gridx = 1; // El área de texto empieza en la columna uno.
		constraints.gridy = 1; // El área de texto empieza en la fila cero
		constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0; // La fila 1 debe estirarse, le ponemos 1.0
		this.getContentPane().add (apellido, constraints);
		constraints.weighty = 0.5; // Restauramos el valor por defecto.
		
		JLabel lemail = new JLabel("Email");
		constraints.gridx = 0; // El área de texto empieza en la columna cero.
		constraints.gridy = 2; // El área de texto empieza en la fila cero
		constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.WEST;
		this.getContentPane().add (lemail, constraints);
		
		JTextArea email = new JTextArea ("email");
		constraints.gridx = 1; // El área de texto empieza en la columna uno.
		constraints.gridy = 2; // El área de texto empieza en la fila cero
		constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0.5; // La fila 1 debe estirarse, le ponemos 1.0
		this.getContentPane().add (email, constraints);
		constraints.weighty = 0.0; // Restauramos el valor por defecto.
		
		JLabel lpais = new JLabel("Pais");
		constraints.gridx = 0; // El área de texto empieza en la columna cero.
		constraints.gridy = 3; // El área de texto empieza en la fila cero
		constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.WEST;
		this.getContentPane().add (lpais, constraints);
		
		JComboBox<String> pais = new JComboBox<String>();
		pais.addItem("Alemania");
		pais.addItem("España");
		pais.addItem("Francia");
		pais.addItem("Italia");
		pais.addItem("Portugal");
		constraints.gridx = 1; // El área de texto empieza en la columna uno.
		constraints.gridy = 3; // El área de texto empieza en la fila cero
		constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 1 fila.
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0.5; // La fila 1 debe estirarse, le ponemos 1.0
		this.getContentPane().add (pais, constraints);
		constraints.weighty = 0.0; // Restauramos al valor por defecto, para no afectar a los siguientes componentes.
		
		pais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//setText(pais.getSelectedItem().toString());
			}
		});
	}
}
