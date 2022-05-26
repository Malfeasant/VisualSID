package ui;

import javafx.application.Platform;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.converter.IntegerStringConverter;

public class HexStringConverter extends IntegerStringConverter {
	private final TextField field;
	private final IntegerSpinnerValueFactory factory;
	
	public static void createIn(Spinner<Integer> spinner) {
		IntegerSpinnerValueFactory factory = (IntegerSpinnerValueFactory) spinner.getValueFactory();
		factory.converterProperty().set(new HexStringConverter(spinner.getEditor(), factory));
	}
	
	private HexStringConverter(TextField tf, IntegerSpinnerValueFactory fact) {
		field = tf;
		factory = fact;
		Runnable update = () -> {
			// do a little dance- makes sure string can be converted to int and back,
			// then sets the text in the edit box to match.
			// reason it's done this way- either have to do the same check twice,
			// or field and value could get out of sync.
			field.textProperty().set(toString(fromString(field.getText())));
			field.selectAll();	// otherwise caret ends up at beginning of field- annoying
		};
		field.focusedProperty().addListener(inv -> {	// react when user clicks out of the field
			Platform.runLater(update);
		});
		field.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {	// otherwise nothing happens when enter pressed...
				Platform.runLater(update);
			}
		});
	}
	@Override
	public Integer fromString(String s) {
		if (s.isBlank()) return factory.getValue();
		if (s.matches("[A-Fa-f0-9]{0,8}")) {	// make sure it's valid hex first (should avoid exception(s) from parse)
			int i = Integer.parseUnsignedInt(s, 16);
			i = Math.min(i, factory.getMax());	// if too big, cap it at max
			i = Math.max(i, factory.getMin());	// if too small...
			return i;
		} else {
			return factory.getValue();	// not valid hex, return original value
		}
	}
	
	@Override
	public String toString(Integer i) {
		return Integer.toHexString(i);
	}
}
