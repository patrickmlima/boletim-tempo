package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Window.Type;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopupProcessingFinished extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopupProcessingFinished dialog = new PopupProcessingFinished();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setNotVisible() {
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	public PopupProcessingFinished() {
		setType(Type.POPUP);
		setResizable(false);
		setBounds(100, 100, 370, 223);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProcessamentoFinalizado = new JLabel("Processamento Finalizado");
		lblProcessamentoFinalizado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProcessamentoFinalizado.setFont(new Font("Cambria Math", Font.BOLD, 23));
		
		JLabel lblOsDiasForam = new JLabel("Os dias foram processados");
		lblOsDiasForam.setHorizontalAlignment(SwingConstants.CENTER);
		lblOsDiasForam.setFont(new Font("Cambria Math", Font.PLAIN, 21));
		
		JLabel lblESalvosCom = new JLabel("e salvos com sucesso");
		lblESalvosCom.setHorizontalAlignment(SwingConstants.CENTER);
		lblESalvosCom.setFont(new Font("Cambria Math", Font.PLAIN, 21));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblOsDiasForam, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblESalvosCom, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(lblProcessamentoFinalizado, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
		);
		
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblProcessamentoFinalizado)
					.addGap(18)
					.addComponent(lblOsDiasForam)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblESalvosCom)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setNotVisible();
					}
				});
				okButton.setFont(new Font("Cambria Math", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(153)
						.addComponent(okButton)
						.addContainerGap(158, Short.MAX_VALUE))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(okButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			
			buttonPane.setLayout(gl_buttonPane);
		}
	}
}
