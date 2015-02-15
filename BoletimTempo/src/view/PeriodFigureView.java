package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import model.actionlistener.ShowFigureBtnsHandler;
import model.componentchangelistener.PeriodFigureViewChanges;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

public class PeriodFigureView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelShowFigures;

	/**
	 * Create the panel.
	 */
	public PeriodFigureView() {
		addComponentListener(new PeriodFigureViewChanges());
		initialize();
	}
	
	public void setPanelShowFigure(JPanel panelShowFigures) {
		this.panelShowFigures = panelShowFigures;
	}
	
	public JPanel getPanelShowFigures() {
		return panelShowFigures;
	}
	
	public void initialize() {
		//Create the panel to show the figures
		panelShowFigures = new JPanel();
		panelShowFigures.setBackground(Color.GRAY);
		
		//Cria o JButton 'Anterior'
		JButton buttonNext = new JButton("Anterior");
		buttonNext.setToolTipText("Clique para ver as figuras do dia anterior");
		buttonNext.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		//Cria JButton 'Próximo'
		JButton buttonPrevious = new JButton("Pr\u00F3ximo");
		buttonPrevious.setToolTipText("Clique para ver as figuras do pr\u00F3ximo dia");
		buttonPrevious.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		//Cria o JButton 'Salvar figuras'
		JButton btnSaveFigures = new JButton("Salvar figuras");
		btnSaveFigures.setToolTipText("Clique aqui para salvar as figuras do turno");
		btnSaveFigures.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		//Cria e adiciona o listener
		ShowFigureBtnsHandler handler = new ShowFigureBtnsHandler();
		buttonNext.addActionListener(handler);
		buttonPrevious.addActionListener(handler);
		btnSaveFigures.addActionListener(handler);
		
		//Cria o layout e adiciona os componentes
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonNext, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonPrevious)
					.addPreferredGap(ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
					.addComponent(btnSaveFigures)
					.addGap(23))
				.addComponent(panelShowFigures, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelShowFigures, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonNext, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonPrevious)
						.addComponent(btnSaveFigures))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
