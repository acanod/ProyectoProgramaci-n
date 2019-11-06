package principal;

import java.awt.EventQueue;

//import ventanas.V_inicio;
import ventanas.*;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new V_inicio();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
