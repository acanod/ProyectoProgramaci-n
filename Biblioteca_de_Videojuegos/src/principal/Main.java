package principal;

import java.awt.EventQueue;

import base_de_datos.BaseDeDatos;
//import ventanas.V_inicio;
import ventanas.*;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new V_inicio();
					//BaseDeDatos.conectarBD();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
