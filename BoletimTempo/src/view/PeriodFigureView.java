package view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import model.util.ApplicationStatus;
import model.util.ImageUtil;
import model.util.Util;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import view.actionlistener.ShowFigureBtnsHandler;
import view.componentchangelistener.PeriodFigureViewChanges;
import view.popup.DialogBox;
import controller.Controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * JPanel which contains the necessary components to show the Period figures
 * generated and save them
 * 
 * @author Patrick M Lima
 *
 */
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

	/**
	 * Gets the JPanel which shows the period figures
	 * 
	 * @return a JPanel instance
	 */
	public JPanel getPanelShowFigures() {
		return panelShowFigures;
	}
	
	/**
	 * Initializes the components
	 */
	public void initialize() {
		// Create the panel to show the figures
		panelShowFigures = new JPanel();
		panelShowFigures.setBackground(Color.GRAY);
		
		//Cria o JButton 'Salvar figuras'
		JButton btnSaveFigures = new JButton("Salvar figuras");
		btnSaveFigures.setToolTipText("Clique aqui para salvar as figuras exibidas");
		btnSaveFigures.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		//Cria o JButton 'Descartar'
		JButton btnRuleOut = new JButton("Descartar");
		btnRuleOut.setToolTipText("Clique aqui para descartar as figuras exibidas");
		btnRuleOut.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		//Cria e adiciona o listener
		ShowFigureBtnsHandler handler = new ShowFigureBtnsHandler();
		btnSaveFigures.addActionListener(handler);
		btnRuleOut.addActionListener(handler);
	
		
		//Cria o layout e adiciona os componentes
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(277, Short.MAX_VALUE)
					.addComponent(btnRuleOut)
					.addPreferredGap(ComponentPlacement.RELATED)
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
						.addComponent(btnSaveFigures)
						.addComponent(btnRuleOut))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	/**
	 * Adds the period figures icons to be shown in a JPanel. There the user can
	 * switch what figures will be save
	 * 
	 * @throws IOException
	 *             case the controller instance can't be taken
	 */
	public void addFigures() throws IOException {
		getPanelShowFigures().removeAll();
		int w = getPanelShowFigures().getWidth()/5 -10;
		int h = getPanelShowFigures().getHeight() -10;

		Controller controller = null;
		controller = Controller.getInstance();
		
		JLabel label = null;
		BufferedImage[] imgs = controller.getImages();
		if(imgs != null) {
			for (BufferedImage bi : imgs) {
				System.out.println("repetição para adicionar imagens");
				label = new JLabel();
				label.setSize(w, h);
				label.setIcon(new ImageIcon(ImageUtil.resize(bi, w, h)));
				getPanelShowFigures().add(label);
			}
		} else {
			WorkWindow.getInstance().setStatus(
					ApplicationStatus.PERIOD_FIGURES_SAVED);
			(new DialogBox())
					.initialize(
							"Conclu\u00EDdo",
							"Todas as imagens foram geradas.\nRetorne \u00E0 primeira aba "
							+ "para processar mais dados\nou finalize a aplica\u00E7\u00E3o",
							this, "OK");
			Util.pathBaixa1 = Util.pathBaixa2 = null;
		}
		revalidate();
		repaint();
	}
}