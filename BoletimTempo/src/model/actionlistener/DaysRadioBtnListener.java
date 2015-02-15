package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import view.ProcessDataView;

public class DaysRadioBtnListener implements ActionListener {
	
	private void setEnabledComponents(ProcessDataView instanceView, boolean status1, boolean status2) {
		instanceView.getLabelSelect1().setEnabled(status1);
		instanceView.getDateChooserADay().setEnabled(status1);
		instanceView.getLabelSelect2().setEnabled(status2);
		instanceView.getDateChooserFirstDay().setEnabled(status2);
		instanceView.getLabelA().setEnabled(status2);
		instanceView.getDateChooserLastDay().setEnabled(status2);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JRadioButton jrb = (JRadioButton) event.getSource();
		ProcessDataView instanceView = (ProcessDataView) jrb.getParent();

		if(jrb.getText().equals("\u00DAnico dia")){
			if(jrb.isSelected()) {
				setEnabledComponents(instanceView, true, false);
				
			}
		} else if(jrb.getText().equals("Intervalo de dias")) {
			if(jrb.isSelected()) {
				setEnabledComponents(instanceView, false, true);
			}
		} else if(jrb.getText().equals("Todos os dias")) {
			if(jrb.isSelected()) {
				setEnabledComponents(instanceView, false, false);
			}
		}
		
	}
}