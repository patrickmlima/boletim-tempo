package main;

import java.awt.EventQueue;

import view.WorkWindow;

public class Main{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkWindow.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
