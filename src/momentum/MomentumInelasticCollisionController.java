package momentum;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MomentumInelasticCollisionController {
	
	@FXML
	TextField massOneTxtFld;
	
	@FXML
	TextField massTwoTxtFld;
	
	@FXML
	TextField massTotalTxtFld;
	
	@FXML
	TextField velocityOneTxtFld;
	
	@FXML
	TextField velocityTwoTxtFld;
	
	@FXML
	TextField velocityFinalTxtFld;
	
	// Used to assure only two entries each for mass and velocity.
	Boolean twoInputsForMassAndVelocity = true;
	
	// Used to assure computations run if and only entries are numeric.
	Boolean entriesAreNumeric = true;

	ComputeUnknows findMyUnknowns = new ComputeUnknows();
	
	SaveToCSVFile saveInCSV = new SaveToCSVFile();
	
	// Must be declared here to make use of CSS!!
	// Declaring it inside a method did not work!
	DialogPane dialogPane;
	
	public void closeApp() {
		MyProgramLogger.getLogger().info("closeApp() entered. Program will be closed.");
        Platform.exit();
        System.exit(0);
	}	
	
	public void findUnknown() {
		MyProgramLogger.getLogger().info("findUnknown() entered.");
		
		// Each time "Find Unknowns" button is pressed, the following
		// booleans are reset as true.
		twoInputsForMassAndVelocity = true;
		entriesAreNumeric = true;
		
		findMyUnknowns.clearValues();
		checkForTooManyInputs();
		
		if(twoInputsForMassAndVelocity) {
			readInputs();
		}
		
		if(entriesAreNumeric) {
			findAnswers();
		}
		
		if(saveInCSV.fileCSV != null) {
			saveInCSV.writeToFile(findMyUnknowns.getResultsForCSV());
		}
	}
	
	// Check for too many inputs.
	
	private void checkForTooManyInputs() {
		MyProgramLogger.getLogger().info("checkForTooManyInputs() entered.");
		threeEntryChk(massOneTxtFld, massTwoTxtFld, massTotalTxtFld, "MASS" );
		threeEntryChk(velocityOneTxtFld, velocityTwoTxtFld, velocityFinalTxtFld, "VELOCITY" );		
	}
	
	// Check for three entries of mass or velocity.
	private void threeEntryChk(TextField txtFldOne, TextField txtFldTwo, TextField txtFldThree, String strMassOrVel ) {
		
		MyProgramLogger.getLogger().info("threeEntryChk(TextField txtFldOne, TextField txtFldTwo, TextField txtFldThree, String strMassOrVel )");
		
		if( !isBlank(txtFldOne.getText()) && !isBlank(txtFldTwo.getText()) && !isBlank(txtFldThree.getText())) {
			Alert alertAbout = new Alert(AlertType.ERROR);
			dialogPane = alertAbout.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toString());
			// Select CSS class as a function of input.
			if(strMassOrVel =="MASS") {
				dialogPane.getStyleClass().add("MassDialog");
			}else {
				dialogPane.getStyleClass().add("VelocityDialog");
			}
			alertAbout.setTitle("TOO MANY " + strMassOrVel + " ENTRIES!");
			alertAbout.setHeaderText(null);
			alertAbout.setContentText("ONE AND ONLY ONE VALUE FOR " + strMassOrVel + " MUST BE BLANK!");
			alertAbout.showAndWait();
			twoInputsForMassAndVelocity = false;
		}
	}

	private void readInputs() {
		MyProgramLogger.getLogger().info("readInputs() entered.");

		if( isNumeric(massOneTxtFld.getText())) {
			findMyUnknowns.setMassOneKg(massOneTxtFld.getText());
		}		
		
		if( isNumeric(massTwoTxtFld.getText()) ) {
			findMyUnknowns.setMassTwoKg(massTwoTxtFld.getText());
		}		
		
		if( isNumeric(massTotalTxtFld.getText()) ) {
			findMyUnknowns.setMassTotalKg(massTotalTxtFld.getText());
		}		
		
		if( isNumeric(velocityOneTxtFld.getText()) ) {
			findMyUnknowns.setVelocityOneMS(velocityOneTxtFld.getText());
		}		
		
		if( isNumeric(velocityTwoTxtFld.getText()) ) {
			findMyUnknowns.setVelocityTwoMS(velocityTwoTxtFld.getText());
		}		
		
		if( isNumeric(velocityFinalTxtFld.getText()) ) {
			findMyUnknowns.setVelocityFinalMS(velocityFinalTxtFld.getText());
		}
	}
	
	private void findAnswers() {
		
		MyProgramLogger.getLogger().info("findAnswers() entered.");
		
		if( isBlank(massOneTxtFld.getText()) ) {
			findMyUnknowns.findMassOne();
			massOneTxtFld.setText(findMyUnknowns.getMassOneKg());
		}	
		
		if( isBlank(massTwoTxtFld.getText()) ) {
			findMyUnknowns.findMassTwo();
			massTwoTxtFld.setText(findMyUnknowns.getMassTwoKg());
		}
		
		if( isBlank(massTotalTxtFld.getText()) ) {
			findMyUnknowns.findMassTotal();
			massTotalTxtFld.setText(findMyUnknowns.getMassTotalKg());
		}
		
		if( isBlank(velocityOneTxtFld.getText()) ) {
			findMyUnknowns.findVelocityOne();
			velocityOneTxtFld.setText(findMyUnknowns.getVelocityOneMS());
		}
		
		if( isBlank(velocityTwoTxtFld.getText()) ) {
			findMyUnknowns.findVelocityTwo();
			velocityTwoTxtFld.setText(findMyUnknowns.getVelocityTwoMS());
		}
		
		if( isBlank(velocityFinalTxtFld.getText()) ) {
			findMyUnknowns.findVelocityFinal();
			velocityFinalTxtFld.setText(findMyUnknowns.getVelocityFinalMS());
		}
		
	}
	
	// Taken from: https://www.baeldung.com/java-check-string-number
	public boolean isNumeric(String strNum) {
		MyProgramLogger.getLogger().info("public static boolean isNumeric(String strNum) entered.");
	    if (strNum == null || strNum.trim().isEmpty() ) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	        String numberChk = "Number Entered and Checked: " + Double.toString(d);
	        MyProgramLogger.getLogger().info(numberChk);
		    return true;
	    } catch (NumberFormatException nfe) {
			MyProgramLogger.getLogger().info("public static boolean isNumeric(String strNum) is FALSE.");
			// Message box created as per: https://code.makery.ch/blog/javafx-dialogs-official/
			Alert alertAbout = new Alert(AlertType.ERROR);
			dialogPane = alertAbout.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toString());
			dialogPane.getStyleClass().add("NotANumberDialog");
			alertAbout.setTitle("A VALUE ENTERED IS NOT A NUMBER!");
			alertAbout.setHeaderText(null);
			alertAbout.setContentText("A VALUE ENTERED IS NOT A NUMBER!");
			alertAbout.showAndWait();
			entriesAreNumeric = false;
	        return false;
	    } 

	}
	
	// Solved blank check problem with ".trim().isEmpty()"
	// from: https://stackoverflow.com/questions/32866937/how-to-check-if-textfield-is-empty
	public static boolean isBlank(String strNum) {
		
		String strBlankLog = "public static boolean isBlank(String strNum) strNum: " + strNum;
		MyProgramLogger.getLogger().info(strBlankLog);
		
		if (strNum == null || strNum.trim().isEmpty() ) {
	    	MyProgramLogger.getLogger().info("isBlank(String strNum): TRUE");
	        return true;	        
	    }else {
	    	MyProgramLogger.getLogger().info("isBlank(String strNum): FALSE");
	        return false;
	    }
	}
	
	public void openCSV() {
		MyProgramLogger.getLogger().info("public saveToCSV() entered.");
		saveInCSV.openFile();		
	}
	
	public void saveToCSV() {
		MyProgramLogger.getLogger().info("public void saveToCSV() entered.");		
		saveInCSV.saveNewFile();		
	}
	
	public void aboutApp() {
		MyProgramLogger.getLogger().info("public void aboutApp() entered.");
		// Message box created as per: https://code.makery.ch/blog/javafx-dialogs-official/
		Alert alertAbout = new Alert(AlertType.INFORMATION);
		alertAbout.setTitle("About This Program");
		alertAbout.setHeaderText(null);
		alertAbout.setContentText("This Program was developed as an exercise in using "
				+ "Scene Builder, JavaFX, Logging, CSV file open and save operations " 
				+ "and CSS for styling GUI." );
		alertAbout.showAndWait();
	}
	
	// Allows hidden button to display current logging level.
	public void hiddenButton() {
		MyProgramLogger.getLogger().info("public void hiddenButton()entered.");
		// Message box created as per: https://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("YOU FOUND THE HIDDEN BUTTON");
		alert.setHeaderText(null);
		alert.setContentText("You have found the hidden button! The current logging level is: "
				+ MyProgramLogger.getLogger().getLevel().toString());
		alert.showAndWait();		
	}

}
