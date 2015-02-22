package view.componentchangelistener;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.util.ApplicationStatus;
import model.util.Util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
				showDataFile(Util.weatherDataFile, wdView);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Puts the data processed in a text area
	 * 
	 * @param file
	 *            which contains the processed data
	 * @param wdv
	 *            an instance of WeahterDayView to update the textArea
	 * @throws ParserConfigurationException
	 *             if the XML file can't be parsed
	 * @throws SAXException
	 * @throws IOException
	 *             if the XML file can't be opened
	 */
	private void showDataFile(File file, WeatherDayView wdv)
			throws ParserConfigurationException, SAXException, IOException {
		if(!wdv.getTextAreaWeatherDay().getText().isEmpty()) {
			wdv.setTextAreaWeatherDay(null);
		}

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		//Declare an entity resolver to get the DTD file 
		docBuilder.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId)
					throws SAXException, IOException {
				if (systemId.contains("weatherDay.dtd")) {
					return new InputSource(
							ClassLoader
									.getSystemResourceAsStream("resources/xml/weatherDay.dtd"));
				} else {
					return null;
				}
			}
		});
		
		Document doc = docBuilder.parse(file);

		NodeList dias = doc.getElementsByTagName("dia");

		JTextArea tArea = wdv.getTextAreaWeatherDay();
		NodeList childs;
		NodeList data;
		NodeList periods;
		String[] periodNames = { "Madrugada", "Manh\u00E3", "Tarde", "Noite" };
		int k;

		Font fontNormal = new Font("Cambria", Font.PLAIN, 18);
		tArea.setFont(fontNormal);
		for (int i = 0; i < dias.getLength(); i++) {
			childs = dias.item(i).getChildNodes();

			data = childs.item(1).getChildNodes();

			tArea.append("Data: ");
			tArea.append(data.item(5).getTextContent() + "-"
					+ data.item(3).getTextContent() + "-"
					+ data.item(1).getTextContent());
			tArea.append("\n");

			tArea.append("\u00CDndice de calor: ");
			tArea.append(childs.item(11).getTextContent() + " �C\n");
			;
			tArea.append("Turnos:\n");

			k = 0;
			for (int j = 3; j < childs.getLength() - 2; j = j + 2) {
				periods = childs.item(j).getChildNodes();

				tArea.append("    " + periodNames[k] + "\n");
				tArea.append("        Press\u00E3o M\u00E9dia: "
						+ periods.item(1).getTextContent() + " hPa\n");
				tArea.append("        Temperatura M\u00EDnima: "
						+ periods.item(3).getTextContent() + " �C\n");
				tArea.append("        Temperatura M\u00E1xima: "
						+ periods.item(5).getTextContent() + " �C\n");
				tArea.append("        Umidade m\u00EDnima: "
						+ periods.item(7).getTextContent() + " %\n");
				tArea.append("        Umidade M\u00E1xima: "
						+ periods.item(9).getTextContent() + " %\n");
				tArea.append("        Velocidade M\u00E1xima do vento: "
						+ periods.item(11).getTextContent()
						+ " m/s - Dire\u00E7\u00E3o: "
						+ periods.item(13).getTextContent() + " �\n");
				tArea.append("        Precipita\u00E7\u00E3o acumulada: "
						+ periods.item(15).getTextContent() + " mm\n");
				tArea.append("\n");
				k++;
			}
			tArea.append("\n");
		}
	}
}