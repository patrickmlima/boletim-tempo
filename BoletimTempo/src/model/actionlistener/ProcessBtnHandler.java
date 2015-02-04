package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import view.ProcessDataView;
import view.WorkWindow;
import view.popup.Popup;
import controller.Controller;

public class ProcessBtnHandler implements ActionListener{
	ProcessDataView pdv;
	public ProcessBtnHandler(ProcessDataView pdv) {
		this.pdv = pdv;
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
		if(pdv.isAllDaysSelected()) {
			WorkWindow.getInstance().setNotClosable();
			controller.computeWeatherDay();
			WorkWindow.getInstance().setClosable();
			(new Popup("Desculpe", "Processamento n\u00E3o realizado", 
					"O dia n\u00E3o \u00E9 v\u00E1lido ou ainda n\u00E3o", "foi processado.")).setVisible(true);
		} else if(pdv.isADaySelected()) {
			if(controller.validateDate(pdv.getTheDayDate())) {
				WorkWindow.getInstance().setNotClosable();
				System.out.println(pdv.getTheDayDate() + "-- \n");
				controller.computeWeatherDay(pdv.getTheDayDate());
				WorkWindow.getInstance().setClosable();
				(new Popup("Processamento Finalizado", "Os dias foram processados", "e salvos com sucesso")).setVisible(true);
			}
			else {
				(new Popup("Processamento n\u00E3o realizado", "Os dados est\u00E3o incorretos", "")).setVisible(true);
			}
		} else if(pdv.isRangeDaysSelected()) {
			if(controller.validateDate(pdv.getFirstDayDate(), pdv.getLastDayDate())) {
				WorkWindow.getInstance().setNotClosable();
				System.out.println(pdv.getFirstDayDate() + "-- " + pdv.getLastDayDate() +" -- \n");
				controller.computeWeatherDay(pdv.getFirstDayDate(), pdv.getLastDayDate());
				WorkWindow.getInstance().setClosable();
				(new Popup("Processamento Finalizado", "Os dias foram processados", "e salvos com sucesso")).setVisible(true);
			}
			else {
				(new Popup("Processamento n\u00E3o realizado", "Os dados est\u00E3o incorretos", "")).setVisible(true);
			}
		}	
	}
}
