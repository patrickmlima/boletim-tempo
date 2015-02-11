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
import java.awt.Color;

public class PeriodFigureView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Create the panel.
	 */
	public PeriodFigureView() {
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
		
		JButton buttonNext = new JButton("Anterior");
		buttonNext.setToolTipText("Clique para ver as figuras do dia anterior");
		buttonNext.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		JButton btnSaveFigures = new JButton("Salvar figuras");
		btnSaveFigures.setToolTipText("Clique aqui para salvar as figuras do turno");
		btnSaveFigures.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		JPanel panelShowFigures = new JPanel();
		panelShowFigures.setBackground(Color.GRAY);
		
		JButton buttonPrevious = new JButton("Pr\u00F3ximo");
		buttonPrevious.setToolTipText("Clique para ver as figuras do pr\u00F3ximo dia");
		buttonPrevious.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonNext, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonPrevious)
					.addPreferredGap(ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
					.addComponent(btnSaveFigures)
					.addContainerGap())
				.addComponent(panelShowFigures, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelShowFigures, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(buttonNext, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(buttonPrevious))
						.addComponent(btnSaveFigures))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
