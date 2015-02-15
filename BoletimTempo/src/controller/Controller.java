package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.process.WeatherDayInterface;
import model.util.CircularArrayList;
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
	private CircularArrayList<BufferedImage[]> periodFigures;

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
	
	public CircularArrayList<BufferedImage[]> getPeriodFigures() {
		return periodFigures;
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

	public boolean validateDate(String firstDate, String lastDate) {
		return DateUtil.isChronological(firstDate, lastDate);
	}
	
	public void clearWeatherDay() {
		wdInterface.clearDays();
	}
	
	public void generatePeriodFigures() {
		this.periodFigures = wdInterface.generatePeriodFigures();
	}
	
	public BufferedImage[] getImages() {
		if (periodFigures != null) {
			if (periodFigures.getIndex() == -1)
				return periodFigures.getNext();
			return periodFigures.get(periodFigures.getIndex());
		}
		return null;
		
	}
	
	public boolean saveFigures(int index, File saveFile) {
		String[] periodsName = new String[]{"madrugada", "manha", "tarde", "noite"};
		BufferedImage[] bi = periodFigures.get(index);
		for(int i = 0; i < bi.length; i++) {
			try {
				ImageIO.write(bi[i], "png", new File(saveFile.getAbsolutePath() + "-" + periodsName[i] + ".png"));
			} catch (IOException e) {
				return false;
			}
		}
		return true;
//		try {
//		File folder = new File(Util.PERIOD_FIGURES_FOLDER);
//		if(!folder.exists()) {
//			folder.mkdir();
//		}
//		//Salvar a imagem
//		ImageIO.write(biToSave, "png", new File(Util.PERIOD_FIGURES_FOLDER + date + "-" + periodName + ".png"));
//	}
//	catch (IOException e) {
//		e.printStackTrace();
//	}
	}
}
