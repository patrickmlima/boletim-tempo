package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import javax.swing.JTextPane;

import model.actionlistener.SearchBtnHandler;
import model.componetchangelistener.WeatherDayViewCleaner;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;

public class WeatherDayView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea textAreaWeatherDay;
	
	public void setTextAreaWeatherDay(String text) {
		textAreaWeatherDay.setText(text);
	}
	
	public JTextArea getTextAreaWeatherDay() {
		return this.textAreaWeatherDay;
	}

	/**
	 * Create the panel.
	 */
	public WeatherDayView() {
		initialize();
	}
	
	public void initialize() {
		
		textAreaWeatherDay = new JTextArea();
		textAreaWeatherDay.setWrapStyleWord(true);
		textAreaWeatherDay.setLineWrap(true);
		textAreaWeatherDay.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textAreaWeatherDay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		JButton btnSave = new JButton("Salvar");
		btnSave.setFont(new Font("Cambria Math", Font.PLAIN, 18));
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
