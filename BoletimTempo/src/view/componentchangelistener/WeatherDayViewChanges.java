package view.componentchangelistener;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.util.ApplicationStatus;
import model.process.DayPeriod;
import model.process.WeatherDay;
import controller.Controller;
import view.WeatherDayView;
import view.WorkWindow;

/**
 * Implements methods which are called when the component changes
 * 
 * @author Patrick M Lima
 *
 */
public class WeatherDayViewChanges extends ComponentAdapter {
	public WeatherDayViewChanges() {
		super();
	}
	
	@Override
	public void componentShown(ComponentEvent component) {
		// TODO Auto-generated method stub
		WeatherDayView wdView = (WeatherDayView) component.getSource();
		
		try {
			if(WorkWindow.getInstance().getApplicationStatus().ordinal() < ApplicationStatus.PERIOD_FIGURES_SAVED.ordinal())
				showDataFile(wdView);
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Puts the data processed in a text area
	 * 
	 * @param file
	 *            which contains the processed data
	 * @param wdv
	 *            an instance of WeahterDayView to update the textArea
	 * @throws ArrayIndexOutOfBoundsException 
	 * @throws ParserConfigurationException
	 *             if the XML file can't be parsed
	 * @throws SAXException
	 * @throws IOException
	 *             if the XML file can't be opened
	 */
	private void showDataFile(WeatherDayView wdv) throws ArrayIndexOutOfBoundsException, IOException {
		if(!wdv.getTextAreaWeatherDay().getText().isEmpty()) {
			wdv.setTextAreaWeatherDay(null);
		}
		
		JTextArea tArea = wdv.getTextAreaWeatherDay();
		Font fontNormal = new Font("Cambria", Font.PLAIN, 18);
		tArea.setFont(fontNormal);
		
		String precisionFormatter = "%.2f";
		
		List<WeatherDay> days = Controller.getInstance().getProcessedDays();
		
		String[] periodNames = { "Madrugada", "Manh\u00E3", "Tarde", "Noite" };
		int periodNameIndex;
		
		for(WeatherDay wd : days) {
			tArea.append("Data: " + wd.getDay() + "-" + wd.getMonth() + "-" + wd.getYear() + "\n");
			
			tArea.append("    Di\u00E1rio:\n");
			tArea.append("        Temperatura M\u00EDnima: "
					+ String.format(precisionFormatter, wd.getDailyDataLine().getLowTemperature()) 
					+ " ºC\n");
			tArea.append("        Temperatura M\u00E1xima: "
					+ String.format(precisionFormatter, wd.getDailyDataLine().getHighTemperature()) 
					+ " ºC\n");
			tArea.append("        Velocidade M\u00E1xima do vento: "
					+ String.format(precisionFormatter, wd.getDailyDataLine().getWindVelocity())
					+ " m/s\n");
			tArea.append("        Precipita\u00E7\u00E3o acumulada: "
					+ String.format(precisionFormatter, wd.getDailyDataLine().getTotalRain())
					+ " mm\n");			
			tArea.append("        \u00CDndice de calor: ");
			tArea.append(String.format(precisionFormatter, wd.getDailyDataLine().getHeatIndex())
					+ " ºC\n");
			tArea.append("\n");
			
			periodNameIndex = 0;
			for(DayPeriod dp : wd.getDayPeriods()) {
				tArea.append("    " + periodNames[periodNameIndex++] + "\n");
				tArea.append("        Press\u00E3o M\u00E9dia: "
						+ String.format(precisionFormatter, dp.getAvgPressure()) + " hPa\n");
				tArea.append("        Temperatura M\u00EDnima: "
						+ String.format(precisionFormatter, dp.getLowTemp()) + " ºC\n");
				tArea.append("        Temperatura M\u00E1xima: "
						+ String.format(precisionFormatter, dp.getHighTemp()) + " ºC\n");
				tArea.append("        Umidade m\u00EDnima: "
						+ String.format(precisionFormatter, dp.getLowHumid()) + " %\n");
				tArea.append("        Umidade M\u00E1xima: "
						+ String.format(precisionFormatter, dp.getHighHumid()) + " %\n");
				tArea.append("        Velocidade M\u00E1xima do vento: "
						+ String.format(precisionFormatter, dp.getMaxSpeed())
						+ " m/s - Dire\u00E7\u00E3o: "
						+ String.format(precisionFormatter, dp.getMaxDirect()) + " �\n");
				tArea.append("        Precipita\u00E7\u00E3o acumulada: "
						+ String.format(precisionFormatter, dp.getAcumRain()) + " mm\n");
				tArea.append("\n");
			}
		}
	}
}
