package model.actionlistener;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import view.PeriodFigureView;
import model.util.DateUtil;
import model.util.Util;

public class ShowFigureHandler implements ActionListener {
	PeriodFigureView fpv;

	public ShowFigureHandler(PeriodFigureView fpv) {
		this.fpv = fpv;
	}

	//
	@Override
	public void actionPerformed(ActionEvent source) {
		if(source.getActionCommand().equals("Salvar")) {
			//salvar
			
		} else if(source.getActionCommand().equals("Próximo")) {
			//ir para proxima figura
			
		} else if(source.getActionCommand().equals("Anterior")) {
			//voltar para figura anterior
		}		
	}
}
