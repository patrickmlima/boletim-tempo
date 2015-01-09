package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.DataLine;
import model.FileManager;
import model.SerializeWeatherDay;
import model.WeatherDay;
import model.TurnFigure;


/**
 * É o meio de acesso à lógica do Boletim-Tempo.
 * 
 * @author Elloa
 *
 */
public class Controller {

	private FileManager fm;
	private List<WeatherDay> days;
	private SerializeWeatherDay swd;
	private TurnFigure turnGen;
	private static Controller instance;


	/**
	 * 
	 * @throws IOException se o arquivo não pode ser acessado ou se não existe.
	 */
	private Controller() throws IOException{
		fm = new FileManager();
		days = new ArrayList<WeatherDay>();
	}
	
	public static Controller getInstance() throws IOException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}


	/**
	 * Lè o arquivo baixa1 e faz todo o processamento.
	 */
	public void readFile(){


		while (fm.hasNextLine()){
			DataLine dl = new DataLine(fm.nextLine());
			boolean found = false;
			for (WeatherDay weatherDay : days) {
				if (weatherDay.equals(dl.getYear(),dl.getMonth(),dl.getDay())){
					found = true;
					weatherDay.addMeasurement(dl);
				}
			}

			if (!found){
				WeatherDay wd = new WeatherDay(dl.getYear(),dl.getMonth(),dl.getDay());
				wd.addMeasurement(dl);
				days.add(wd);
			}
			
			

		}
		
		for (WeatherDay weatherDay : days) {
			weatherDay.processMeasurements();
		}


	}
	
	/**
	 * Escreve todos os dados de todos os dias no arquivo XML.
	 */
	public void saveAllDays(){
		swd = new SerializeWeatherDay(days);
		turnGen = new TurnFigure(days);
		try {
			swd.writeDaybyDay();
			turnGen.generateFigures();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
