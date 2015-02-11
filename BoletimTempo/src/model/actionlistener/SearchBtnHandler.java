package model.actionlistener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import view.FileChooser;
import view.WeatherDayView;

public class SearchBtnHandler implements ActionListener {
	public SearchBtnHandler() {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton c = (JButton) arg0.getSource();
		WeatherDayView wdv = (WeatherDayView) c.getParent();

		FileChooser fc = new FileChooser();
		File f = fc.getSelectedFile();
		if (f != null) {
			try {
				wdv.getTextAreaWeatherDay().setText("");
				showDataFile(f, wdv);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
}
