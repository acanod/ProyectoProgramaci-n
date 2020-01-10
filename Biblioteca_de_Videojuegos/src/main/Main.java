package main;

import java.awt.EventQueue;

import base_de_datos.BaseDeDatos;
import ventanas.V_inicio;

public class Main {
	
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
