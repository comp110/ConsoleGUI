package comp110.toys;

public class Die {

	private int _value;

	private int _unfairOffset;

	private static int[] _unfair;

	public Die() {
		_value = 1;
		_unfairOffset = 0;
	}

	public void roll() {
		if (_unfair == null) {
			_value = (int) (Math.random() * 6.0) + 1;
		} else {
			int index = _unfairOffset % _unfair.length;
			_value = _unfair[index];
			_unfairOffset++;
		}
	}

	public int getValue() {
		return _value;
	}

	public static void unfair(int[] values) {
		_unfair = values;
	}

}