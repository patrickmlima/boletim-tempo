package model;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * It's the interface to WeatherDay class. Through this class it's possible
 * to access the logic and data within WeatherDay.
 * @author Patrick M Lima
 *
 */
public class WeatherDayInterface {
	
	private FileManager fm;
	private List<WeatherDay> days;
	private SerializeWeatherDay swd;
	private PeriodFigure turnGen;
	private String fileName = "default";
	
	public WeatherDayInterface() throws IOException {
		fm = new FileManager();
		days = new ArrayList<WeatherDay>(); 
		
	}

	/**
	 * Reads all days within the file and processes their data
	 */
	public void readAllDays(){
		fileName = "allDays";
		while (fm.hasNextLine()){
			makeWeatherDays(fm.nextLine());
		}	
		process();
	}
	
	/**
	 * Reads a range of days (beginning in dInitial and ending in dFinal, including it)
	 * to process their data
	 * @param dtInitial first day to read
	 * @param dtFinal last day to read
	 */
	public void readRangeDays(String dtInitial, String dtFinal) {
		fileName = dtInitial.replace("/", ".") + "_to_" + dtFinal.replace("/", ".");

		findDays(adjustDate(dtInitial), adjustDateToInt(dtFinal));

		System.out.println("Conjunto de dias processados.");
	}
	
	/**
	 * Reads only a day and processes its data
	 * @param day the day to be read
	 */
	public void readADay(String day) {
		fileName = day.replace("/", ".");
		
		findDays(adjustDate(day), adjustDateToInt(day));
		
		System.out.println("Único dia processado.");
	}
	
	/**
	 * Reads a set of data line and process them.
	 * @param dateInit the initial date 
	 * @param dateFinal the final date in array format 
	 */
	private void findDays(String dateInit, int[] dateFinal) {
		String line = null;
		DataLine dline = null;
		
		while(fm.hasNextLine()) {
			dline = new DataLine(fm.nextLine());
			if(dline.getDate().equals(dateInit) && dline.getHour() == 0 && dline.getMinute() == 0) {
				break;
			}
		}
		
		while(fm.hasNextLine()) {
			line = fm.nextLine();
			makeWeatherDays(line);
			dline = new DataLine(line);
			if(dline.equals(dateFinal[0]+1, dateFinal[1], dateFinal[2]) && dline.getHour() == 0 && dline.getMinute() == 0) {
				break;
			}
		}
		
		process();
	}
	
	/**
	 * Adjusts a date to an array format (with day, month and year sequence)
	 * @param day the date to convert (needs to be separated by '/')
	 * @return an integer array which represents the date
	 */
	private int[] adjustDateToInt(String day) {
		String[] dayArray = day.split("/");
		int[] d = new int[dayArray.length]; 
		for(int i = 0; i < dayArray.length; i++) {
			d[i] = Integer.parseInt(dayArray[i]);
		}
		return d;
	}
	
	/**
	 * Adjusts a date to the form used in DataLine class (which converts
	 * the integer date in a String).
	 * @param day the data to adjust
	 * @return a String with the date adjusted
	 */
	private String adjustDate(String day) {
		int[] d = adjustDateToInt(day);
		return "" + d[0] + "/" + d[1] + "/" + d[2];
	}
	
	/**
	 * Makes the WeatheDays (creates the DataLine objects and separates the turns)
	 * @param line a string which contains the data.
	 */
	public void makeWeatherDays(String line) {
		DataLine dl = new DataLine(line);

		boolean found = false;
		for (WeatherDay weatherDay : days) {
			if ( weatherDay.isSameWeatherDay(dl.getYear(), dl.getMonth(), dl.getDay(), dl.getHour(), dl.getMinute()) ) {
				found = true;
				weatherDay.addMeasurement(dl);
			}
		}

		if (!found){
			WeatherDay wd = new WeatherDay(dl.getYear(), dl.getMonth(), dl.getDay());
			wd.addMeasurement(dl);
			days.add(wd);
		}
	}
	
	/**
	 * Process the measurements in days list
	 */
	synchronized public void process() {
		for (WeatherDay weatherDay : days) {
			weatherDay.processMeasurements();
		}
	}
	
	/**
	 * Saves the data in the days list in a XML file and generate the turn figures.
	 */
	public void saveDays(){
		swd = new SerializeWeatherDay(days, fileName);
		turnGen = new PeriodFigure(days);
		try {
			swd.writeDaybyDay();
			turnGen.generateFigures();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clearDays() {
		this.days.clear();
	}
	
	
}
