package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class V_Tienda extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V_Tienda frame = new V_Tienda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public V_Tienda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[556px]", "[372px]"));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(new MigLayout("", "[208px,grow][129px][113px]", "[33px][grow]"));
		panel_3.setLayout(new MigLayout("", "[1px]", "[1px]"));
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, "cell 0 0,grow");
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel_3.add(panel, "cell 0 0,grow");
		
		JLabel lblSaldoDisponible = new JLabel("Saldo disponible: ");
		panel.add(lblSaldoDisponible);
		
		JLabel label = new JLabel("00,00");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, "cell 0 0,grow");
		
		JButton btnAadirFondos = new JButton("A\u00F1adir Fondos");
		panel_1.add(btnAadirFondos);
		
		JPanel panelJuegos = new JPanel();
		panel_3.add(panelJuegos, "cell 0 0,alignx center,aligny center");
		panelJuegos.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JLabel lblJuego = new JLabel("Juego1");
		panelJuegos.add(lblJuego, "cell 0 0");
		
		JLabel lblJuego_1 = new JLabel("Juego 2");
		panelJuegos.add(lblJuego_1, "cell 1 0");
		
		JLabel lblJuego_2 = new JLabel("Juego 3+");
		panelJuegos.add(lblJuego_2, "cell 2 0");
	}

}