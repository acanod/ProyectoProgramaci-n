package ventanas;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class V_registro extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public void ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setVisible(true);
		setLocationRelativeTo(null);
		componentes();
	}
	
	public void componentes() {
		JPanel panelTexto = new JPanel();
		JTextArea nombre = new JTextArea();
		JPasswordField password = new JPasswordField();
		panelTexto.add(nombre);
		panelTexto.add(password);
		add(panelTexto, BorderLayout.NORTH);
	}
}
