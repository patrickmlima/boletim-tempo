package model.process;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import model.util.Util;

/**
 * Class which generate a period figure (figure which illustrates the data
 * obtained in a period processing )
 * 
 * @author Patrick M Lima
 */
public class PeriodFigure {
	private List<WeatherDay> weatherDay;

	private int X_PERIOD;
	private int Y_PERIOD = 370;

	/**
	 * The class constructor which initializes the weatherDay class attribute
	 * 
	 * @param weatherDay
	 *            a list containing WeatherDay objects (where from the figures
	 *            will be generated)
	 */
	public PeriodFigure(List<WeatherDay> weatherDay) {
		this.weatherDay = weatherDay;
	}
	
	/**
	 * returns a boolean which represents if there are still figures to be
	 * generated
	 * 
	 * @return true if the figures still can be generated, false otherwise
	 */
	public boolean hasFiguresToGenerate() {
		if (this.weatherDay == null)
			return false;
		return true;
	}
	
	
	/**
	 * Takes the first weatherDay List attribute, find the periods and send them
	 * to the figure being organized
	 * 
	 * @return a vector with 4 period figures (periods of one day) or null if
	 *         there's no data to generate the figures
	 */
	public BufferedImage[] generateADayFigures() {
		if (!weatherDay.isEmpty()) {
			int count;
			
			WeatherDay wd = weatherDay.get(0);
			String date = wd.getWeatherDayDate(".", 1);
			count = 0;
			String periodName = "";
			BufferedImage[] bi = new BufferedImage[5];
			for (DayPeriod period : wd.getDayPeriods()) {
				periodName = Util.ranksPeriod(count);

				bi[count++] = organizeImage(date, periodName, period.getHighTemp(),
						period.getLowTemp(), period.getMaxSpeed(), period.getAcumRain(), wd.getHeatIndex());
			}
			System.out.println("gerando imagem diária");
			DailyDataLine ddl = wd.getDailyDataLine();
			periodName = Util.ranksPeriod(count);
			System.out.println(periodName);
			bi[count] = organizeImage(date, periodName, ddl.getHighTemperature(), ddl.getLowTemperature(),
					ddl.getWindVelocity(), ddl.getTotalRain(), ddl.getHeatIndex());
			System.out.println("figura diária gerada");
			weatherDay.remove(0);
			return bi;
		}
		weatherDay = null;
		return null;
	}
	
	/**
	 * Sets up the image to be saved
	 * 
	 * @param dayPeriod
	 *            a DayPeriod object which represents a period
	 * @param date
	 *            the date of the DayPeriod object
	 * @param periodName
	 *            a specific period name which can be either, dawn, morning,
	 *            afternoon, night.
	 * @return a period figure
	 */
	private BufferedImage organizeImage(String date, String periodName, double highTemp, double lowTemp, 
			double windVelocity, double acumRain, double heatIndex) {
		Graphics2D g2d = null;
		BufferedImage biToSave = null;
		Image img = null;

		if (periodName.equals("madrugada")) {
			X_PERIOD = 715;
		} else {
			X_PERIOD = 1250;
		}
		
		if( periodName.equals("manha") || periodName.equals("tarde") ) {
			if(acumRain == 0.0) {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/sunny.png")).getImage();
			} else {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/rainy.png")).getImage();
			}
		} else if(periodName.equals("noite") || periodName.equals("madrugada")) {
			if(acumRain == 0.0) {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/night.png")).getImage();
			}
			else {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/rainy_night.png")).getImage();
			}
		} else {
			img = new ImageIcon(ClassLoader.getSystemResource("resources/img/daily.png")).getImage();
		}
		
		if (periodName.equals("manha")) {
			periodName = "manhã";
		}
		
		biToSave = new BufferedImage(img.getWidth(null), img.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		
		g2d = (Graphics2D) biToSave.getGraphics();
		g2d.drawImage(img, 0, 0, null);

		g2d.setColor(new Color(51,51,51));
		g2d.setFont(new Font("Cambria", Font.BOLD, 240));
		g2d.drawString(periodName.toUpperCase(), X_PERIOD, Y_PERIOD);
		g2d.setFont(new Font("Cambria", Font.BOLD, 220));
		g2d.drawString(date.replace(".", "/"), 1040, 660);

		g2d.setFont(new Font("Cambria", Font.BOLD, 200));
		
		g2d.drawString(String.format("%.1f", highTemp ).replace(",", ".") + "°C", 1500, 1450);
		g2d.drawString(String.format("%.1f", lowTemp ).replace(",", ".") + "°C", 1500, 1760);
		g2d.drawString(String.format("%.1f", windVelocity).replace(",", ".") + " m/s", 50, 2900);
		g2d.drawString(String.format("%.1f", heatIndex).replace(",", ".") + "°C",  1500, 2900);
		if(acumRain > 0.0) {
			g2d.drawString( String.format("%.1f", acumRain).replace(",", ".") + " mm", 50, 1400);
		}
		g2d.dispose();
		
		System.out.println("fim organizar figura");
		return biToSave;
	}
}