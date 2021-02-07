package ui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

class FreqBox {
	private final HBox box;
	private final Spinner<Integer> spinner;
	
	FreqBox(String lbl) {
		spinner = new Spinner<>();//0, 0xffff, 0, 1);
		spinner.valueFactoryProperty().set(new HexSpinnerValueFactory(0, 0xffff, 0, 1));
		spinner.setEditable(true);
		
		Label label = new Label(lbl);
		box = new HBox(label, spinner);
	}
	
	final ReadOnlyObjectProperty<Integer> valueProperty() {
		return spinner.valueProperty();
	}

	HBox getBox() {
		return box;
	}
}
