package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VisualSID extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Visual SID");
		
		// controls
		FreqBox[] freqBoxen = {
				new FreqBox("Voice 1"),
				new FreqBox("Voice 2"),
				new FreqBox("Voice 3"),
		};
		freqBoxen[0].valueProperty().addListener((observable, oldV, newV) -> {
			System.out.println(newV);
		});
		VBox controls = new VBox(freqBoxen[0].getBox(), freqBoxen[1].getBox(), freqBoxen[2].getBox());
		
		StackPane root = new StackPane();
		root.getChildren().add(controls);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
