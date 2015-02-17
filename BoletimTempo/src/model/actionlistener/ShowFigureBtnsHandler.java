package model.actionlistener;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.util.ApplicationStatus;
import controller.Controller;
import view.PeriodFigureView;
import view.WorkWindow;
import view.popup.DialogBox;

/**
 * Class which implements the handler of the PeriodFigureView Buttons
 * @author Patrick M Lima
 *
 */
public class ShowFigureBtnsHandler implements ActionListener {

	public ShowFigureBtnsHandler() {
	}

	@Override
	public void actionPerformed(ActionEvent source) {
		PeriodFigureView pfView = (PeriodFigureView) ((JButton) source
				.getSource()).getParent();
		// caso não haja period figures para salvar
		if (!hasPeriodFigures()) {
			(new DialogBox())
					.initialize(
							"Você gerou todas as figuras",
							"Retorne à primeira aba para processar mais dados\n ou finalize a aplicação.",
							pfView, "information");
			//muda o status da aplicação
			WorkWindow.getInstance().setStatus(
					ApplicationStatus.PERIOD_FIGURES_SAVED);
			//remove as figuras do JPanel
			pfView.getPanelShowFigures().removeAll();
			pfView.revalidate();
			return;
		}

		// caso o botão salvar tenha sido clicado
		if (source.getActionCommand().equals("Salvar figuras")) {
			// cria um file chooser para salvar as imagens
			JFileChooser saveImage = new JFileChooser();
			saveImage.setAcceptAllFileFilterUsed(false);
			saveImage.addChoosableFileFilter(new FileNameExtensionFilter(
					"Arquivo de imagem (*.png)", "png"));
			// pega a opção escolhida pelo usuário
			int option = saveImage.showSaveDialog(null);

			// caso o usuário tenha clicado em salvar
			if (option == JFileChooser.APPROVE_OPTION) {
				
				WorkWindow.getInstance().setNotClosable();
				// muda o cursor do mouse para espera
				WorkWindow
						.getInstance()
						.getFrame()
						.setCursor(
								Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				// recebe o resultado da tentativa de persistência da imagem
				boolean result = saveImage(saveImage.getSelectedFile());
				// muda o cursos do mouse para o padrão
				WorkWindow.getInstance().getFrame()
						.setCursor(Cursor.getDefaultCursor());

				// caso nenhum erro tenha ocorrido mostra uma mensagem
				// informando o sucesso
				if (result) {
					(new DialogBox()).initialize("Finalizado",
							"Figuras salvas com sucesso.", pfView, "OK");
					
					try {
						pfView.addFigures();
					} catch (IOException e) {
	
					}

					// caso não seja possível salvar as imagens, mostra uma
					// mensagem informando o fato.
				} else {
					(new DialogBox())
							.initialize(
									"ERRO",
									"Figuras não puderam ser salvas.\nVerifique se você inseriu um nome\nou diretório válido.",
									pfView, "error");
				}
				WorkWindow.getInstance().setClosable();

			}
		
		//caso o botão descartar tenha sido clicado
		} else if(source.getActionCommand().equals("Descartar")) {
			//mostra uma mensagem informando as consequências do descarte
			int result = JOptionPane
					.showConfirmDialog(
							null,
							"Descartando essas imagens não será possível salvá-las novamente\n "
							+ "a menos que um novo processamento deste dia seja feito.\n"
									+ "Deseja mesmo descartar as imagens?",
							"Atenção", JOptionPane.OK_OPTION,
							JOptionPane.CANCEL_OPTION);
			//se o usuário desejar descartar
			if(result == JOptionPane.OK_OPTION) {
				try {
					pfView.addFigures();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	/**
	 * Saves the figures of a day chosen by the user 
	 * @param file which the user selected (in a JFileChooser)
	 * @return boolean information about the success or failure
	 * @throws IOException if the controller instance can't be taken
	 */
	private boolean saveImage(File file){
		try {
			return Controller.getInstance().saveFigures(file);
		} catch (IOException | NullPointerException e) {
			
		}
		return false;
	}
	
	/**
	 * Verifies if there are still period figures to be saved
	 * @return a boolean information about the success or failure
	 */
	private boolean hasPeriodFigures() {
		try {
			return Controller.getInstance().hasPeriodFiguresToGenerate() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
