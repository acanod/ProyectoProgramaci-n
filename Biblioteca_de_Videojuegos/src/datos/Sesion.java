package datos;

import javax.swing.JLabel;

public class Sesion implements Runnable {

	JLabel tiempo;
	Thread hilo;
	boolean cronometroActivo;

	public Sesion(JLabel tiempo) {
		this.tiempo = tiempo;
	}

	public void run() {
		Integer minutos = 0, segundos = 0, milesimas = 0;
		String min = "", seg = "", mil = "";
		
		try {
			while (cronometroActivo) {
				Thread.sleep(4);
				milesimas += 4;
				if (milesimas == 1000) {
					milesimas = 0;
					segundos += 1;
					if (segundos == 60) {
						segundos = 0;
						minutos++;
					}
				}
				if (minutos < 10) {
					min = "0" + minutos;
				} else {
					min = minutos.toString();
				}
				if (segundos < 10) {
					seg = "0" + segundos;
				} else { 
					seg = segundos.toString();
				}
				if (milesimas < 10) {
					mil = "00" + milesimas;
				} else if (milesimas < 100) {
					mil = "0" + milesimas;
				} else {
					mil = milesimas.toString();
				}

				tiempo.setText(min + ":" + seg + ":" + mil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tiempo.setText("00:00:000");
	}

	public void iniciarCronometro() {
		cronometroActivo = true;
		hilo = new Thread(this);
		hilo.start();
	}

}
