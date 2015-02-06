package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import view.ProcessDataView;
import view.WorkWindow;
import view.popup.Dialog;
import controller.Controller;

public class ProcessBtnHandler implements ActionListener{
	public ProcessBtnHandler() {
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Controller controller = null;
		try {
			controller = Controller.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		ProcessDataView pdv = (ProcessDataView) ((JButton) ev.getSource()).getParent();
		if(pdv.isAllDaysSelected()) {
			WorkWindow.getInstance().setNotClosable();
			controller.computeWeatherDay();
			(new Dialog("Desculpe", "Processamento n\u00E3o realizado", 
					"O dia n\u00E3o \u00E9 v\u00E1lido ou ainda n\u00E3o", "foi processado.")).setVisible(true);
		} else if( pdv.isADaySelected() && controller.validateDate(pdv.getTheDayDate()) ) {
				WorkWindow.getInstance().setNotClosable();
				controller.computeWeatherDay(pdv.getTheDayDate());
				(new Dialog("Processamento Finalizado", "Os dias foram processados", "e salvos com sucesso")).setVisible(true);
		} else if(pdv.isRangeDaysSelected() && controller.validateDate(pdv.getFirstDayDate(), pdv.getLastDayDate())) {
				WorkWindow.getInstance().setNotClosable();
				controller.computeWeatherDay(pdv.getFirstDayDate(), pdv.getLastDayDate());
				WorkWindow.getInstance().setClosable();
				(new Dialog("Processamento Finalizado", "Os dias foram processados", "e salvos com sucesso")).setVisible(true);
		} else {
				(new Dialog("Processamento n\u00E3o realizado", "Os dados est\u00E3o incorretos", "")).setVisible(true);
		}
		WorkWindow.getInstance().setClosable();
	}
}
