package model.componentchangelistener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.util.ImageUtil;
import controller.Controller;
import view.PeriodFigureView;

public class PeriodFigureViewChanges extends ComponentAdapter {
	
	public PeriodFigureViewChanges() {
		super();
	}
	
	@Override
	public void componentShown(ComponentEvent component) {
		PeriodFigureView pfView = (PeriodFigureView) component.getSource();
		try {
			addFigures(pfView);
		} catch (NullPointerException | IOException e) {
//			e.printStackTrace();
		}
	}
	
	@Override
	public void componentResized(ComponentEvent component) {
		componentShown(component);
	}
	
	private void addFigures(PeriodFigureView pfView) throws IOException, NullPointerException {
		pfView.getPanelShowFigures().removeAll();
		int w = pfView.getPanelShowFigures().getWidth()/4 -10;
		int h = pfView.getPanelShowFigures().getHeight() -10;

		Controller controller = null;
		controller = Controller.getInstance();
		
		for (BufferedImage bi : controller.getImages()) {
			JLabel label = new JLabel();
			label.setSize(w, h);
			label.setIcon(new ImageIcon(ImageUtil.resize(bi, w, h)));
			pfView.getPanelShowFigures().add(label);
		}
	}

}
