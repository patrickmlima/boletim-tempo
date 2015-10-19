package view.actionlistener;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;

import model.util.ApplicationStatus;
import model.util.Util;
import view.ProcessDataView;
import view.WorkWindow;
import view.popup.DialogBox;
import controller.Controller;

/**
 * Handle of Process Button within ProcessDataView class
 * 
 * @author Patrick M Lima
 *
 */
public class ProcessDataBtnHandler implements ActionListener {

	public ProcessDataBtnHandler() {
	}

	@Override
	public void actionPerformed(ActionEvent source) {
		if(Util.pathBaixa1 == null && Util.pathBaixa2 == null) {
			(new DialogBox()).initialize("", "Por favor, selecione os arquivos de dados\n para iniciar o processamento", null, "error");
			return;
		}
		Controller controller = null;
		// pega uma instancia do controller
		try {
			controller = Controller.getInstance();
		} catch (IOException | NullPointerException e) {
			System.out.println("Erro ao carregar arquivo");
			e.printStackTrace();
			(new DialogBox()).initialize("Erro",
					"N\u00E3o foi poss\u00EDvel encontrar o arquivo de dados",
					null, "error");
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			(new DialogBox()).initialize("Erro", 
					"Os arquivos selecionados n\u00E3o possuem a estrutura\ndos arquivos Baixa1 e Baixa2\n"
							+ "Selecione os arquivos corretos para continuar", 
							null, "error");
			return ;
		}
		
		//Caso o status seja diferente do status 'inicial' da aplicação
		if(WorkWindow.getInstance().getApplicationStatus() != ApplicationStatus.INITIALIZED) {
			//limpa os dados em memória
			controller.clear();
			//configuras as abas para ativar apenas a de processamento de dados
			WorkWindow.getInstance().configureTabbedPaneEnable();
		}
		
		// pega o JPanel ProcessDataView
		ProcessDataView processDataView = (ProcessDataView) ((JButton) source
				.getSource()).getParent().getParent();
		
		WorkWindow workWindow = WorkWindow.getInstance();
		//pega a opção da janela, e de acordo com ela, toma as decisões
		ProcessDataViewCases result = ranksTheCase(processDataView);
		
		switch(result) {
		case ALL_DAY_SELECTED:
			workWindow.setNotClosable();
			WorkWindow.getInstance().getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if(controller.computeWeatherDay()) {
				WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());

				(new DialogBox()).initialize("Processamento finalizado",
						"Todos os dias foram processados\n e salvos com sucesso.",
						processDataView, "OK");
				changeToWeatherDayTab();
			} else {
				WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());
				(new DialogBox()).initialize("Processamento n\u00E3o realizado",
						"Os dados ainda n\u00E3o foram salvos ou h\u00E1\n uma disparidade nos arquivos de dados.",
						processDataView, "error");
			}
			workWindow.setClosable();
			break;

		case ONE_DAY_SELECTED:
			WorkWindow.getInstance().setNotClosable();
			WorkWindow.getInstance().getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//se o retorno da computação é true (computou o dia)
			if (controller.computeWeatherDay(new SimpleDateFormat("dd/MM/yyyy").
					format(processDataView.getDateChooserADay().getDate())) ) {
				WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());
				//exibe uma mensagem de finalização
				(new DialogBox()).initialize("Processamento finalizado",
						"Todos os dias foram processados\n e salvos com sucesso.",
						processDataView, "OK");

				//muda a aba
				changeToWeatherDayTab();
			}
			//senão, exibe uma mensagem informando que o dia não foi processado
			else {
				WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());
				(new DialogBox()).initialize("Processamento n\u00E3o realizado",
						"O dado ainda n\u00E3o foi salvo ou h\u00E1\n uma disparidade nos arquivos de dados.",
						processDataView, "error");
			}
			workWindow.setClosable();
			break;

		case RANGE_OF_DAYS_SELECTED:
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fDay, lDay;
			fDay = formatter.format(processDataView.getDateChooserFirstDay()
					.getDate());
			lDay = formatter.format(processDataView.getDateChooserLastDay()
					.getDate());

			//verifica se as datas estão em ordem cronológica
			if (controller.validateDate(fDay, lDay)) {
				workWindow.setNotClosable();
				WorkWindow.getInstance().getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				//verifica se os dias foram computados
				if (controller.computeWeatherDay(fDay, lDay) ) {
					//se sim, exibe uma mensagem informando o fim do processamento
					WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());
					(new DialogBox()).initialize("Processamento finalizado",
							"Todos os dias foram processados\n e salvos com sucesso.",
							processDataView, "OK");
					
					//muda a aba
					changeToWeatherDayTab();
				}
				//senão, exibe uma mesagem informando o não processamento
				else {
					WorkWindow.getInstance().getFrame().setCursor(Cursor.getDefaultCursor());
					(new DialogBox()).initialize("Processamento n\u00E3o realizado",
							"Os dados ainda n\u00E3o foram salvos ou h\u00E1\n uma disparidade nos arquivos de dados.",
							processDataView, "error");
				}
				workWindow.setClosable();

				//se as datas não são cronológicas exibe uma mensagem informando o fato
			} else {
				(new DialogBox()).initialize("Processamento n\u00E3o realizado",
						"As datas precisam estar em\n ordem cronol\u00F3gica.",
						processDataView, "incorrect");
			}
			break;

		case NO_DATE_SELECTED:
			(new DialogBox()).initialize("Processamento n\u00E3o realizado",
					"Selecione a(s) data(s) para iniciar o processamento",
					processDataView, "incorrect");
			break;

		case NO_OPTIONS_SELECTED:
			(new DialogBox()).initialize("Por favor", "Selecione uma op\u00E7\u00E3o para iniciar o processamento", processDataView, "information");
			break;
		}
	}		
	
	/**
	 * Ranks the cases according the options selected in the view
	 * 
	 * @param processDataView
	 *            the instance of view
	 * @return an enum which represents the possible states
	 */
	private ProcessDataViewCases ranksTheCase(ProcessDataView processDataView) {
		if (processDataView.isAllDaysSelected()) {
			return ProcessDataViewCases.ALL_DAY_SELECTED;
		} else if (processDataView.isADaySelected()) {
			if(processDataView.getDateChooserADay().getDate() == null)
				return ProcessDataViewCases.NO_DATE_SELECTED;
			return ProcessDataViewCases.ONE_DAY_SELECTED;
		} else if (processDataView.isRangeDaysSelected()) {
			if(processDataView.getDateChooserFirstDay().getDate() == null || processDataView.getDateChooserLastDay().getDate() == null)
				return ProcessDataViewCases.NO_DATE_SELECTED;
			return ProcessDataViewCases.RANGE_OF_DAYS_SELECTED;
		} else {
			return ProcessDataViewCases.NO_OPTIONS_SELECTED;
		}

	}
	
	/**
	 * Change the tab to show the WeatherDayView
	 */
	private void changeToWeatherDayTab() {
		WorkWindow.getInstance().setStatus(ApplicationStatus.DATA_PROCESSED);
		WorkWindow.getInstance().setSelectedTab(1);
	}
}