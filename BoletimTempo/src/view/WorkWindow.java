package view;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.FontUIResource;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import view.popup.DialogBox;
import model.util.ApplicationStatus;

/**
 * Main application view which brings together all components which implements
 * the system functionalities
 * 
 * @author Patrick M Lima
 *
 */
public class WorkWindow {
	private ApplicationStatus status;
	private static WorkWindow instance;
	private JFrame frame;
	private JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	private WorkWindow() {
		setDialogFonts();
		initialize();
		status = ApplicationStatus.INITIALIZED;
	}

	/**
	 * Sets the application status
	 * 
	 * @param status
	 *            the status to be defined
	 */
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	/**
	 * Gets the application status
	 * 
	 * @return the current application status
	 */
	public ApplicationStatus getApplicationStatus() {
		return status;
	}

	/**
	 * Gets the JTabbedPane where the components are bringing together
	 * 
	 * @return a JTabbedPane instance
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Gets the current work window instance
	 * 
	 * @return the WorkWindow instance
	 */
	public static WorkWindow getInstance() {
		if (instance == null) {
			instance = new WorkWindow();
		}
		return instance;
	}

	/**
	 * Gets the application frame
	 * 
	 * @return a JFrame instance
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Sets the application window not closable
	 */
	public void setNotClosable() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Sets the application window closable
	 */
	public void setClosable() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Sets the selected tab and shows your content
	 * 
	 * @param index
	 *            the tab index
	 */
	public void setSelectedTab(int index) {
		if (index < tabbedPane.getTabCount()) {
			tabbedPane.setSelectedIndex(index);
			tabbedPane.setEnabledAt(index, true);

			configureTabbedPaneEnable();
			tabbedPane.revalidate();
		}
	}

	/**
	 * Configures the enabled state from the tabs of the application
	 */
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
	 * Sets the font used in the application dialogs
	 */
	private void setDialogFonts() {
		Font fontPopup = new Font("Cambria Math", Font.PLAIN, 18);
		UIManager.put("OptionPane.messageFont", new FontUIResource(fontPopup));
		UIManager.put("OptionPane.buttonFont", new FontUIResource(fontPopup));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("BoCLIMA ");
		int height = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() - 60;
		frame.setBounds(100, 100, (int)(height*1.33333), height);
		frame.setResizable(false);
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
		
        frame.addWindowListener(new WindowAdapter()  
        {  
        	
            public void windowClosing (WindowEvent e)  
            {  
				if (frame.getDefaultCloseOperation() == JFrame.EXIT_ON_CLOSE) {
					new DialogBox();
					// caixa de dialogo retorna a opção
					int option = JOptionPane.showConfirmDialog(null,
							"Deseja mesmo fechar o BoCLIMA?", "Finalizar",
							JOptionPane.YES_NO_OPTION);
					// se sim
					if (option == JOptionPane.YES_OPTION) {
						System.exit(0);
					//senão
					} else {
						frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					}
				}                
            }
            
            //quando a janela volta a estar ativa (após o fechamento do dialog anterior)
            @Override
            public void windowActivated(WindowEvent e) {
            	//torna-a novamente "fechável"
            	setClosable();
            }
            
        }); 
		
		frame.setVisible(true);
	}
}