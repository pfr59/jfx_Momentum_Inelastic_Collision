package momentum;

// Derived from tutorial: https://www.youtube.com/watch?v=W0_Man88Z3Q&t=90s
// Sample Code: https://github.com/simply-coded/java-tutorials/blob/master/code/tutorial_48.java

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class ProgramLogger {
	
	// Reference for Final Static Variables:
	// https://www.tutorialspoint.com/Final-static-variables-in-Java
	
	private static final Logger LOGGER = Logger.getLogger(ProgramLogger.class.getName());
	
	private static final ProgramLogger INSTANCE = new ProgramLogger();
	
	private ProgramLogger() {
		
		// Clear default handler.
        LogManager.getLogManager().reset();
        
        // Logging level set to "Level.INFO" reports info and more severe events.
        // Logging may be turned off by setting "Level.OFF".
        // Using the following string to set level allows changes to logging 
        // level throughout logger by changing one word.
        String logLevel = "INFO"; 
        LOGGER.setLevel(Level.parse(logLevel));        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.parse(logLevel));
        LOGGER.addHandler(ch);        
        LOGGER.info("Console Handler Set.");

        try {        	
        	FileHandler fh = new FileHandler("momentum.log", false);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.parse(logLevel));
            LOGGER.addHandler(fh);
            LOGGER.info("File Handler Set.");
        } catch (java.io.IOException e) {            
            // don't stop my program but log out to console.
        	LOGGER.log(Level.SEVERE, "File logger not working.", e);
        }

    }
	
	public static ProgramLogger getInstance() {
		return INSTANCE;
	}
	
	public static Logger getLogger() {		
		return LOGGER;
	}
	
}
