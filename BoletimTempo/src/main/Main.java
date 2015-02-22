package main;

import java.awt.EventQueue;

import view.WorkWindow;

/**
 * It's the class which starts the software execution
 * 
 * @author Patrick M Lima
 *
 */
public class Main{
	//lauchs the application
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
