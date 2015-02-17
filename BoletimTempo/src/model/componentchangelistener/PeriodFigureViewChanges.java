package model.componentchangelistener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import view.PeriodFigureView;

/**
 * Implements methods to handle the change on component view
 * @author Patrick M Lima
 *
 */
public class PeriodFigureViewChanges extends ComponentAdapter {
	
	public PeriodFigureViewChanges() {
		super();
	}
	
	@Override
	public void componentShown(ComponentEvent component) {
		PeriodFigureView pfView = (PeriodFigureView) component.getSource();
		if (pfView.getPanelShowFigures().getComponentCount() == 0) {
			try {
				pfView.addFigures();
			} catch (NullPointerException | IOException e) {
				// e.printStackTrace();
			}
		}
	}
}
