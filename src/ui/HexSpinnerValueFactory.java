package ui;

import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.IntegerStringConverter;

public class HexSpinnerValueFactory extends SpinnerValueFactory.IntegerSpinnerValueFactory {
	public class HexStringConverter extends IntegerStringConverter {
		@Override
		public Integer fromString(String s) {
			if (s.matches("[A-Fa-f0-9]+")) {	// make sure it's valid hex first (should avoid exception(s) from parse)
				int i = Integer.parseUnsignedInt(s, 16);
				i = Math.min(i, getMax());	// if too big, cap it at max
				i = Math.max(i, getMin());	// if too small...
				return i;
			} else {
				// TODO how to redraw the text when conversion fails? as is, the value is correct, but the text doesn't update until next
				// inc()/dec()...
				return getValue();	// not valid hex, return original value
			}
		}

		@Override
		public String toString(Integer i) {
			return Integer.toHexString(i);
		}
	}
	public HexSpinnerValueFactory(int min, int max) {
		super(min, max);
	}
	public HexSpinnerValueFactory(int min, int max, int initialValue) {
		super(min, max, initialValue);
	}
	public HexSpinnerValueFactory(int min, int max, int initialValue, int amountToStepBy) {
		super(min, max, initialValue, amountToStepBy);
	}

	{
		setConverter(new HexStringConverter());
	}
	@Override
	public void decrement(int arg0) {
		super.decrement(arg0);
	}

	@Override
	public void increment(int arg0) {
		super.increment(arg0);
	}
}
