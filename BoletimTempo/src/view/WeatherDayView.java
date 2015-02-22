package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTextArea;

import view.actionlistener.SaveDataFileBtnHandler;
import view.componentchangelistener.WeatherDayViewChanges;

/**
 * Class which brings together all necessary view components to show the data
 * that was processed
 * 
 * @author Patrick M Lima
 *
 */
public class WeatherDayView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaWeatherDay;

	/**
	 * Sets the content of the JTextArea where the processed data will be shown
	 * 
	 * @param text
	 *            text which will be the content of the text area
	 */
	public void setTextAreaWeatherDay(String text) {
		textAreaWeatherDay.setText(text);
	}

	/**
	 * Gets the JTextArea where the processed data will be shown
	 * 
	 * @return a JTextArea instance
	 */
	public JTextArea getTextAreaWeatherDay() {
		return this.textAreaWeatherDay;
	}

	/**
	 * Create the panel.
	 */
	public WeatherDayView() {
		addComponentListener(new WeatherDayViewChanges());
		initialize();
	}

	/**
	 * Initializes the components
	 */
	public void initialize() {
		//Cria o text area que exibirá os dados
		textAreaWeatherDay = new JTextArea();
		textAreaWeatherDay.setWrapStyleWord(true);
		textAreaWeatherDay.setLineWrap(true);
		textAreaWeatherDay.setEditable(false);
		
		//Adiciona um scrollPane ao text area
		JScrollPane scroll = new JScrollPane(textAreaWeatherDay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//Cria o botão Save
		JButton btnSave = new JButton("Salvar");
		btnSave.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		btnSave.addActionListener(new SaveDataFileBtnHandler());
		
		//Cria o layout e adiciona os componentes
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scroll, Alignment.TRAILING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(489, Short.MAX_VALUE)
					.addComponent(btnSave)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(btnSave)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
}