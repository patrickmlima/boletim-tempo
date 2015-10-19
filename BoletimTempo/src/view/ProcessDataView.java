package view;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import com.toedter.calendar.JDateChooser;

import javax.swing.SwingConstants;

import model.util.ApplicationStatus;
import model.util.Util;
import view.actionlistener.DaysRadioBtnListener;
import view.actionlistener.ProcessDataBtnHandler;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Class which brings together all necessary elements to process the data file
 * 
 * @author Patrick M Lima
 *
 */
public class ProcessDataView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAllDays;
	private JRadioButton rdbtnADay;
	private JRadioButton rdbtnRangeDays;
	private JDateChooser dateChooserADay;
	private JDateChooser dateChooserFirstDay;
	private JDateChooser dateChooserLastDay;
	private JLabel lblSelect1;
	private JLabel lblSelect2;
	private JLabel lblA;

	/**
	 * Returns the selected state of 'Todos os dias' radio button
	 * 
	 * @return true if the radio button is selected, false otherwise
	 */
	public boolean isAllDaysSelected() {
		return rdbtnAllDays.isSelected();
	}

	/**
	 * Returns the selected state of 'Único dia' radio button
	 * 
	 * @return true if the radio button is selected, false otherwise
	 */
	public boolean isADaySelected() {
		return rdbtnADay.isSelected();
	}

	/**
	 * Returns the selected state of 'Intervalo de dias' radio button
	 * 
	 * @return true if the radio button is selected, false otherwise
	 */
	public boolean isRangeDaysSelected() {
		return rdbtnRangeDays.isSelected();
	}

	/**
	 * Gets the JDateChooser of one day option
	 * 
	 * @return JDateChooser of one day option
	 */
	public JDateChooser getDateChooserADay() {
		return dateChooserADay;
	}
	
	public ButtonGroup getRadioButtonsGroup() {
		return buttonGroup;
	}

	/**
	 * Gets the JDateChooser of the first day from the range of days option
	 * 
	 * @return JDateChooser of the first day (range of days option)
	 */
	public JDateChooser getDateChooserFirstDay() {
		return dateChooserFirstDay;
	}

	/**
	 * Gets the JDateChooser of the last day from the range of days option
	 * 
	 * @return JDateChooser of the last day (range of days option)
	 */
	public JDateChooser getDateChooserLastDay() {
		return dateChooserLastDay;
	}

	public JLabel getLabelSelect1() {
		return lblSelect1;
	}

	public JLabel getLabelSelect2() {
		return lblSelect2;
	}

	public JLabel getLabelA() {
		return lblA;
	}
	
	public JRadioButton getRadioButtonAllDays() {
		return rdbtnAllDays;
	}
	
	public JRadioButton getRadioButtonADay() {
		return rdbtnADay;
	}
	
	public JRadioButton getRadioButtonRangeDays() {
		return rdbtnRangeDays;
	}

	/**
	 * Create the panel.
	 */
	public ProcessDataView() {
		initialize();
	}

	/**
	 * Initializes the components
	 */
	public void initialize() {
		//Cria os labels com a mensagem inicial
		JLabel lblSelecioneUmaOpo = new JLabel("Selecione uma op\u00E7\u00E3o para processamento dos");
		lblSelecioneUmaOpo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneUmaOpo.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblDiasMeteorolgicos = new JLabel("dias meteorol\u00F3gicos");
		lblDiasMeteorolgicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiasMeteorolgicos.setFont(new Font("Cambria Math", Font.BOLD | Font.ITALIC, 20));

		//Cria o radiobutton para processar todos os dias
		rdbtnAllDays = new JRadioButton("Todos os dias");
		rdbtnAllDays.setToolTipText("Selecione para processar todos os dias");
		rdbtnAllDays.setFont(new Font("Cambria Math", Font.PLAIN, 20));

		//cria o radiobutton para processar um único dia
		rdbtnADay = new JRadioButton("\u00DAnico dia");
		rdbtnADay.setToolTipText("Selecione para processar um \u00FAnico dia");
		rdbtnADay.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		lblSelect1 = new JLabel("Selecionar");
		lblSelect1.setFont(new Font("Cambria Math", Font.ITALIC, 18));
		lblSelect1.setEnabled(false);
		//cria o datechooser para aquele dia
		dateChooserADay = new JDateChooser();
		dateChooserADay.getCalendarButton().setToolTipText("Clique para escolher o dia");
		dateChooserADay.setToolTipText("");
		dateChooserADay.setEnabled(false);
		
		//cria o radiobutton para processar um intervalo de dias
		rdbtnRangeDays = new JRadioButton("Intervalo de dias");
		rdbtnRangeDays.setToolTipText("Selecione para processar um intervalo de dias");
		rdbtnRangeDays.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		lblSelect2 = new JLabel("Selecionar");
		lblSelect2.setFont(new Font("Cambria Math", Font.ITALIC, 18));
		lblSelect2.setEnabled(false);
		//cria o datechooser para o primeiro dia
		dateChooserFirstDay = new JDateChooser();
		dateChooserFirstDay.getCalendarButton().setToolTipText("Clique para escolher o primeiro dia");
		dateChooserFirstDay.setToolTipText("");
		dateChooserFirstDay.setEnabled(false);
		
		lblA = new JLabel("a");
		lblA.setFont(new Font("Cambria Math", Font.ITALIC, 18));
		lblA.setEnabled(false);
		//cria o datechooser para o último dia
		dateChooserLastDay = new JDateChooser();
		dateChooserLastDay.getCalendarButton().setToolTipText("Clique para escolher o \u00FAltimo dia");
		dateChooserLastDay.setToolTipText("");
		dateChooserLastDay.setEnabled(false);

		//groups the radio buttons
		buttonGroup.add(rdbtnAllDays);
		buttonGroup.add(rdbtnADay);
		buttonGroup.add(rdbtnRangeDays);
		
		//cria o listener do radioButtons
		DaysRadioBtnListener rdbtnlistener = new DaysRadioBtnListener();
		//adiciona os listeners dos radiobuttons
		rdbtnAllDays.addActionListener(rdbtnlistener);
		rdbtnADay.addActionListener(rdbtnlistener);
		rdbtnRangeDays.addActionListener(rdbtnlistener);
		
		//cria um panel para "abrigar" o botão 'Processar' (usando flow layout para garantir a centralização)
		JPanel panel = new JPanel();
		
		SelectFileButtonHandler selectFileHandler = new SelectFileButtonHandler();
		
		JButton btnSelectBaixa1 = new JButton("Selecionar Baixa1");
		btnSelectBaixa1.addActionListener(selectFileHandler);
		btnSelectBaixa1.setToolTipText("Clique aqui para selecionar o arquivo Baixa1.");
		btnSelectBaixa1.setFont(new Font("Cambria Math", Font.PLAIN, 16));
		
		JButton btnSelectBaixa2 = new JButton("Selecionar Baixa2");
		btnSelectBaixa2.addActionListener(selectFileHandler);
		btnSelectBaixa2.setToolTipText("Clique aqui para selecionar o arquivo Baixa2.");
		btnSelectBaixa2.setFont(new Font("Cambria Math", Font.PLAIN, 16));

		//Cria o layout e adiciona os componentes
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblSelecioneUmaOpo, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(lblDiasMeteorolgicos, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSelectBaixa2)
						.addComponent(btnSelectBaixa1)
						.addComponent(rdbtnADay)
						.addComponent(rdbtnAllDays, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(lblSelect1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserADay, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addComponent(rdbtnRangeDays)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(lblSelect2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserFirstDay, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblA)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserLastDay, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(224, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSelecioneUmaOpo, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(lblDiasMeteorolgicos)
					.addGap(18)
					.addComponent(rdbtnAllDays)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnADay)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSelect1)
								.addComponent(dateChooserADay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(rdbtnRangeDays)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblSelect2)
								.addComponent(dateChooserFirstDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblA)
						.addComponent(dateChooserLastDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSelectBaixa1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSelectBaixa2)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Cria o botão 'Processar'
		JButton btnProcess = new JButton("Processar");
		btnProcess.setToolTipText("Clique aqui para iniciar o processamento");
		btnProcess.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		btnProcess.addActionListener(new ProcessDataBtnHandler());
		//Aciona ele a um panel com FlowLayout (para centralizá-lo)
		panel.add(btnProcess);
		
		setLayout(groupLayout);
		addComponentListener(new TabComponentHandler());
	}
}

class SelectFileButtonHandler implements ActionListener {
	public SelectFileButtonHandler() {
	}

	@Override
	public void actionPerformed(ActionEvent source) {
		JFileChooser fc = new JFileChooser();
		int r = fc.showOpenDialog(WorkWindow.getInstance().getFrame());
		
		if(r == JFileChooser.APPROVE_OPTION) {
			JButton btn = (JButton)source.getSource();
			String path = fc.getSelectedFile().getAbsolutePath();
			if(btn.getText().equals("Selecionar Baixa1"))
				Util.pathBaixa1 = path;
			else
				Util.pathBaixa2 = path;
		}
		
	}
}

class TabComponentHandler implements ComponentListener {
	@Override
	public void componentShown(ComponentEvent source) {
		ProcessDataView pdv  = (ProcessDataView) source.getSource();
		if((WorkWindow.getInstance().getApplicationStatus() == ApplicationStatus.PERIOD_FIGURES_SAVED)
				&& (pdv.getRadioButtonsGroup().getSelection() != null) ){
			if(pdv.isADaySelected()) {
				pdv.getDateChooserADay().setDate(null);
				pdv.getDateChooserADay().setEnabled(false);
			}
			else if(pdv.isRangeDaysSelected()) {
				pdv.getDateChooserFirstDay().setDate(null);
				pdv.getDateChooserFirstDay().setEnabled(false);
				pdv.getDateChooserLastDay().setDate(null);
				pdv.getDateChooserLastDay().setEnabled(false);
			}
			pdv.getRadioButtonsGroup().clearSelection();
		}
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {	
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		
	}
}