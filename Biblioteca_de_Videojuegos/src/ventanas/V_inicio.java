package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import base_de_datos.*;
import datos.Usuario;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		JPanel panelCentral = new JPanel();
		JPanel panelBotones = new JPanel(new FlowLayout());
		JButton bIniciar = new JButton("Iniciar sesión");
		
		
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
		panelBotones.add(bIniciar);
		panelBotones.add(bRegistrar);
		panelBotones.add(bAtras);

		getContentPane().add(panelCentral, BorderLayout.CENTER);
		JLabel lpassword = new JLabel("Contraseña");
		JPasswordField password = new JPasswordField(35);
		JLabel lnombre = new JLabel("Nombre");
		
		//Componentes
		JTextField nombre = new JTextField(100);
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.TRAILING)
						.addComponent(lnombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
						.addComponent(lpassword, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nombre, 0, 0, Short.MAX_VALUE)
						.addComponent(password, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
					.addGap(16))
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap(71, Short.MAX_VALUE)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lnombre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(lpassword, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41))
		);
		panelCentral.setLayout(gl_panelCentral);
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
				Usuario iniciar = new Usuario(nombre.getText(), password.getPassword().toString());
				BaseDeDatos.comprobarLogin(iniciar);
				new V_principal(iniciar).setVisible(true);
				V_inicio.this.setVisible(false);
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
