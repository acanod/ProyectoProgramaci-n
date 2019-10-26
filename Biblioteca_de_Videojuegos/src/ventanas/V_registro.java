package ventanas;

import java.awt.*;
import javax.swing.*;

public class V_registro extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/4);
		setLocationRelativeTo(null);
		setTitle("Inicio");
		setResizable(false);
		componentes();
		setVisible(true);
	}
	
	public void componentes() {
		//Contenedores
		JPanel panelCentral = new JPanel(new FlowLayout());
		JPanel panelInfo = new JPanel(new GridLayout(2, 0));
		JPanel panelBotones = new JPanel(new FlowLayout());
		
		//Componentes
		JTextArea nombre = new JTextArea();
		JPasswordField password = new JPasswordField();
		JLabel lnombre = new JLabel("Nombre");
		JLabel lpassword = new JLabel("Contraseña");
		JButton bIniciar = new JButton("Iniciar sesión");
		JButton bRegistrar = new JButton("Registrar");
		JButton bAtras = new JButton("Atrás");
		
		//Asignacion de componentes a contenedores
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
	}
}
