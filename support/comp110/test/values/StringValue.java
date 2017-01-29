package comp110.test.values;

public class StringValue extends Value {

	private String _value;

	public StringValue(String value) {
		_value = value;
	}

	public String get() {
		return _value;
	}

	public String toString() {
		return "" + _value;
	}

}