package model.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import view.PeriodFigureView;
import view.popup.DialogBox;

public class ShowFigureBtnsHandler implements ActionListener {

	public ShowFigureBtnsHandler() {
	}

	//
	@Override
	public void actionPerformed(ActionEvent source) {		
		PeriodFigureView pfView = (PeriodFigureView) ((JButton) source.getSource()).getParent();
		
		if(source.getActionCommand().equals("Salvar figuras")) {
			JFileChooser saveImage = new JFileChooser();
			saveImage.setAcceptAllFileFilterUsed(false);
			saveImage.addChoosableFileFilter(new FileNameExtensionFilter("Arquivo de imagem", "png"));
			int option = saveImage.showSaveDialog(null);
			if(option == JFileChooser.APPROVE_OPTION) {
				try {
					if(saveImage(saveImage.getSelectedFile())) {
						(new DialogBox()).initialize("Finalizado", "Figuras salvas com sucesso.", pfView, "OK");
					} else {
						(new DialogBox()).initialize("ERRO", "Figuras não puderam ser salvas.", pfView, "error");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch(NullPointerException e) {
					e.printStackTrace();
				}
			}
			
		} else if(source.getActionCommand().equals("Pr\u00F3ximo")) {
			try {
				Controller.getInstance().getPeriodFigures().getNext();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(source.getActionCommand().equals("Anterior")) {
			try {
				Controller.getInstance().getPeriodFigures().getPrevious();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	private boolean saveImage(File file) throws IOException {
		return Controller.getInstance().saveFigures(Controller.getInstance().getPeriodFigures().getIndex(), file);
	}
}
