package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import base_de_datos.*;
import datos.Usuario;

public class V_inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JPasswordField password;
	
	public V_inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 4);
		setLocationRelativeTo(null);
		setTitle("Inicio");
		setResizable(false);
		componentes();
		setVisible(true);
	}

	private void componentes() {
		// Contenedores
		JPanel panelTexto = new JPanel();
		panelTexto.setLayout(new GridBagLayout());
		JPanel panelBotones = new JPanel(new FlowLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel lnombre = new JLabel("Nombre");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		panelTexto.add(lnombre, constraints);
		constraints.weighty = 0.0;
		
		JLabel lpassword = new JLabel("Contraseña");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		panelTexto.add(lpassword, constraints);
		constraints.weighty = 0.0;
		
		nombre = new JTextField(30);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		panelTexto.add(nombre, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		
		password = new JPasswordField(30);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		panelTexto.add(password, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		
		JButton bIniciar = new JButton("Iniciar sesión");
		JButton bRegistrar = new JButton("Registrar");
		JButton bAtras = new JButton("Atrás");
		
		
		panelBotones.add(bIniciar);
		panelBotones.add(bRegistrar);
		panelBotones.add(bAtras);

		getContentPane().add(panelTexto, BorderLayout.CENTER);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		bRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_inicio.this.setVisible(false);
				new V_registro();
			}
		});
		bIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] contra = password.getPassword();
				String contr = new String(contra);
				if(nombre.getText().isBlank() || contr.length()==0) {
					JOptionPane.showMessageDialog(null, "Escriba en todos los parametros", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Usuario iniciar = new Usuario(nombre.getText(), contr);
					Usuario inicio = BaseDeDatos.comprobarLogin(iniciar);
					new V_principal(inicio).setVisible(true);
					V_inicio.this.setVisible(false);
				}
			}
		});
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_inicio.this.dispose();
			}
		});
		
	}
}
