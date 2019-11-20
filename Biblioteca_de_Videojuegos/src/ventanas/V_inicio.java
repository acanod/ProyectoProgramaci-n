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
		JPanel panelLabels = new JPanel();
		JPanel panelBotones = new JPanel(new FlowLayout());
		
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.Y_AXIS));
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
		
		panelTexto.setMinimumSize(new Dimension().getSize());
		
		nombre = new JTextField(30);
		password = new JPasswordField(30);
		
		nombre.setMinimumSize(new Dimension().getSize());
		JButton bIniciar = new JButton("Iniciar sesión");
		JButton bRegistrar = new JButton("Registrar");
		JButton bAtras = new JButton("Atrás");
		
		JLabel lpassword = new JLabel("Contraseña");
		JLabel lnombre = new JLabel("Nombre");
		
		panelTexto.add(nombre);
		panelTexto.add(password);
		
		float y = panelLabels.getAlignmentY();
		panelLabels.add(lnombre, y/3);
		panelLabels.add(lpassword, y);
		
		panelBotones.add(bIniciar);
		panelBotones.add(bRegistrar);
		panelBotones.add(bAtras);

		getContentPane().add(panelTexto, BorderLayout.CENTER);
		getContentPane().add(panelLabels, BorderLayout.WEST);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		//panelCentral.setLayout(gl_panelCentral);
		
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
					Usuario iniciar = new Usuario(nombre.getText(), password.getPassword().toString());
					BaseDeDatos.comprobarLogin(iniciar);
					new V_principal(iniciar).setVisible(true);
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
