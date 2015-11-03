package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import model.process.WeatherDayFacade;
import model.process.WeatherDay;
import model.util.DateUtil;

/**
 * It's the means to access the weather report
 * 
 * @author Elloa B. Guedes
 *
 */
public class Controller {

	private static Controller instance;
	private WeatherDayFacade wdFacade;
	private BufferedImage[] periodFigures;

	/**
	 * Controller class constructor
	 * 
	 * @throws IOException
	 *             if the file can't be accessed or doesn't exist
	 */
	private Controller() throws IOException {
		wdFacade = new WeatherDayFacade();
	}

	/**
	 * returns the Controller instance (only one instance can exist)
	 * 
	 * @return the Controller instance reference
	 * @throws IOException
	 *             if the class constructor can't create a WeatherDayFacade
	 *             instance
	 */
	public static Controller getInstance() throws IOException, ArrayIndexOutOfBoundsException {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
	
	/**
	 * gets the actual period figures
	 * 
	 * @return the period figures (of one day)
	 */
	public BufferedImage[] getPeriodFigures() {
		return periodFigures;
	}

	/**
	 * Communicates with WeatherDay Facade instance to computes all days
	 */
	public boolean computeWeatherDay() {
		if(wdFacade.readAllDays())
			return true;

		wdFacade.clearDays();
		return false;
	}

	/**
	 * Communicates with WeatherDay Facade instance to computes only a day
	 * 
	 * @param day
	 *            the day to be processed
	 * @return true if the day was computed, false otherwise
	 */
	public boolean computeWeatherDay(String day) {
		if(wdFacade.readADay(day))
			return true;
		wdFacade.clearDays();
		return false;
	}

	/**
	 * Communicates with WeatherDay Facade instance to computes a range of days
	 * 
	 * @param initialDay
	 *            the day to begins the processing
	 * @param finalDay
	 *            the day to finalize the processing
	 * @return true if the range of days were computed, false otherwise
	 */
	public boolean computeWeatherDay(String initialDay, String finalDay) {
		if(wdFacade.readRangeDays(initialDay, finalDay))
			return true;
		
		wdFacade.clearDays();
		return false;
	}
	
	/**
	 * Validates a range of dates
	 * 
	 * @param firstDate
	 *            initial date
	 * @param lastDate
	 *            final date
	 * @return true if the date are chronological
	 */
	public boolean validateDate(String firstDate, String lastDate) {
		return DateUtil.isChronological(firstDate, lastDate);
	}
	
	/**
	 * Clear the reference to processed data in memory
	 */
	public void clear() {
		wdFacade.clearDays();
		periodFigures = null;
	}
	
	/**
	 * generate the images of a period and returns them.
	 * 
	 * @return a vector which contains the figures
	 */
	public BufferedImage[] getImages() {
		periodFigures = wdFacade.generatePeriodFigures();
		return periodFigures;
	}
	
	/**
	 * storage the figures
	 * 
	 * @param saveFile
	 *            reference to save the figures (chosen by the user)
	 * @return a boolean indicating the result
	 */
	public boolean saveFigures(File saveFile) {
			String[] periodsName = new String[] { "madrugada", "manha",
					"tarde", "noite" };
			try {
				for (int i = 0; i < periodFigures.length; i++) {
					ImageIO.write(periodFigures[i], "png",
							new File(saveFile.getAbsolutePath() + "-"
									+ periodsName[i] + ".png"));
				}
				periodFigures = null;
				return true;
			} catch (IOException e) {

			}

		return false;
	}
	
	/**
	 * Indicates if there are still period figures to be generated
	 * 
	 * @return a boolean as an answer
	 */
	public boolean hasPeriodFiguresToGenerate() {
		return wdFacade.getFigureGenerator().hasFiguresToGenerate();
	}
	
	public List<WeatherDay> getProcessedDays() {
		return wdFacade.getDays();
	}
	
	public void saveXMLDataFile(String fPath) {
		wdFacade.saveDays(fPath);
	}
}