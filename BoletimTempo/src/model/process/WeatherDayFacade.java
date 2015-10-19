package model.process;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import model.util.DateUtil;
import model.util.Util;

/**
 * It's the interface to WeatherDay class. Through this class it's possible to
 * access the logic and data within WeatherDay.
 * 
 * @author Patrick M Lima
 *
 */
public class WeatherDayFacade {

	private FileManager fm;
	private FileManager dfm;
	private List<WeatherDay> days;
	private SerializeWeatherDay swd;
	private PeriodFigure figureGenerator;
	private String fileName = "default";

	public WeatherDayFacade() throws IOException, ArrayIndexOutOfBoundsException {
		days = new ArrayList<WeatherDay>();
		checkFiles();
	}
	
	private void checkFiles() throws IOException, ArrayIndexOutOfBoundsException {
		fm = new FileManager(Util.pathBaixa1);
		dfm = new FileManager(Util.pathBaixa2);
		if(fm.hasNextLine() && dfm.hasNextLine()) {
			new DataLine(fm.nextLine());
			new DailyDataLine(dfm.nextLine());
		}
		fm = new FileManager(Util.pathBaixa1);
		dfm = new FileManager(Util.pathBaixa2);
	}

	public PeriodFigure getFigureGenerator() {
		return figureGenerator;
	}

	/**
	 * Reads all days within the file and processes their data
	 */
	public boolean readAllDays() {
		fileName = "allDays";
		while (fm.hasNextLine()) {
			if(!makeWeatherDays(fm.nextLine()) )
				return false;
		}
		process();
		return true;
	}
	
	/**
	 * Reads a range of days (beginning in dInitial and ending in dFinal,
	 * including it) to process their data
	 * 
	 * @param dtInitial
	 *            first day to read
	 * @param dtFinal
	 *            last day to read
	 * @return true if the days were find and processed, false otherwise
	 */
	public boolean readRangeDays(String dtInitial, String dtFinal) {
		fileName = dtInitial.replace("/", ".") + "_to_" + dtFinal.replace("/", ".");
		return findDays(DateUtil.adjustDate(dtInitial), DateUtil.adjustDateToInt(dtFinal));
	}
	
	/**
	 * Reads only a day and processes its data
	 * 
	 * @param day
	 *            the day to be read
	 * @return true if the day was find and processed, false otherwise
	 */
	public boolean readADay(String day) {
		fileName = day.replace("/", ".");
		return findDays(DateUtil.adjustDate(day), DateUtil.adjustDateToInt(day));
	}
	
	/**
	 * Reads a set of data line and process them.
	 * 
	 * @param dateInit
	 *            the initial date
	 * @param dateFinal
	 *            the final date in array format
	 * @return true if the days were find and processed, false otherwise
	 */
	private boolean findDays(String dateInit, int[] dateFinal) {
		String line = null;
		DataLine dline = null;
		
		while(fm.hasNextLine()) {
			dline = new DataLine(fm.nextLine());
			if(dline.getDate().equals(dateInit) && dline.getHour() >= 0 && dline.getMinute() >= 0) {
				break;
			}
		}
		
		while(fm.hasNextLine()) {
			line = fm.nextLine();
			if(!makeWeatherDays(line))
				return false;
			dline = new DataLine(line);
			if(dline.equals(dateFinal[0]+1, dateFinal[1], dateFinal[2]) && dline.getHour() == 0 && dline.getMinute() == 0) {
				break;
			}
		}
		if(this.days.isEmpty())
			return false;
		else {
			process();
			return true;
		}
		
	}
	
	/**
	 * Makes the WeatheDays (creates the DataLine objects and separates the turns)
	 * @param line a string which contains the data.
	 */
	public boolean makeWeatherDays(String line) {
		DataLine dl = new DataLine(line);
		
		boolean found = false;
		for (WeatherDay weatherDay : days) {
			if ( weatherDay.isSameWeatherDay(dl.getYear(), dl.getMonth(), dl.getDay(), dl.getHour(), dl.getMinute()) ) {
				found = true;
				weatherDay.addMeasurement(dl);
			}
		}

		if (!found){
			String dLine = null;
			if(dfm.hasNextLine() ) {
				DailyDataLine ddl;
				while(dfm.hasNextLine()) {
					dLine = dfm.nextLine();
					ddl = new DailyDataLine(dLine);
					if(ddl.getYear() > dl.getYear())
						return false;
					else if(ddl.getYear() == dl.getYear()) {
						if(ddl.getMonth() > dl.getMonth())
							return false;
						else if(ddl.getMonth() == dl.getMonth()) {
							if(ddl.getDay() > dl.getDay())
								return false;
							else if(ddl.getDay() == dl.getDay())
								break;
						}
					}
				}
			} else
				return false;
			WeatherDay wd = new WeatherDay(dl.getYear(), dl.getMonth(), dl.getDay());
			wd.addMeasurement(dl);
			wd.addDailyData(dLine);
			days.add(wd);
		}
		return true;
	}
	
	/**
	 * Process the measurements in days list
	 */
	public void process() {
		// process the data about weather days
		for (WeatherDay weatherDay : days) {
			weatherDay.processMeasurements();
		}
		figureGenerator = new PeriodFigure(days);
	}
	
	/**
	 * Saves the data in the days list in a XML file and generate the turn
	 * figures.
	 */
	public void saveDays() {
		swd = new SerializeWeatherDay(days, fileName);
		try {
			swd.writeDaybyDay();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates the period figures of a day
	 * 
	 * @return a vector with 4 period figures
	 */
	public BufferedImage[] generatePeriodFigures() {
		return figureGenerator.generateADayFigures();
	}
	
	/**
	 * Clear the weatherDay data processed references as well the fileManager
	 */
	public void clearDays() {
		this.days.clear();
		try {
			fm = new FileManager(Util.pathBaixa1);
			dfm = new FileManager(Util.pathBaixa2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}