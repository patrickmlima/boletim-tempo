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
		if(source.getActionCommand().equals("Abrir")) {
			if(fpv.getPeriodFigures() != null) {
				fpv.getPeriodFigures().clear();
			}else if(fpv.getTextDate() != null) {
				String day = DateUtil.adjustDate(fpv.getTextDate()).replace("/", ".");
				Image img = null;
				String[] periods = new String[]{"madrugada", "manhã", "tarde", "noite"};
				
				for(String period : periods) {
					 img = new ImageIcon(Util.PERIOD_FIGURES_FOLDER + day + "-" + period + ".png").getImage();
					 if(img != null) {
						 fpv.addPeriodFigure(img);
					 }
				}
				
				if(fpv.getPeriodFigures() != null) {

				}
			}
			
		} else if(source.getActionCommand().equals("Próximo")) {
			//ir para proxima figura
			
		} else if(source.getActionCommand().equals("Anterior")) {
			//voltar para figura anterior
		}		
	}
}
