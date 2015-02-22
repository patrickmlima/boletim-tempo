package view.popup;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.util.ImageUtil;

/**
 * Class which shows the dialog boxes used in the application
 * 
 * @author Patrick M Lima
 *
 */
public class DialogBox {
	public DialogBox() {
		
	}
	
	/**
	 * Initializes and show the dialog box
	 * 
	 * @param title
	 *            the title to be shown in the dialog window
	 * @param text
	 *            the message to be shown in the dialog window
	 * @param parent
	 *            the parent component
	 * @param type
	 *            type of the message which can be either OK, information,
	 *            incorrect, error
	 */
	public void initialize(String title, String text, Component parent, String type) {		
		if(type.equals("OK")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title, JOptionPane.PLAIN_MESSAGE, 
					ImageUtil.resize(50, 50, new ImageIcon(ClassLoader.getSystemResource("resources/img/ok-icon.png")).getImage()));
		} else if (type.equals("information")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title,
					JOptionPane.INFORMATION_MESSAGE);
		} else if (type.equals("incorrect")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title,
					JOptionPane.WARNING_MESSAGE);
		} else if (type.equals("error")) {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title,
					JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.setDefaultLocale(null);
			JOptionPane.showMessageDialog(parent, text, title,
					JOptionPane.PLAIN_MESSAGE);
		}
	}
}