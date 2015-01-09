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
 * obtained in a turn processing 
 * @author Patrick M Lima
 *
 */
public class TurnFigure {
	private Image imSun;
	private Image imRain;
	private Image imWind;
	private Image imThermo;
	private List<WeatherDay> weatherDay;
	
	/**
	 * The class Constructor which load the images that are used
	 * @param weatherDay
	 */
	public TurnFigure(List<WeatherDay> weatherDay) {
		this.weatherDay = weatherDay; 
		imSun = new ImageIcon(Util.IM_SUN).getImage();
		imRain = new ImageIcon(Util.IM_RAIN).getImage();
		imWind = new ImageIcon(Util.IM_WIND).getImage();
		imThermo = reduceSize(new ImageIcon(Util.IM_THERMO).getImage(), 40, 140);
	}
	
	/**
	 * Takes the weatherDay List attribute, runs it, find the turns and send them to
	 * the figure being generated 
	 */
	public void generateFigures() {
		int count;
		String turnName;
		for(WeatherDay wd : weatherDay) {
			
			count = 0;
			turnName = "";
			for(DayPeriod turn : wd.getDayPeriods()) {
				if(count == 0) {
					turnName = "Madrugada";
				} else if(count == 1) {
					turnName = "Manhã";
				} else if(count == 2) {
					turnName = "Tarde";
				} else {
					turnName = "Noite";
				}
				
				String date = Integer.toString(wd.getDay()) +"."+ Integer.toString(wd.getMonth()) +"."+ Integer.toString(wd.getYear());
				organizeImage(turn, date, turnName);
				
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
	public void organizeImage(DayPeriod dayPeriod, String date, String turnName) {
			
		BufferedImage biToSave = new BufferedImage(420, 500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) biToSave.getGraphics();
		
		g2.setBackground(Color.WHITE);
		g2.fillRect(0, 0, biToSave.getWidth(), biToSave.getHeight());
		
		g2.setColor(Color.BLACK);
		Font font = new Font("Times New Roman", Font.BOLD, 30);
		g2.setFont(font);
		
		if(dayPeriod.getAcumRain() > 0.0) {
			g2.drawImage(imRain, 17, 12, null);
			g2.drawString(Double.toString(dayPeriod.getAcumRain())+" mm", 30, imRain.getHeight(null)+30);
		}
		else {
			g2.drawImage(imSun, 10, 10,null);
		}
		g2.drawString(turnName, imSun.getWidth(null)+100, 65);
		g2.drawString(date, imSun.getWidth(null)+105, 95);
		g2.drawString(Double.toString(dayPeriod.getHighTemp()) + " °C", imSun.getWidth(null)+110, 190);
		g2.drawString(Double.toString(dayPeriod.getLowTemp()) + " °C", imSun.getWidth(null)+110, 250);
		g2.drawImage(imWind, 10, 280, null);
		g2.drawString(Double.toString(dayPeriod.getMaxSpeed()) + " m/s", 30, 430);
		g2.drawImage(imThermo, imSun.getWidth(null)+120, 300, null);
		g2.drawString("duvida", imSun.getWidth(null)+175, 369);
		g2.dispose();
		
		try {
			//Salvar a imagem
			ImageIO.write(biToSave, "png", new File(Util.OUTPUT_FOLDER+"turn_figures"+Util.SEPARATOR+date+"-"+turnName+".png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Resize a image to a shorter size
	 * @param im the Image object to be decreased
	 * @param w an integer which represents the new width
	 * @param h an integer which represents the new height
	 * @return a BufferedImage object (a Image decreased)
	 */
	public BufferedImage reduceSize(Image im, int w, int h) {
		BufferedImage bfi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) bfi.createGraphics();
		g2.drawImage(im, 0, 0, w, h, null);
		
		return bfi;
	}
}
