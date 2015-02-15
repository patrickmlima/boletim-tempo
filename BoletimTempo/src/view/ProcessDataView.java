package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import model.actionlistener.ProcessDataBtnHandler;
import model.actionlistener.DaysRadioBtnListener;

import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

/**
 * Class which brings together all necessary elements to process
 * the data file 
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
	
	public boolean isAllDaysSelected() {
		return rdbtnAllDays.isSelected();
	}
	
	public boolean isADaySelected() {
		return rdbtnADay.isSelected();
	}
	
	public boolean isRangeDaysSelected() {
		return rdbtnRangeDays.isSelected();
	}
	
	public JDateChooser getDateChooserADay() {
		return dateChooserADay;
	}
	
	public JDateChooser getDateChooserFirstDay() {
		return dateChooserFirstDay;
	}
	
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

		//Cria o layout e adiciona os componentes
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblSelecioneUmaOpo, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
				.addComponent(lblDiasMeteorolgicos, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
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
					.addContainerGap(85, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
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
	}
}

