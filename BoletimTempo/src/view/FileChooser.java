package view;

import java.io.File;

import javax.swing.JFileChooser;

import view.assist.MyExtensionFileFilter;
import model.util.Util;

public class FileChooser {
	private JFileChooser chooser;
	
	public FileChooser() {
		initialize();	
	}
	
	public void initialize() {
		chooser = new JFileChooser();
		chooser.setFileFilter(new MyExtensionFileFilter(".xml", "xml"));
		chooser.setCurrentDirectory(new File(Util.OUTPUT_FOLDER));
		  
	}
	
	public File getSelectedFile() {
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)   
			   return null;
		return chooser.getSelectedFile();
	}

}
