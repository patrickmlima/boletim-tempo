package view.popup;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogBox {
	public DialogBox() {
	}
	
	public void initialize(String title, String text, Component parent, String type) {
		if(type.equals("OK")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title, JOptionPane.INFORMATION_MESSAGE);
		} else if(type.equals("incorrect")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title, JOptionPane.WARNING_MESSAGE);
		} else if(type.equals("error")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title, JOptionPane.ERROR_MESSAGE);
		}
	}
}
