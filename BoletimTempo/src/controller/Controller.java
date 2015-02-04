package controller;

import java.io.IOException;

import model.process.WeatherDayInterface;
import model.util.DateUtil;

/**
 * It's the means to access the weather report 
 * 
 * @author Elloa
 *
 */
public class Controller {

	private static Controller instance;
	private WeatherDayInterface wdInterface;

	/**
	 * 
	 * @throws IOException if the file can't be accessed or doesn't exist
	 */
	private Controller() throws IOException{
		wdInterface = new WeatherDayInterface();
	}
	
	/**
	 * returns the Controller instance (only one instance can exist)
	 * @return the Controller instance reference
	 * @throws IOException
	 */
	public static Controller getInstance() throws IOException{
		if (instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	/**
	 * Communicates with WeatherDay instance and computes all days
	 */
	public void computeWeatherDay() {
		wdInterface.readAllDays();
		wdInterface.saveDays();
		wdInterface.clearDays();
	}
	
	/**
	 * Communicates with WeatherDay instance and computes only a day
	 * @param day the day to be processed 
	 */
	public void computeWeatherDay(String day) {
		wdInterface.readADay(day);
		wdInterface.saveDays();
		wdInterface.clearDays();
	}
	
	/**
	 * Communicates with WeatherDay instance and computes a range of days
	 * @param initialDay the day to begins the processing
	 * @param finalDay the day to finalize the processing
	 */
	public void computeWeatherDay(String initialDay, String finalDay) {
		wdInterface.readRangeDays(initialDay, finalDay);
		wdInterface.saveDays();
		wdInterface.clearDays();
	}
	
	public boolean validateDate(String date) {
		return DateUtil.isValidDate(date);
	}
	
	public boolean validateDate(String firstDate, String lastDate) {
		if(validateDate(firstDate) && validateDate(lastDate)) {
			return DateUtil.isChronological(firstDate, lastDate);
		}
		return false;
	}
}
