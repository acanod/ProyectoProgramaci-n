package main;

import java.util.ArrayList;
import java.util.List;

import datos.Usuario;
import ventanas.V_principal;

public class Main {
	public static List<Usuario> u=new ArrayList<Usuario>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] juegosComprados=new String[49];
		u.add(new Usuario("Ima","Ola","e@opp.es","ESP", "ERT", 0, 30.00, juegosComprados));
	
		new V_principal(u.get(0)).setVisible(true);
	}
	

}
