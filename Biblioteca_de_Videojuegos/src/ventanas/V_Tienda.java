package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Juego;
import datos.Usuario;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
					V_Tienda frame = new V_Tienda(null);
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
	
	public V_Tienda(Usuario u) {
		setTitle ("Tienda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		List<Juego> juegos= new ArrayList<Juego>();
	
		
		String[] categorias = {"Todos", "Deportes","Aventura","Estrategia"};
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[208px,grow][129px][][113px,grow]", "[33px][grow]"));
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, "cell 0 0,alignx left,aligny top");
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		
		
		JPanel panel = new JPanel();
		panel_3.add(panel, "cell 1 0,alignx left,aligny center");
		
		JLabel lblSaldoDisponible = new JLabel("Saldo disponible: ");
		panel.add(lblSaldoDisponible);
		
		
		JLabel fondos = new JLabel(Double.toString(u.getSaldo()));
		panel.add(fondos);
		
		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, "cell 3 0,alignx left,aligny top");
		
		JButton btnAadirFondos = new JButton("A\u00F1adir Fondos");
		btnAadirFondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(u.getSaldo()<500) {
					u.setSaldo(u.getSaldo()+10);
				fondos.setText(Double.toString(u.getSaldo()));
				}else {
					JFrame f;   
					    f=new JFrame();  
					    JOptionPane.showMessageDialog(f,"No puedes añadir mas de 500 euros"); 
				}
				
			}
		});
		panel_1.add(btnAadirFondos);
		
		JPanel panelJuegos = new JPanel();
		panel_3.add(panelJuegos, "cell 0 1 2 1,grow");
		
		JLabel lblJuego = new JLabel("");
		lblJuego.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(0),u).setVisible(true);
				
			}
		});
		ImageIcon imageIcon = new ImageIcon("./img/1.jpg");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		lblJuego.setIcon(imageIcon);
		
		JLabel lblJuego2 = new JLabel("");
		lblJuego2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(1),u).setVisible(true);
				
			}
		});
		ImageIcon imageIcon2 = new ImageIcon("./img/2.jpg");
		Image image2 = imageIcon2.getImage();
		Image newimg2 = image2.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon2 = new ImageIcon(newimg2);  
		lblJuego2.setIcon(imageIcon2);
		
		
		JLabel lblJuego3 = new JLabel("");
		lblJuego3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {		
				new V_JuegoInfo(juegos.get(2),u).setVisible(true);
				
			}
		});
		ImageIcon imageIcon3 = new ImageIcon("./img/3.jpg");
		Image image3 = imageIcon3.getImage();
		Image newimg3 = image3.getScaledInstance(100, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon3 = new ImageIcon(newimg3);  
		lblJuego3.setIcon(imageIcon3);
		
		JLabel lblJuego4 = new JLabel("");
		
		JLabel lblJuego5 = new JLabel("");
		
		juegos.add(new Juego("Assasins Creed Origins", 16,"Aventura",50.00,false,lblJuego));
		juegos.add(new Juego("Fifa 20", 12,"Deportes",60.00,false,lblJuego2));
		juegos.add(new Juego("Total War Shogun 2", 16,"Estrategia",30.00,false,lblJuego3));
		GroupLayout gl_panelJuegos = new GroupLayout(panelJuegos);
		gl_panelJuegos.setHorizontalGroup(
			gl_panelJuegos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelJuegos.createSequentialGroup()
					.addGap(7)
					.addComponent(lblJuego)
					.addGap(36)
					.addComponent(lblJuego2)
					.addGap(32)
					.addComponent(lblJuego3)
					.addGap(32)
					.addComponent(lblJuego4))
		);
		gl_panelJuegos.setVerticalGroup(
			gl_panelJuegos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelJuegos.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panelJuegos.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJuego)
						.addComponent(lblJuego2)
						.addComponent(lblJuego3)
						.addComponent(lblJuego4)))
		);
		panelJuegos.setLayout(gl_panelJuegos);
		
		
		JButton btnBuscarJuego = new JButton("Buscar Juego");
		btnBuscarJuego.addActionListener(new ActionListener() {
			@SuppressWarnings("unlikely-arg-type")
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals(null)) {
					for(int i=0;i<juegos.size();i++) {
						
					}
				}
				if(textField.getText().equals(juegos)) {

					System.out.println(lblJuego.getText());
					lblJuego2.setVisible(false);
					
				}
			}
		});
		panel_2.add(btnBuscarJuego);
		
		JPanel panelBuscar = new JPanel();
		panel_3.add(panelBuscar, "cell 3 1,grow");
		
		JComboBox comboBox = new JComboBox(categorias);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i =0; i<juegos.size();i++) {
					juegos.get(i).getCaratula().setVisible(true);
					}
				if(!comboBox.getSelectedItem().equals("Todos")) {
				for(int i =0; i<juegos.size();i++) {
					
					if(!juegos.get(i).getCategoria().equals(comboBox.getSelectedItem().toString())) {
				juegos.get(i).getCaratula().setVisible(false);
					
					}
					
					
					}
			}
			}});
		
		JLabel lblBuscarPorCategoria = new JLabel("Categoria");
		lblBuscarPorCategoria.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			new V_principal(u).setVisible(true);
			V_Tienda.this.dispose();	
			}
		});
		GroupLayout gl_panelBuscar = new GroupLayout(panelBuscar);
		gl_panelBuscar.setHorizontalGroup(
			gl_panelBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBuscar.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBuscarPorCategoria, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
						.addComponent(btnNewButton)
						.addComponent(comboBox, 0, 120, Short.MAX_VALUE)
						.addComponent(btnAtras, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panelBuscar.setVerticalGroup(
			gl_panelBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBuscar.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuscarPorCategoria, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnNewButton)
					.addGap(157)
					.addComponent(btnAtras)
					.addGap(36))
		);
		panelBuscar.setLayout(gl_panelBuscar);
		

	}
}
