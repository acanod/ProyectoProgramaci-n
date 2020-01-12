package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;

import base_de_datos.BaseDeDatos;
import datos.Usuario;

public class V_registro extends JFrame{

	private static final long serialVersionUID = 1L;
	private String[] listaPais = {"Alemania", "España", "Francia", "Italia", "Portugal"};
	private final ArrayList<String> paises = new ArrayList<String>();
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JComboBox<String> pais;
	private JTextField fecha;
	private JCheckBox admin;
	
	public V_registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width/3, Toolkit.getDefaultToolkit().getScreenSize().height/2);
		setLocationRelativeTo(null);
		setTitle("Registro");
		setResizable(false);
		componentes();
		setVisible(true);
	}
	
	private void componentes() {
		GridBagConstraints constraints = new GridBagConstraints();
		
		this.getContentPane().setLayout(new GridBagLayout());

		JButton registrar = new JButton ("Registrar");
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.weighty = 0.05;
		this.getContentPane().add (registrar, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 0.0;

		JButton atras = new JButton ("Atras");
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.weighty = 0.05;
		this.getContentPane().add (atras, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 0.0;
		
		JLabel lnombre = new JLabel("Nombre");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lnombre, constraints);
		constraints.weighty = 0.0;
		
		nombre = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(nombre, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		
		JLabel lapellido = new JLabel("Apellido");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lapellido, constraints);
		constraints.weighty = 0.0;
		
		apellido = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(apellido, constraints);
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		
		JLabel lpassword = new JLabel("Contraseña");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lpassword, constraints);
		constraints.weighty = 0.0;
		
		JPasswordField password = new JPasswordField();
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(password, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.CENTER;
		
		JLabel lemail = new JLabel("Email");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lemail, constraints);
		constraints.weighty = 0.0;
		
		email = new JTextField();
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(email, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.CENTER;
		
		JLabel lpais = new JLabel("Pais");
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weighty = 0.05;
		getContentPane().add(lpais, constraints);
		constraints.weighty = 0.0;
		
		for (int i = 0; i < listaPais.length; i++) {
			paises.add(listaPais[i]);
		}
		pais = new JComboBox<String>();
		for(int i = 0; i < paises.size(); i++) {
			pais.addItem(paises.get(i));
		}
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0.05;
		getContentPane().add(pais, constraints);
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		
		JLabel lfecha = new JLabel("Fecha de nacimiento");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(lfecha, constraints);
		
		fecha = new JTextField("YYYY-MM-DD");
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(fecha, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.CENTER;
		
		JLabel lAdmin = new JLabel("Admin");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(lAdmin, constraints);
		
		admin = new JCheckBox();
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0.05;
		getContentPane().add(admin, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.CENTER;
		
		registrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				char[] contra = password.getPassword();
				String contr = new String(contra);
				
				boolean administrador;
				if(admin.isSelected()) {
					administrador = true;
				} else {
					administrador = false;
				}
				
				Usuario registrar = new Usuario(nombre.getText(), apellido.getText(), contr, fecha.getText(), email.getText(),
						pais.getSelectedItem().toString(), 0, 50, administrador);
				ArrayList<Usuario> lista = BaseDeDatos.verTodosUsuarios();
				try {
					if(lista.isEmpty()) {
						if(BaseDeDatos.insertarUsuario(registrar)) {
							V_registro.this.setVisible(false);
							new V_principal(registrar, BaseDeDatos.verTodosUsuarios(), BaseDeDatos.verTodosJuegos()).setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null,"Error a la hora de añadir el usuario");
						}
					} else {
						for(int i = 0; i <= lista.size(); i++) {
							if(lista.get(i).getNombre() == registrar.getNombre()) {
								JOptionPane.showMessageDialog(null, "Este usuario ya existe", "Atencion", JOptionPane.WARNING_MESSAGE);
							} else {
								if(BaseDeDatos.insertarUsuario(registrar)) {
									V_registro.this.setVisible(false);
									new V_principal(registrar,BaseDeDatos.verTodosUsuarios(),BaseDeDatos.verTodosJuegos()).setVisible(true);
									break;
								} else {
									JOptionPane.showMessageDialog(null,"Error a la hora de añadir el usuario");
									break;
								}
							}
						}
					}
				} catch (HeadlessException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				V_registro.this.setVisible(false);
				new V_inicio();
			}
		});
	}
}