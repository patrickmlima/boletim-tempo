package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.text.MaskFormatter;

import model.actionlistener.ProcessBtnHandler;

public class ProcessDataView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAllDays;
	private JRadioButton rdbtnADay;
	private JFormattedTextField fTextFieldTheDay;
	private JRadioButton rdbtnRangeDays;
	private JFormattedTextField fTextFieldFirstDay;
	private JFormattedTextField fTextFieldLastDay;
	
	public boolean isAllDaysSelected() {
		return rdbtnAllDays.isSelected();
	}
	
	public boolean isADaySelected() {
		return rdbtnADay.isSelected();
	}
	
	public boolean isRangeDaysSelected() {
		return rdbtnRangeDays.isSelected();
	}
	
	public String getTheDayDate() {
		return fTextFieldTheDay.getText();
	}
	
	public String getFirstDayDate() {
		return fTextFieldFirstDay.getText();
	}
	
	public String getLastDayDate() {
		return fTextFieldLastDay.getText();
	}
	
	/**
	 * Create the panel.
	 */
	public ProcessDataView() {
		initialize();
	}
	
	public void initialize() {
		
		rdbtnAllDays = new JRadioButton("Todos os dias");
		rdbtnAllDays.setToolTipText("Selecione para processar todos os dias");
		buttonGroup.add(rdbtnAllDays);
		rdbtnAllDays.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		rdbtnADay = new JRadioButton("\u00DAnico dia");
		rdbtnADay.setToolTipText("Selecione para processar um \u00FAnico dia");
		buttonGroup.add(rdbtnADay);
		rdbtnADay.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		MaskFormatter maskDate = null;
		try {
			maskDate = new MaskFormatter("##/##/####");
			maskDate.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		fTextFieldTheDay = new JFormattedTextField(maskDate);
		fTextFieldTheDay.setToolTipText("Insira o dia a ser processado");
		fTextFieldTheDay.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		rdbtnRangeDays = new JRadioButton("Intervalo de dias");
		rdbtnRangeDays.setToolTipText("Selecione para processar um intervalo de dias");
		buttonGroup.add(rdbtnRangeDays);
		rdbtnRangeDays.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		fTextFieldFirstDay = new JFormattedTextField(maskDate);
		fTextFieldFirstDay.setToolTipText("Insira o dia inicial");
		fTextFieldFirstDay.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		fTextFieldLastDay = new JFormattedTextField(maskDate);
		fTextFieldLastDay.setToolTipText("Insira o dia final");
		fTextFieldLastDay.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		JLabel lblA = new JLabel("a");
		lblA.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		
		JButton btnOk = new JButton("Processar");
		btnOk.addActionListener(new ProcessBtnHandler(this));
		btnOk.setToolTipText("Clique aqui para iniciar o processamento");
		btnOk.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(198)
					.addComponent(btnOk)
					.addGap(195))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnAllDays, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
					.addGap(47))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnADay)
					.addContainerGap(389, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(fTextFieldTheDay, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(359, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnRangeDays)
					.addContainerGap(327, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(fTextFieldFirstDay, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fTextFieldLastDay, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(206, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(rdbtnAllDays)
					.addGap(18)
					.addComponent(rdbtnADay)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fTextFieldTheDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(rdbtnRangeDays)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(fTextFieldFirstDay, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblA)
						.addComponent(fTextFieldLastDay, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addGap(57))
		);
		setLayout(groupLayout);
	}
}

