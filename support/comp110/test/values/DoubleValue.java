package comp110.test.values;

public class DoubleValue extends Value {

	private double _value;

	public DoubleValue(double value) {
		_value = value;
	}

	public double get() {
		return _value;
	}

	public String toString() {
		return "" + _value;
	}

}