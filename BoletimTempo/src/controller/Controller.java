package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	private BufferedImage[] periodFigures;

	/**
	 * 
	 * @throws IOException
	 *             if the file can't be accessed or doesn't exist
	 */
	private Controller() throws IOException {
		wdInterface = new WeatherDayInterface();
	}

	/**
	 * returns the Controller instance (only one instance can exist)
	 * 
	 * @return the Controller instance reference
	 * @throws IOException
	 */
	public static Controller getInstance() throws IOException {
		if (instance == null) {
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
	}

	/**
	 * Communicates with WeatherDay instance and computes only a day
	 * 
	 * @param day
	 *            the day to be processed
	 */
	public boolean computeWeatherDay(String day) {
		if(wdInterface.readADay(day)) {
			wdInterface.saveDays();
			return true;
		}
		wdInterface.clearDays();
		return false;
	}

	/**
	 * Communicates with WeatherDay instance and computes a range of days
	 * 
	 * @param initialDay
	 *            the day to begins the processing
	 * @param finalDay
	 *            the day to finalize the processing
	 */
	public boolean computeWeatherDay(String initialDay, String finalDay) {
		if(wdInterface.readRangeDays(initialDay, finalDay)) {
			wdInterface.saveDays();
			return true;
		}
		wdInterface.clearDays();
		return false;
	}
	
	/**
	 * Validates a range of dates
	 * @param firstDate
	 * @param lastDate
	 * @return true if the date are chronological
	 */
	public boolean validateDate(String firstDate, String lastDate) {
		return DateUtil.isChronological(firstDate, lastDate);
	}
	
	/**
	 * Clear the reference to processed data in memory
	 */
	public void clear() {
		wdInterface.clearDays();
		periodFigures = null;
	}
	
	/**
	 * gets the images of a period.
	 * @return a vector which contains the figures
	 */
	public BufferedImage[] getImages() {
		periodFigures = wdInterface.generatePeriodFigures();
		return periodFigures;
	}
	
	/**
	 * storage the figures
	 * @param saveFile reference to save the figures (chosen by the user)
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
	 * @return a boolean as an answer
	 */
	public boolean hasPeriodFiguresToGenerate() {
		return wdInterface.getFigureGenerator().hasFiguresToGenerate();
	}
}
