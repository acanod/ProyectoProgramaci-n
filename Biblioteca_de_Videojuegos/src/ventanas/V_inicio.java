package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class V_inicio extends JFrame {

	private static final long serialVersionUID = 1L;

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
		JPanel panelCentral = new JPanel(new FlowLayout());
		JPanel panelInfo = new JPanel(new GridLayout(2, 0));
		JPanel panelBotones = new JPanel(new FlowLayout());
		
		//Componentes
		JTextField nombre = new JTextField(35);
		JPasswordField password = new JPasswordField(35);
		JLabel lnombre = new JLabel("Nombre");
		JLabel lpassword = new JLabel("Contraseña");
		JButton bIniciar = new JButton("Iniciar sesión");
		
		bIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//verificacion con la base de datos
				if(nombre.equals("Nombre") && password.equals("Apellido")) {
					//nombre como argumento de ventana principal por cada cuenta
					
					new V_principal( ).setVisible(true);
					V_inicio.this.setVisible(false);
					
				}
			}
		});
		JButton bRegistrar = new JButton("Registrar");
		bRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new V_registro().setVisible(true);
				V_inicio.this.setVisible(false);
			}
		});
		JButton bAtras = new JButton("Atrás");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				V_inicio.this.dispose();
			}
		});

		// Asignacion de componentes a contenedores
		panelInfo.add(lnombre);
		panelInfo.add(nombre);
		panelInfo.add(lpassword);
		panelInfo.add(password);
		panelBotones.add(bIniciar);
		panelBotones.add(bRegistrar);
		panelBotones.add(bAtras);
		panelCentral.add(panelInfo);

		add(panelCentral, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		bRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				V_inicio.this.setVisible(false);
				new V_registro();
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
