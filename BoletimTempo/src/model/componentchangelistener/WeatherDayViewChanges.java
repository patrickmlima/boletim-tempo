package model.componentchangelistener;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.util.Util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import view.WeatherDayView;

public class WeatherDayViewChanges extends ComponentAdapter {
	public WeatherDayViewChanges() {
		super();
	}
	
	@Override
	public void componentShown(ComponentEvent component) {
		// TODO Auto-generated method stub
		WeatherDayView wdView = (WeatherDayView) component.getSource();
		
		try {
			showDataFile(Util.weatherDataFile, wdView);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showDataFile(File file, WeatherDayView wdv)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();

		Document doc = docBuilder.parse(file);

		NodeList dias = doc.getElementsByTagName("dia");

		JTextArea tArea = wdv.getTextAreaWeatherDay();
		NodeList childs;
		NodeList data;
		NodeList periods;
		String[] periodNames = { "Madrugada", "Manhã", "Tarde", "Noite" };
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

			tArea.append("Índice de calor: ");
			tArea.append(childs.item(11).getTextContent() + " °C\n");
			;
			tArea.append("Turnos:\n");

			k = 0;
			for (int j = 3; j < childs.getLength() - 2; j = j + 2) {
				periods = childs.item(j).getChildNodes();

				tArea.append("    " + periodNames[k] + "\n");
				tArea.append("        Pressão Média: "
						+ periods.item(1).getTextContent() + " hPa\n");
				tArea.append("        Temperatura Mínima: "
						+ periods.item(3).getTextContent() + " °C\n");
				tArea.append("        Temperatura Máxima: "
						+ periods.item(5).getTextContent() + " °C\n");
				tArea.append("        Umidade mínima: "
						+ periods.item(7).getTextContent() + " %\n");
				tArea.append("        Umidade Máxima: "
						+ periods.item(9).getTextContent() + " %\n");
				tArea.append("        Velocidade Máxima do vento: "
						+ periods.item(11).getTextContent()
						+ " m/s - Direção: "
						+ periods.item(13).getTextContent() + " °\n");
				tArea.append("        Precipitação acumulada: "
						+ periods.item(15).getTextContent() + " mm\n");
				tArea.append("\n");
				k++;
			}
			tArea.append("\n");
		}
	}
	
//	public void saveAsTxt() {
//		try {  
//
//			File f = new File( "conteudo.txt" );  
//
//			PrintWriter out = new PrintWriter( new FileOutputStream( f ) );  
//
//			out.print( "ao conteúdo da JTextArea vem aqui..." );  
//			out.flush();  
//
//			out.close();  
//
//		} catch ( IOException exc ) {
//
//		}  
//	}
}
