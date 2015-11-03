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
			count = 0;
			String periodName = "";
			BufferedImage[] bi = new BufferedImage[4];
			for (DayPeriod period : wd.getDayPeriods()) {
				periodName = Util.ranksPeriod(count);

				String date = Integer.toString(wd.getDay()) + "."
						+ Integer.toString(wd.getMonth()) + "."
						+ Integer.toString(wd.getYear());

				bi[count] = organizeImage(period, date, periodName,
						wd.getDailyDataLine().getHeatIndex());
				count++;
			}
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
	private BufferedImage organizeImage(DayPeriod dayPeriod, String date,
			String periodName, double heatIndex) {
		Graphics2D g2d = null;
		BufferedImage biToSave = null;
		Image img = null;

		if (periodName.equals("madrugada")) {
			X_PERIOD = 715;
		} else {
			X_PERIOD = 1250;
		}
		
		if( periodName.equals("manha") || periodName.equals("tarde") ) {
			if(dayPeriod.getAcumRain() == 0.0) {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/sunny.png")).getImage();
			} else {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/rainy.png")).getImage();
			}
		} else {
			if(dayPeriod.getAcumRain() == 0.0) {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/night.png")).getImage();
			}
			else {
				img = new ImageIcon(ClassLoader.getSystemResource("resources/img/rainy_night.png")).getImage();
			}
		}
		
		if (periodName.equals("manha")) {
			periodName = "manhã";
		}
		
		biToSave = new BufferedImage(img.getWidth(null), img.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		
		g2d = (Graphics2D) biToSave.getGraphics();
		g2d.drawImage(img, 0, 0, null);

		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Cambria", Font.BOLD, 240));
		g2d.drawString(periodName.toUpperCase(), X_PERIOD, Y_PERIOD);
		g2d.setFont(new Font("Cambria", Font.BOLD, 220));
		g2d.drawString(date.replace(".", "/"), 1040, 680);

		g2d.setFont(new Font("Cambria", Font.BOLD, 200));
		
		g2d.drawString(String.format("%.1f", dayPeriod.getHighTemp() ).replace(",", ".") + "°C", 1500, 1370);
		g2d.drawString(String.format("%.1f", dayPeriod.getLowTemp() ).replace(",", ".") + "°C", 1500, 1700);
		g2d.drawString(String.format("%.1f", dayPeriod.getMaxSpeed()).replace(",", ".") + " km/h", 50, 2900);
		g2d.drawString(String.format("%.1f", heatIndex).replace(",", ".") + "°C",  1500, 2900);
		if(dayPeriod.getAcumRain() > 0.0) {
			g2d.drawString( String.format("%.1f", dayPeriod.getAcumRain()).replace(",", ".") + " mm", 50, 1400);
		}
		g2d.dispose();

		return biToSave;
	}
}