package main;

import java.awt.EventQueue;

import baseDeDatos.BaseDeDatos;

import ventanas.VInicio;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new VInicio();
					BaseDeDatos.conectarBD();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
}
