package ventanas;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base_de_datos.BaseDeDatos;
import datos.Juego;
import datos.Usuario;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class V_ListaUsuarios extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;

	public V_ListaUsuarios(Usuario u, List<Usuario> users, List<Juego> j) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelBuscar = new JPanel();

		JList<String> listaUsuarios = new JList<String>();
		listaUsuarios.setModel(inicializarListaA(u, users));

		JScrollPane scrollPaneLista = new JScrollPane(listaUsuarios);
		scrollPaneLista.setBorder(BorderFactory.createTitledBorder("Lista de Usuarios"));

		JButton bAtras = new JButton("Atras");
		bAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new V_Amigos(u, users, j).setVisible(true);
				V_ListaUsuarios.this.setVisible(false);

			}
		});
		
		JButton botonAnadir = new JButton("A\u00F1adir");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addComponent(panelBuscar, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(scrollPaneLista, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(botonAnadir)
							.addPreferredGap(ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
							.addComponent(bAtras)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPaneLista, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(bAtras)
						.addComponent(botonAnadir))
					.addContainerGap())
		);

		txtSearch = new JTextField();
		panelBuscar.add(txtSearch);
		txtSearch.setColumns(10);

		JButton botonBuscar = new JButton("Buscar Usuario");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelBuscar.add(botonBuscar);
		contentPane.setLayout(gl_contentPane);
		
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame f;
				f = new JFrame();
				int seleccion = JOptionPane.showOptionDialog(f,
						"Deseas solicitar amistad a "
								+ listaUsuarios.getSelectedValue().toString() + "?",
						"Solicitar amistad a " + listaUsuarios.getSelectedValue().toString(), JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" }, // null para YES, NO y CANCEL
						"opcion 1");

				if (seleccion != -1 && seleccion == 0) {

					JFrame x = new JFrame();
					JOptionPane.showMessageDialog(x, "Eres amigo de " + listaUsuarios.getSelectedValue().toString());
					
					nuevoAmigo(u,listaUsuarios.getSelectedValue().toString(),BaseDeDatos.verTodosUsuarios());
					
				}
			}
		});
	}

	public static DefaultListModel<String> inicializarListaA(Usuario u, List<Usuario> list) {
		DefaultListModel<String> listaBien = new DefaultListModel<String>();
		
		int x = 0;
		for (int i = 0; i < list.size(); i++) {
			boolean esAmigo=false;
			for(int z=0; z<BaseDeDatos.listaDeAmigos(u).size();z++) {
				if(list.get(i).getNombre().equals(BaseDeDatos.listaDeAmigos(u).get(z).getNombre())) {
					esAmigo=true;
				}
			}
			if (!list.get(i).getNombre().equals(u.getNombre()) && esAmigo==false) {
				listaBien.add(x, list.get(i).getNombre());
			} else {
				x--;
			}
			x++;	
		}
		return listaBien;
	}
	
	public static void nuevoAmigo(Usuario u,String nombreAmigo, List<Usuario> listU) {
		Usuario resultado=null;
		for(int i=0; i<listU.size();i++) {
			if(nombreAmigo.equals(listU.get(i).getNombre())) {
				resultado=listU.get(i);
			}
			
		}		
		BaseDeDatos.amigos(u, resultado);
		BaseDeDatos.amigos(resultado, u);
		
	}
}