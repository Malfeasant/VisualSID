package ui;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VisualSID extends Application {
	
	Property<Integer>[] registers;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Visual SID");
		
		var openItem = new MenuItem("Open...");
		openItem.setOnAction(e -> {
			var chooser = new FileChooser();
			chooser.setTitle("Open a .sid file");
			chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SID Files", "*.sid"));
			var selected = chooser.showOpenDialog(primaryStage);
			if (selected != null) {
				// TODO something
			}
		});
		var fileMenu = new Menu("File", null, openItem);
		var menubar = new MenuBar(fileMenu);
		
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
		
		var root = new BorderPane(controls, menubar, null, null, null);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
