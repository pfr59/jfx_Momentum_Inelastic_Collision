/*
 * Original file used statements like:
 * System.out.println("Method: openFile()");
 * for debugging. Replaced with calls to MyProgramLogger August 23, 2023.
 */
package momentum;

import java.io.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SaveToCSVFile {
	
    File fileCSV;
	
	// create a File chooser
    FileChooser filechooser = new FileChooser();
    
	// Create header row.
	String headerRow = "Mass 1 (kg), Mass 2 (kg), Mass Total (kg), Velocity 1 (m/s), Velocity 2 (m/s), Velocity Final (m/s), \r\n";

	public void openFile() {
//		System.out.println("Method: openFile()");
		MyProgramLogger.getLogger().info("Entered: public void openFile()");
		filechooser.setInitialDirectory(new File("C:\\Users"));
		filechooser.setTitle("Open File");
		setFileChoserExtenstions();
		fileCSV = filechooser.showOpenDialog(null);
	}
	
	public void saveNewFile() {
//		System.out.println("Method: saveFile()");
		MyProgramLogger.getLogger().info("Entered: public void saveNewFile()");
		filechooser.setTitle("Save File");
		setFileChoserExtenstions();	
		filechooser.setInitialFileName("*.csv");
		filechooser.setInitialDirectory(new File("C:\\Users"));
		fileCSV = filechooser.showSaveDialog(null);
		if(fileCSV != null) {
			writeToFile(headerRow);
		}
	}
	
	private void setFileChoserExtenstions() {
		// File extensions as per: https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
		filechooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.csv", "*.txt", "*.doc"),
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
	}
	
	public void writeToFile(String writeRow) {
//		System.out.println("Method: writeToFile()");
		MyProgramLogger.getLogger().info("Entered: public void writeToFile(String writeRow)");
		
		if(fileCSV != null) {
			try
			{
				FileWriter writer;
				writer = new FileWriter(fileCSV, true);
				writer.write(writeRow);
				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
