package model.componetchangelistener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import view.WeatherDayView;

public class WeatherDayViewCleaner extends ComponentAdapter {

	public WeatherDayViewCleaner() {
		super();
	}

	@Override
	public void componentHidden(ComponentEvent source) {
		WeatherDayView wdv = (WeatherDayView) source.getSource();
		wdv.getTextAreaWeatherDay().setText("");
		wdv.setFilePath("");
	}

}
