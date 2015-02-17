package view.popup;

import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import model.util.ImageUtil;

public class DialogBox {
	public DialogBox() {
	}
	
	public void initialize(String title, String text, Component parent, String type) {		
		Font fontPopup = new Font("Cambria Math", Font.PLAIN, 18);
		UIManager.put("OptionPane.messageFont", new FontUIResource(fontPopup));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(fontPopup));
		if(type.equals("OK")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title, JOptionPane.PLAIN_MESSAGE, 
					ImageUtil.resize(50, 50, new ImageIcon(ClassLoader.getSystemResource("resources/img/ok-icon.png")).getImage()));
		}else if(type.equals("information")) {
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
