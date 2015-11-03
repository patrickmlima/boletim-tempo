package model.process;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.util.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class which receive a set of meteorological days and make the serialization
 * in a XML file
 * 
 * @author Elloá B. Guedes
 *
 */
public class SerializeWeatherDay {

	private List<WeatherDay> days;
	private String filePath;

	/**
	 * Makes a file from a set of meteorological days passed by parameter
	 * 
	 * @param list
	 *            list of WeatherDay objects which are going to be saved
	 * @param fileName
	 *            the name of the temporary file (date of the day processed)
	 */
	public SerializeWeatherDay(List<WeatherDay> list, String filePath) {
		this.days = list;
		this.filePath = filePath;
	}

	/**
	 * Process and writes the data about the meteorological days in a temporary
	 * file
	 * 
	 * @throws Throwable
	 *             if there are problems in the file writing
	 */
	public void writeDaybyDay() throws Throwable {
		DocumentBuilderFactory dayBuilder = DocumentBuilderFactory
				.newInstance();
		dayBuilder.setNamespaceAware(true);
		dayBuilder.setValidating(true);

		DocumentBuilder docBuilder = dayBuilder.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		doc.setXmlStandalone(true);

		Element days = doc.createElement("dias");
		doc.appendChild(days);
		String precFormat = "%.2f";
		for (WeatherDay weatherDay : this.days) {


			// day
			Element day = doc.createElement("dia");
			days.appendChild(day);

			// Date
			Element date = doc.createElement("data");
			day.appendChild(date);

			// Year
			Element year = doc.createElement("ano");
			year.appendChild(doc.createTextNode(Integer.toString(weatherDay.getYear())));
			date.appendChild(year);
			
			// Month
			Element month = doc.createElement("mes");
			month.appendChild(doc.createTextNode(Integer.toString(weatherDay.getMonth())));
			date.appendChild(month);
			
			// day's number
			Element nDay = doc.createElement("nDia");
			nDay.appendChild(doc.createTextNode(Integer.toString(weatherDay.getDay())));
			date.appendChild(nDay);

			int counter = 0;
			List<DayPeriod> turnos = weatherDay.getDayPeriods();

			for (DayPeriod dayPeriod : turnos) {

				String sPeriod = Util.ranksPeriod(counter);

				// Elementos de cada dia		
				Element period = doc.createElement(sPeriod);
				day.appendChild(period);
				{
					Element meanPressure = doc.createElement("pressao_media");
					meanPressure.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getAvgPressure())));
					period.appendChild(meanPressure);
					
					Element lowTemperature = doc.createElement("temperatura_minima");
					lowTemperature.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getLowTemp())));
					period.appendChild(lowTemperature);

					Element highTemperature = doc.createElement("temperatura_maxima");
					highTemperature.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getHighTemp())));
					period.appendChild(highTemperature);

					Element lowHumidity = doc.createElement("umidade_minima");
					lowHumidity.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getLowHumid())));
					period.appendChild(lowHumidity);

					Element highHumidity = doc.createElement("umidade_maxima");
					highHumidity.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getHighHumid())));
					period.appendChild(highHumidity);

					Element windSpeed = doc.createElement("velocidade_maxima");
					windSpeed.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getMaxSpeed())));
					period.appendChild(windSpeed);

					Element windDirection = doc.createElement("direcao_velocidade_maxima");
					windDirection.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getMaxDirect())));
					period.appendChild(windDirection);

					Element totalRain = doc.createElement("precipitacao_acumulada");
					totalRain.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getAcumRain())));
					period.appendChild(totalRain);
				}
				
				counter++;

			}
			Element daily = doc.createElement("diario");
			day.appendChild(daily);
			
			Element dLowTemperature = doc.createElement("temperatura_minima");
			dLowTemperature.appendChild(doc.createTextNode(String.format(precFormat, weatherDay.getDailyDataLine().getLowTemperature())));
			daily.appendChild(dLowTemperature);
			
			Element dHighTemperature = doc.createElement("temperatura_maxima");
			dHighTemperature.appendChild(doc.createTextNode(String.format(precFormat, weatherDay.getDailyDataLine().getHighTemperature())));
			daily.appendChild(dHighTemperature);
			
			Element dWindVelocity = doc.createElement("velocidade_maxima");
			dWindVelocity.appendChild(doc.createTextNode(String.format(precFormat, weatherDay.getDailyDataLine().getWindVelocity())));
			daily.appendChild(dWindVelocity);
			
			Element dTotalRain = doc.createElement("precipitacao_acumulada");
			dTotalRain.appendChild(doc.createTextNode(String.format(precFormat, weatherDay.getDailyDataLine().getTotalRain())));
			daily.appendChild(dTotalRain);
			
			Element indiceCalor = doc.createElement("indice_de_calor");
			indiceCalor.appendChild(doc.createTextNode(String.format(precFormat,weatherDay.getDailyDataLine().getHeatIndex())));
			daily.appendChild(indiceCalor);
		}

		final Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"weatherDay.dtd");
		File outputFile = new File(filePath);
		transformer.transform(new DOMSource(doc), new StreamResult(outputFile));
	}
}