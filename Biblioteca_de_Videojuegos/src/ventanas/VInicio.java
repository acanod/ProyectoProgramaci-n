package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import datos.Gestion;
import datos.Usuario;

public class VInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombre;
	private JPasswordField password;
	
	public VInicio() {
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
				VInicio.this.setVisible(false);
				new VRegistro();
			}
		});
		bIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario inicio = null;
				try {
					inicio = Gestion.comprobarLogin(nombre, password);
				} catch (NullPointerException | SQLException e1) {
					e1.printStackTrace();
				}
				new VPrincipal(inicio, Gestion.todosLosUsuarios(), Gestion.todosLosJuegos()).setVisible(true);
				VInicio.this.setVisible(false);
			}
		});
		
		bAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VInicio.this.dispose();
			}
		});
		
	}
	
}