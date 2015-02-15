package view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;

import model.util.ApplicationStatus;

public class WorkWindow {
	private ApplicationStatus status;
	private static WorkWindow instance;
	private JFrame frame;
	private JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	private WorkWindow() {
		initialize();
		status = ApplicationStatus.INITIALIZED;
	}
	
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	
	public ApplicationStatus getStatus() {
		return status;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public static WorkWindow getInstance() {
		if (instance == null) {
			instance = new WorkWindow();
		}
		return instance;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setNotClosable() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void setClosable() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setSelectedTab(int index) {
		if(index < tabbedPane.getTabCount()) {
			tabbedPane.setSelectedIndex(index);
			tabbedPane.setEnabledAt(index, true);

			configureTabbedPaneEnable();
			tabbedPane.revalidate();
		}
	}

	public void configureTabbedPaneEnable() {
		int selected = tabbedPane.getSelectedIndex();
		switch (selected) {
		case 0:
			tabbedPane.setEnabledAt((selected++) + 1, false);
			
		case 1:
			tabbedPane.setEnabledAt(selected + 1, false);
			break;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("BoCLIMA");
		frame.setBounds(100, 100, 800, 600);
		frame.setLocationRelativeTo(null);
		setClosable();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE));

		ProcessDataView processDataView = new ProcessDataView();
		tabbedPane.addTab("Processar Dados", null, processDataView,
				"Selecione para processar os dados meteorol\u00F3gicos");

		WeatherDayView weatherDayView = new WeatherDayView();
		tabbedPane
				.addTab("Ver Boletim", null, weatherDayView,
						"Selecione para visualizar os dados do boletim meteorol\u00F3gico");

		PeriodFigureView figurePeriodView = new PeriodFigureView();
		tabbedPane.addTab("Ver Figura dos turnos", null, figurePeriodView,
				"Selecione para ver as figuras de um dia meteorol\u00F3gico");
		frame.getContentPane().setLayout(groupLayout);
		configureTabbedPaneEnable();
		
		frame.setVisible(true);
	}
}
