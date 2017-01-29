package comp110.test.values;

public class IntValue extends Value {

	private int _value;

	public IntValue(int value) {
		_value = value;
	}

	public int get() {
		return _value;
	}

	public String toString() {
		return "" + _value;
	}

}