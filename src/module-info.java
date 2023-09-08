module jfx_Momentum_Inelastic_Collision {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	
	opens momentum to javafx.graphics, javafx.fxml;
}
