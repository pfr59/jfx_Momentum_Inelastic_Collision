package momentum;

public class ComputeUnknows {
	
	private double massOneKg;
	private double massTwoKg;
	private double massTotalKg;
	
	private double velocityOneMS;
	private double velocityTwoMS;
	private double velocityFinalMS;
	
	// Clear values when compute "Find Unknowns" button pressed.
	public void clearValues() {
		massOneKg = 0;
		massTwoKg = 0;
		massTotalKg = 0;
		
		velocityOneMS = 0;
		velocityTwoMS = 0;
		velocityFinalMS = 0;		
	}
	
	// Mass setters and getters.
	public void setMassOneKg(String massOne) {	
		String setMassOneLog = "setMassOneKg(String massOne) entered: " + massOne;
		MyProgramLogger.getLogger().info(setMassOneLog);
		massOneKg = Double.parseDouble(massOne);
		MyProgramLogger.getLogger().info(getMassOneKg());
	}
	
	public void setMassTwoKg(String massTwo) {	
		String setMassTwoLog = "setMassTwoKg(String massTwo) entered: " + massTwo;
		MyProgramLogger.getLogger().info(setMassTwoLog);
		massTwoKg = Double.parseDouble(massTwo);
	}
	
	public void setMassTotalKg(String massTotal) {		
		String setMassTotalLog = "setMassTotalKg(String massTotal) entered: " + massTotal;
		MyProgramLogger.getLogger().info(setMassTotalLog);
		massTotalKg = Double.parseDouble(massTotal);
	}	
	
	public String getMassOneKg() {
		MyProgramLogger.getLogger().info("public String getMassOneKg() entered.");
		return String.format("%.3f", massOneKg);		
	}
	
	public String getMassTwoKg() {
		MyProgramLogger.getLogger().info("public String getMassTwoKg() entered.");
		return String.format("%.3f", massTwoKg);		
	}
	
	public String getMassTotalKg() {
		MyProgramLogger.getLogger().info("public String getMassTotalKg() entered.");
		return String.format("%.3f", massTotalKg);		
	}
	
	// Velocity setters and getters.
	public void setVelocityOneMS(String velocityOne) {		
		MyProgramLogger.getLogger().info("setVelocityOneMS(String velocityOne) entered: " + velocityOne);
		velocityOneMS = Double.parseDouble(velocityOne);
	}
	
	public void setVelocityTwoMS(String velocityTwo) {		
		MyProgramLogger.getLogger().info("setVelocityTwoMS(String velocityTwo) entered: " + velocityTwo);
		velocityTwoMS = Double.parseDouble(velocityTwo);
	}
	
	public void setVelocityFinalMS(String velocityFinal) {		
		MyProgramLogger.getLogger().info("setVelocityFinalMS(String velocityFinal) entered: " + velocityFinal);
		velocityFinalMS = Double.parseDouble(velocityFinal);
	}
	
	public String getVelocityOneMS() {
		MyProgramLogger.getLogger().info("public String getVelocityOneMS() entered.");
		return String.format("%.3f", velocityOneMS);	
	}
	
	public String getVelocityTwoMS() {
		MyProgramLogger.getLogger().info("public String getVelocityTwoMS() entered.");
		return String.format("%.3f", velocityTwoMS);		
	}	
	
	public String getVelocityFinalMS() {
		MyProgramLogger.getLogger().info("public String getVelocityFinalMS() entered.");
		return String.format("%.3f", velocityFinalMS);		
	}
	
	// Methods to compute unknowns.
	
	public void findMassOne() {
		MyProgramLogger.getLogger().info("public void findMassOne() entered.");
		
		massOneKg = massTotalKg - massTwoKg;

		massValuesForLog();
	}
	
	public void findMassTwo() {
		MyProgramLogger.getLogger().info("public void findMassTwo() entered.");
		
		massTwoKg = massTotalKg - massOneKg;
		
		massValuesForLog();
	}
	
	public void findMassTotal() {
		MyProgramLogger.getLogger().info("public void findMassTotal() entered.");
		
		massTotalKg = massOneKg + massTwoKg;
		
		massValuesForLog();
	}
	
	// This program solves for the unknown values using the principle of 
	// conservation of momentum in an inelastic collision as expressed by: 
	// (m1 + m2)Vfinal = m1Voriginal + m2Voriginal
	
	public void findVelocityOne() {
		// (m1 + m2)Vfinal = m1Voriginal + m2Voriginal

		velocityOneMS = ( (massTotalKg * velocityFinalMS) - (massTwoKg * velocityTwoMS ) )/massOneKg;
		
		massValuesForLog();
		velocityValuesForLog();
	}
	
	public void findVelocityTwo() {
		// (m1 + m2)Vfinal = m1Voriginal + m2Voriginal

		velocityTwoMS = ( (massTotalKg * velocityFinalMS) - (massOneKg * velocityOneMS) )/massTwoKg;		
		
		massValuesForLog();
		velocityValuesForLog();
	}
	
	public void findVelocityFinal() {
		// (m1 + m2)Vfinal = m1Voriginal + m2Voriginal
		MyProgramLogger.getLogger().info("public void findVelocityFinal() entered.");

		velocityFinalMS = ( (massOneKg * velocityOneMS) + (massTwoKg * velocityTwoMS) )/massTotalKg;
		
		massValuesForLog();
		velocityValuesForLog();
	}
	
	private void massValuesForLog() {
		String strMassOneLog = "massOneKg: " + Double.toString(massOneKg);
		MyProgramLogger.getLogger().info(strMassOneLog);
		
		String strMassTwoLog = "massTwoKg: " + Double.toString(massTwoKg);
		MyProgramLogger.getLogger().info(strMassTwoLog);
		
		String strMassTotalLog = "massTotalKg: " + Double.toString(massTotalKg);
		MyProgramLogger.getLogger().info(strMassTotalLog);
		
	}
	
	private void velocityValuesForLog() {
		String strVelocityOneLog = "velocityOneMS: " + Double.toString(velocityOneMS);
		MyProgramLogger.getLogger().info(strVelocityOneLog);
		
		String strVelocityTwoLog = "velocityTwoMS: " + Double.toString(velocityTwoMS);
		MyProgramLogger.getLogger().info(strVelocityTwoLog);
		
		String strVelocityFinalLog = "velocityFinalMS: " + Double.toString(velocityFinalMS);
		MyProgramLogger.getLogger().info(strVelocityFinalLog);
		
	}
	
	public String getResultsForCSV() {
		
		String strDataRow = Double.toString(massOneKg)+ ", " + Double.toString(massTwoKg) 
			+ ", " + Double.toString(massTotalKg) + ", " + Double.toString(velocityOneMS) 
			+ ", " + Double.toString(velocityTwoMS) + ", " +  Double.toString(velocityFinalMS)
			+"\r\n";
		
		MyProgramLogger.getLogger().info(strDataRow);
		return strDataRow;
		
	}
	
}
