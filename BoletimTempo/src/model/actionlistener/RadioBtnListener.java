package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;

public class RadioBtnListener implements ActionListener {
	private JFormattedTextField theDay;
	private JFormattedTextField firstDay;
	private JFormattedTextField lastDay;
	
	public RadioBtnListener(JFormattedTextField theDay, JFormattedTextField firstDay, JFormattedTextField lastDay) {
		this.theDay = theDay;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
	}
	
	private void setTheDayState(boolean st) {
		theDay.setEnabled(st);
	}
	
	private void setFirstAndLastDayState(boolean st) {
		firstDay.setEnabled(st);
		lastDay.setEnabled(st);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JRadioButton jrb = (JRadioButton) event.getSource();

		if(jrb.getText().equals("\u00DAnico dia")){
			if(jrb.isSelected()) {
				setFirstAndLastDayState(false);
				setTheDayState(true);
			}
		} else if(jrb.getText().equals("Intervalo de dias")) {
			if(jrb.isSelected()) {
				setTheDayState(false);
				setFirstAndLastDayState(true);	
			}
		} else if(jrb.getText().equals("Todos os dias")) {
			if(jrb.isSelected()) {
				setTheDayState(false);
				setFirstAndLastDayState(false);
			}
		}
		
	}
}