package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import java.awt.SystemColor;

import javax.swing.JTextPane;

import model.actionlistener.SearchBtnHandler;

public class WeatherDayView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea textAreaWeatherDay;
	private JTextPane textPaneFileName;
	
	public void setFilePath(String path) {
		textPaneFileName.setText(path);
	}
	
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
		
		JButton btnSearch = new JButton("Procurar...");
		btnSearch.addActionListener(new SearchBtnHandler(this));
		btnSearch.setToolTipText("Clique aqui para procurar o Boletim");
		btnSearch.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		
		textAreaWeatherDay = new JTextArea();
		textAreaWeatherDay.setWrapStyleWord(true);
		textAreaWeatherDay.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane(textAreaWeatherDay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		textPaneFileName = new JTextPane();
		textPaneFileName.setFont(new Font("Cambria Math", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPaneFileName, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
				.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textPaneFileName)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(7)
					.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
