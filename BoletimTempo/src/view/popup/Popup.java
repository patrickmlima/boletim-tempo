package view.popup;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Popup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JLabel lblTitle;
	private JLabel lblLine1;
	private JLabel lblLine2;
	private String windowTitle;
	private String title;
	private String line1;
	private String line2;

	/**
	 * Create the dialog.
	 */	
	public Popup(String windowTitle, String title, String line1, String line2) {
		super();
		this.windowTitle = windowTitle;
		this.title = title;
		this.line1 = line1;
		this.line2 = line2;
		initialize();
	}
	
	public Popup(String title, String line1, String line2) {
		this("", title, line1, line2);
	}
	
	private void initialize() {
		setTitle(this.windowTitle);
		setBounds(100, 100, 370, 223);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblTitle = new JLabel(this.title);
			lblTitle.setFont(new Font("Cambria Math", Font.BOLD, 23));
			contentPanel.add(lblTitle);
		}
		{
			lblLine1 = new JLabel(this.line1);
			lblLine1.setFont(new Font("Cambria Math", Font.PLAIN, 20));
			contentPanel.add(lblLine1);
		}
		{
			lblLine2 = new JLabel(this.line2);
			lblLine2.setFont(new Font("Cambria Math", Font.PLAIN, 20));
			contentPanel.add(lblLine2);
		}
		{
			JLabel label = new JLabel("");
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setFont(new Font("Cambria Math", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent action) {
						setVisible(false);
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
						.addContainerGap(171, Short.MAX_VALUE)
						.addComponent(okButton)
						.addGap(156))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(okButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

}
