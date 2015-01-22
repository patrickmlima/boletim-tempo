package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.DataLine;
import model.FileManager;
import model.SerializeWeatherDay;
import model.WeatherDay;
import model.PeriodFigure;

import java.util.Scanner;

/**
 * It's the means to access the weather report 
 * 
 * @author Elloa
 *
 */
public class Controller {

	private FileManager fm;
	private List<WeatherDay> days;
	private SerializeWeatherDay swd;
	private PeriodFigure turnGen;
	private static Controller instance;
	private String fileName = "default";
	Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * @throws IOException if the file can't be accessed or doesn't exist
	 */
	private Controller() throws IOException{
		fm = new FileManager();
		days = new ArrayList<WeatherDay>();
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
	 * Reads all days within the file and processes their data
	 */
	public void readAllDays(){
		fileName = "allDays";
		String line;
		while (fm.hasNextLine()){
			line = fm.nextLine();
//			System.out.println(line);
			makeWeatherDays(line);
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
		
	}
	
	/**
	 * Reads only a day and processes its data
	 * @param day the day to be read
	 */
	public void readADay(String day) {
		int[] d = adjustDateToInt(day);
		fileName = day.replace("/", ".");
		day = adjustDate(day);
		String line = null;
		DataLine dline = null;
		
		while(fm.hasNextLine()) {
			line = fm.nextLine();
			dline = new DataLine(line);
			if(dline.getDate().equals(day) && dline.getHour() == 0 && dline.getMinute() == 0) {
				break;
			}
		}
		
		while(fm.hasNextLine()) {
			line = fm.nextLine();
			makeWeatherDays(line);
			dline = new DataLine(line);
			if(d[0] + 1 == dline.getDay() && dline.getHour() == 0 && dline.getMinute() == 0) {
				break;
			}
		}
		process();
		System.out.println("Dia " + day + " processado.");
	}
	
	private int[] adjustDateToInt(String day) {
		String[] dayArray = day.split("/");
		int[] d = new int[dayArray.length]; 
		for(int i = 0; i < dayArray.length; i++) {
			d[i] = Integer.parseInt(dayArray[i]);
		}
		return d;
	}
	
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
		System.out.println(dl.getDate() + " -- " + dl.getHour() + ":" + dl.getMinute());
		boolean found = false;
		for (WeatherDay weatherDay : days) {
			if ( weatherDay.isSamePeriod(dl.getYear(), dl.getMonth(), dl.getDay(), dl.getHour(), dl.getMinute()) ) {
				found = true;
				weatherDay.addMeasurement(dl);
//				System.out.println("adicionou weatherDay");
			}
		}

		if (!found){
			WeatherDay wd = new WeatherDay(dl.getYear(), dl.getMonth(), dl.getDay());
			wd.addMeasurement(dl);
			days.add(wd);
//			System.out.println("criou weatherDay");
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



}
