package mx.com.hotel.main;

import java.awt.EventQueue;

import mx.com.hotel.views.MenuPrincipal;

public class HotelMain {

	public static void main(String[] args) {
		/**
		 * Launch the application.
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
