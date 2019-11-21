package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import base_de_datos.BaseDeDatos;
import datos.Usuario;
import ventanas.V_inicio;

public class Main {
	
	public static List<Usuario> u=new ArrayList<Usuario>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new V_inicio();
					BaseDeDatos.conectarBD();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
}
