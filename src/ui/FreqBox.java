package ui;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

class FreqBox {
	private final HBox box;
	private final Spinner<Integer> spinner;
	private final SpinnerValueFactory<Integer> spinFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 0xffff, 0);
	/* TODO: builtin integer handling is piss poor- if box isn't editable, it works well enough, but 64k is to large a range
	 * to step through entirely, and I'd rather not separate into two byte-size spinners (even though that's how the SID does
	 * it internally...) Anywho, if editable, all sorts of little bugs creep in- typing an out of range value gets corrected,
	 * but still fires the out of range event first, then the corrected event.  If a non-number is entered, it throws an 
	 * exception.  No good.  Following site has an improved editor/validator that can handle at least the non-number case...
	 * https://news.kynosarges.org/2016/10/28/javafx-spinner-for-numbers/
	 * ... but this will do for now.
	 */
	
	FreqBox(String lbl) {
		spinner = new Spinner<>();
		spinner.setValueFactory(spinFactory);
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
