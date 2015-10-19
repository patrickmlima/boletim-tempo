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
	private String fileName;

	/**
	 * Makes a file from a set of meteorological days passed by parameter
	 * 
	 * @param list
	 *            list of WeatherDay objects which are going to be saved
	 * @param fileName
	 *            the name of the temporary file (date of the day processed)
	 */
	public SerializeWeatherDay(List<WeatherDay> list, String fileName) {
		this.days = list;
		this.fileName = fileName;
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

		Element dias = doc.createElement("dias");
		doc.appendChild(dias);
		String precFormat = "%.2f";
		for (WeatherDay weatherDay : days) {


			// Dia
			Element dia = doc.createElement("dia");
			dias.appendChild(dia);

			// Data
			Element data = doc.createElement("data");
			dia.appendChild(data);

			Element ano = doc.createElement("ano");
			ano.appendChild(doc.createTextNode(Integer.toString(weatherDay.getYear())));
			data.appendChild(ano);
			
			// Mês
			Element mes = doc.createElement("mes");
			mes.appendChild(doc.createTextNode(Integer.toString(weatherDay.getMonth())));
			data.appendChild(mes);
			// Número do dia
			Element nDia = doc.createElement("nDia");
			nDia.appendChild(doc.createTextNode(Integer.toString(weatherDay.getDay())));
			data.appendChild(nDia);

			int counter = 0;
			List<DayPeriod> turnos = weatherDay.getDayPeriods();

			for (DayPeriod dayPeriod : turnos) {

				String sTurno = Util.ranksPeriod(counter);

				// Elementos de cada dia		
				Element turno = doc.createElement(sTurno);
				dia.appendChild(turno);
				{
					Element pressaoMedia = doc.createElement("pressao_media");
					pressaoMedia.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getAvgPressure())));
					turno.appendChild(pressaoMedia);
					
					Element temperaturaMin = doc.createElement("temperatura_minima");
					temperaturaMin.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getLowTemp())));
					turno.appendChild(temperaturaMin);

					Element temperaturaMax = doc.createElement("temperatura_maxima");
					temperaturaMax.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getHighTemp())));
					turno.appendChild(temperaturaMax);

					Element umidadeMin = doc.createElement("umidade_minima");
					umidadeMin.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getLowHumid())));
					turno.appendChild(umidadeMin);

					Element umidadeMax = doc.createElement("umidade_maxima");
					umidadeMax.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getHighHumid())));
					turno.appendChild(umidadeMax);

					Element speed = doc.createElement("velocidade_maxima");
					speed.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getMaxSpeed())));
					turno.appendChild(speed);

					Element direction = doc.createElement("direcao_velocidade_maxima");
					direction.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getMaxDirect())));
					turno.appendChild(direction);

					Element rain = doc.createElement("precipitacao_acumulada");
					rain.appendChild(doc.createTextNode(String.format(precFormat,dayPeriod.getAcumRain())));
					turno.appendChild(rain);
				}
				
				counter++;

			}
			Element indiceCalor = doc.createElement("indice_de_calor");
			indiceCalor.appendChild(doc.createTextNode(String.format(precFormat,weatherDay.getHeatIndex())));
			dia.appendChild(indiceCalor);

		}

		final Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"weatherDay.dtd");
		File outputFile = File.createTempFile(fileName, ".xml");
		outputFile.deleteOnExit();
		transformer.transform(new DOMSource(doc), new StreamResult(outputFile));
		Util.weatherDataFile = outputFile;
	}
}