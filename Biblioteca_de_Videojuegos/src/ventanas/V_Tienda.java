package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class V_Tienda extends JFrame {

	private static final long serialVersionUID = 1L;
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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		List<Juego> juegos= new ArrayList<Juego>();
		juegos.add(new Juego("Assasins Creed Origins", 16,"Aventura",50.00,false));
		juegos.add(new Juego("Fifa 20", 12,"Deportes",60.00,false));
		juegos.add(new Juego("Total War Shogun 2", 16,"Estrategia",30.00,false));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[208px,grow][129px][113px]", "[33px][grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, "cell 0 0,alignx left,aligny top");
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnBuscarJuego = new JButton("Buscar Juego");
		panel_2.add(btnBuscarJuego);
		
		JPanel panel = new JPanel();
		panel_3.add(panel, "cell 1 0,alignx left,aligny center");
		
		JLabel lblSaldoDisponible = new JLabel("Saldo disponible: ");
		panel.add(lblSaldoDisponible);
		
		JLabel label = new JLabel("00,00");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, "cell 2 0,alignx left,aligny top");
		
		JButton btnAadirFondos = new JButton("A\u00F1adir Fondos");
		panel_1.add(btnAadirFondos);
		  
		JPanel panelJuegos = new JPanel();
		panel_3.add(panelJuegos, "cell 0 1 2 1,grow");
		panelJuegos.setLayout(new MigLayout("", "[][][][][][][][][][][][][][]", "[]"));
		
		JLabel lblJuego = new JLabel("");
		lblJuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(0)).setVisible(true);
				
			}
		});
		panelJuegos.add(lblJuego, "cell 0 0");
		ImageIcon imageIcon = new ImageIcon("./img/1.jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		lblJuego.setIcon(imageIcon);
		
		JLabel lblJuego2 = new JLabel("");
		lblJuego2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(1)).setVisible(true);
				
			}
		});
		panelJuegos.add(lblJuego2, "cell 1 0");
		ImageIcon imageIcon2 = new ImageIcon("./img/2.jpg");
		Image image2 = imageIcon2.getImage();
		Image newimg2 = image2.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon2 = new ImageIcon(newimg2);  
		lblJuego2.setIcon(imageIcon2);
		
		
		JLabel lblJuego3 = new JLabel("");
		lblJuego3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(2)).setVisible(true);
				
			}
		});
		panelJuegos.add(lblJuego3, "cell 2 0");
		ImageIcon imageIcon3 = new ImageIcon("./img/3.jpg");
		Image image3 = imageIcon3.getImage();
		Image newimg3 = image3.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon3 = new ImageIcon(newimg3);  
		lblJuego3.setIcon(imageIcon3);
		

	}

}
