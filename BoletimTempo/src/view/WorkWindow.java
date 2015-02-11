package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class WorkWindow {
	private static WorkWindow instance;
	private JFrame frame;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkWindow window = new WorkWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private WorkWindow() {
		initialize();
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

	public void setNotClosable() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	public void setClosable() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setSelectedTab(int index) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (index < tabbedPane.getComponentCount()) {
					tabbedPane.setSelectedIndex(index);
				}
			}
		});
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
				.addTab("Abrir Boletim", null, weatherDayView,
						"Selecione para abrir e visualizar os dados do boletim meteorol\u00F3gico");

		PeriodFigureView figurePeriodView = new PeriodFigureView();
		tabbedPane.addTab("Abrir Figura", null, figurePeriodView,
				"Selecione para abrir as figuras de um dia meteorol\u00F3gico");
		frame.getContentPane().setLayout(groupLayout);
//		configureTabbedPaneEnable();
	}
}
