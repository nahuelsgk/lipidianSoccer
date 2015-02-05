/*
 * Jaume Vinyes Navas
 */



package vistas;

import javax.swing.SwingUtilities;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FutbolFrame inst = FutbolFrame.getInstancia();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

}
