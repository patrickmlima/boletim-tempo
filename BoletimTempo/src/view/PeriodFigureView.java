package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.Image;
import java.text.ParseException;

import model.actionlistener.ShowFigureHandler;
import model.util.CircularArrayList;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;

public class PeriodFigureView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CircularArrayList<Image> periodFigures;
	private JFormattedTextField fTextFieldDate;
	JLabel lblFigure;
	
	public String getTextDate() {
		return fTextFieldDate.getText();
	}
	
	public JLabel getLabelFigure() {
		return this.lblFigure;
	}
	
	public CircularArrayList<Image> getPeriodFigures() {
		return this.periodFigures;
	}
	
	public void addPeriodFigure(Image img) {
		periodFigures.add(img);
	}

	/**
	 * Create the panel.
	 */
	public PeriodFigureView() {
		periodFigures = new CircularArrayList<Image>();
		initialize();
	}
	
	public void initialize() {
		MaskFormatter maskDate = null;
		try {
			maskDate = new MaskFormatter("##/##/####");
			maskDate.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		fTextFieldDate = new JFormattedTextField(maskDate);
		fTextFieldDate.setToolTipText("Digite a data do boletim desejado");
		fTextFieldDate.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		ShowFigureHandler listener = new ShowFigureHandler(this);
		
		JButton btnOpen = new JButton("Abrir");
		btnOpen.setToolTipText("Clique para abrir as figuras");
		btnOpen.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		btnOpen.addActionListener(listener);
		
		JButton btnNext = new JButton("Próximo");
		btnNext.setToolTipText("Clique para ver o pr\u00F3ximo turno");
		btnNext.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		btnNext.addActionListener(listener);
		
		JButton btnPrevious = new JButton("Anterior");
		btnPrevious.setToolTipText("Clique para ver o turno anterior");
		btnPrevious.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		btnPrevious.addActionListener(listener);
		
		lblFigure = new JLabel("");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(fTextFieldDate, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOpen)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(btnPrevious)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNext)
					.addContainerGap())
				.addComponent(lblFigure, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOpen)
						.addComponent(fTextFieldDate, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext)
						.addComponent(btnPrevious))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFigure, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
