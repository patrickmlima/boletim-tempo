package model;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * Class which generate a turn figure (figure which illustrates  the data
 * obtained in a period processing 
 * @author Patrick M Lima
 * Date: 09/01/2015
 */
public class PeriodFigure {
	private List<WeatherDay> weatherDay;
	
	private int X_PERIOD;
	private int Y_PERIOD = 370;
	
	/**
	 * The class constructor which initializes the weatherDay class attribute
	 * @param weatherDay a list containing WeatherDay objects
	 */
	public PeriodFigure(List<WeatherDay> weatherDay) {
		this.weatherDay = weatherDay; 
	}
	
	/**
	 * Takes the weatherDay List attribute, runs it, find the periods and send them to
	 * the figure being generated 
	 */
	public void generateFigures() {
		int count;
		String periodName;
		for(WeatherDay wd : weatherDay) {
			
			count = 0;
			periodName = "";
			for(DayPeriod period : wd.getDayPeriods()) {
				periodName = Util.ranksPeriod(count);
				
				String date = Integer.toString(wd.getDay()) +"."+ Integer.toString(wd.getMonth()) +"."+ Integer.toString(wd.getYear());
				
				organizeImage(period, date, periodName);
				
				count++;
			}
		}
		System.out.println("Todas as figuras foram geradas");
	}
	
	/**
	 * Mount the image to be saved
	 * @param dayPeriod a DayPeriod object which represents a turn
	 * @param date the date of the object DayPeriod
	 * @param turnName a specific turn name which can be either, dawn, morning, afternoon, night.
	 */
	public void organizeImage(DayPeriod dayPeriod, String date, String periodName) {
		Graphics2D g2d = null;
		BufferedImage biToSave = null;
		Image img = null;
		
		if( periodName.equals("madrugada")) {
			X_PERIOD = 715;
		} else {
			X_PERIOD = 1250;
		}
		
		if( periodName.equals("manha") || periodName.equals("tarde") ) {
			if(dayPeriod.getAcumRain() == 0.0) {
				img = new ImageIcon(Util.SUNNY).getImage();
			} else {
				img = new ImageIcon(Util.RAINY).getImage();
			}
		} else {
			if(dayPeriod.getAcumRain() == 0.0) {
				img = new ImageIcon(Util.NIGHT).getImage();
			}
			else {
				img = new ImageIcon(Util.RAINY_NIGHT).getImage();
			}
		}
		
		if(periodName.equals("manha")) {
			periodName = "manhã";
		}
		
		biToSave = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		g2d = (Graphics2D) biToSave.getGraphics();
		g2d.drawImage(img, 0, 0, null);
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Cambria", Font.BOLD, 240));	
		g2d.drawString(periodName.toUpperCase(), X_PERIOD, Y_PERIOD);
		g2d.setFont(new Font("Cambria", Font.BOLD, 220));
		g2d.drawString(date.replace(".",  "/"), 1040, 680);
		
		g2d.setFont(new Font("Cambria", Font.BOLD, 200));
		
		g2d.drawString(String.format("%.2f", dayPeriod.getHighTemp() ).replace(",", ".") + "°C", 1500, 1370);
		g2d.drawString(String.format("%.2f", dayPeriod.getLowTemp() ).replace(",", ".") + "°C", 1500, 1700);
		g2d.drawString(String.format("%.2f", dayPeriod.getMaxSpeed()).replace(",", ".") + " km/h", 50, 2900);
		g2d.drawString(String.format("%.2f", 30.555).replace(",", ".") + "°C",  1500, 2900);
		if(dayPeriod.getAcumRain() > 0.0) {
			g2d.drawString( String.format("%.2f", dayPeriod.getAcumRain()).replace(",", ".") + " mm", 50, 1400);
		}
		g2d.dispose();
		
		try {
			//Salvar a imagem
			ImageIO.write(biToSave, "png", new File(Util.PERIOD_FIGURES_FOLDER + date + "-" + periodName + ".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

/*	//**
	 * Resize a image to a shorter size
	 * @param im the Image object to be decreased
	 * @param w an integer which represents the new width
	 * @param h an integer which represents the new height
	 * @return a BufferedImage object (a Image decreased)
	 *//*
	private BufferedImage reduceSize(Image im, int w, int h) {
		BufferedImage bfi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) bfi.createGraphics();
		g2.drawImage(im, 0, 0, w, h, null);
		
		return bfi;
	}*/
}
