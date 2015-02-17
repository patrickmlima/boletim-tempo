package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.util.ApplicationStatus;
import model.util.Util;
import view.WeatherDayView;
import view.WorkWindow;
import view.popup.DialogBox;

/**
 * Class which implements the necessary methods to handle the click on 'Salvar'
 * button in WeatherDayView
 * @author Patrick M Lima
 *
 */
public class SaveDataFileBtnHandler implements ActionListener{
	private String title;
	private String msg;
	/**
	 * Constructor
	 */
	public SaveDataFileBtnHandler() {
	}
	
	/**
	 * Method which handle the save action
	 */
	@Override
	public void actionPerformed(ActionEvent action) {
		title = "Arquivo não pôde ser salvo";
		msg = "Verifique se você inseriu um nome ou diretório válido e tente novamente.";
		//Pega a instância de WeatherDayView 
		WeatherDayView wdView = (WeatherDayView) ((JButton) action.getSource()).getParent();
		
		//Cria um JFileChooser para salvar o arquivo
		JFileChooser saveChooser = new JFileChooser();
		//adiciona as extensões suportadas
		saveChooser.setFileFilter(new FileNameExtensionFilter("Arquivo de texto", "txt"));
		saveChooser.setFileFilter(new FileNameExtensionFilter("Arquivo XML", "xml"));
		//remove a extensão 'Todos os arquivos'
		saveChooser.setAcceptAllFileFilterUsed(false);
		//Mostra a janela e captura a opção escolhida pelo usuário
		int option = saveChooser.showSaveDialog(null);
		
		//caso seja a opção 'Salvar'
		if(option == JFileChooser.APPROVE_OPTION) {
			//pega o arquivo selecionado
			File file = saveChooser.getSelectedFile();
			boolean result = false;
			//caso a extensão escolhida seja XML
			if(saveChooser.getFileFilter().getDescription().equals("Arquivo XML")){
				result = saveAsXmlFile(file);
			}
			else {
				result = saveAsTxtFile(file, wdView.getTextAreaWeatherDay());
			}
			
			//analisa o retorno e caso tenha ocorrido tudo bem mostra uma mensagem
			if(result) {
				(new DialogBox()).initialize(" ", "Arquivo salvo com sucesso.", wdView, "OK");
				changeToFiguresTab();
			}else {
				if (title != null)
					(new DialogBox()).initialize(title, msg, wdView, "error");
			}
				
		}
	}	
	
	/**
	 * Method which storage the processed data in a XML file
	 * @param file the file to saves the data
	 * @return a boolean confirming the save success or failure 
	 */
	private boolean saveAsXmlFile(File file) {
		File f = new File(file.getAbsolutePath() + ".xml");
		try {
			return copyXmlFile(Util.weatherDataFile, f);
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Method which storage the processed data in a text file
	 * (like shown in JTextArea on WeatherDayView)
	 * @param file the file to saves the data
	 * @param textArea JTextArea that contains the text which will be save 
	 * @return a boolean confirming the save success or failure
	 */
	private boolean saveAsTxtFile(File file, JTextArea textArea) {
		try {
			File f = new File(file.getAbsolutePath() + ".txt");
			if (chooseReplaceFile(f)) {

				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(f), "UTF-8"));
				out.write(textArea.getText());
				out.flush();
				out.close();

				return true;
			}
		} catch (IOException exc) {

		}
		return false;
	}
	
	/**
	 * method which makes a copy of the XML temporary file that contains the
	 * WeatherDay data processed
	 * @param source the XML temporary file
	 * @param destination the file which will be save
	 * @return the confirmation of the method success of failure
	 * @throws IOException if the file can't be accessed
	 */
	private boolean copyXmlFile(File source, File destination) throws IOException {    
		if (chooseReplaceFile(destination)) {
			FileChannel sourceChannel = null;
			FileChannel destinationChannel = null;

			try {
				sourceChannel = new FileInputStream(source).getChannel();
				destinationChannel = new FileOutputStream(destination)
						.getChannel();
				sourceChannel.transferTo(0, sourceChannel.size(),
						destinationChannel);
			} finally {
				if (sourceChannel != null && sourceChannel.isOpen())
					sourceChannel.close();
				if (destinationChannel != null && destinationChannel.isOpen()) {
					destinationChannel.close();
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Assistant method which verifies the file existence and
	 * give to user the option to replace it. 
	 * @param file the file which will be verified
	 * @return the confirmation if replaced or not
	 */
	private boolean chooseReplaceFile(File file) {
		if (file.exists()) {
			int result = JOptionPane.showConfirmDialog(null,
					"O arquivo com o nome selecionado já existe\n"
							+ "Deseja sobrescrever-lo?", "Aviso",
					JOptionPane.OK_OPTION, JOptionPane.CANCEL_OPTION);
			if (result == JOptionPane.NO_OPTION) {
				title = null;
				return false;
			}
			file.delete();
		}
		return true;
	}
	
	private void changeToFiguresTab() {
		if (WorkWindow.getInstance().getApplicationStatus() == ApplicationStatus.DATA_PROCESSED)
			WorkWindow.getInstance().setStatus(
					ApplicationStatus.DATA_FILE_SAVED);
		WorkWindow.getInstance().setSelectedTab(2);
	}
		
}