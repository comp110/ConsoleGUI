package comp110.test;

import java.util.ArrayList;
import java.util.List;

import comp110.test.methods.MethodCall;
import comp110.test.values.Value;

public class Session {

	private List<MethodCall> _calls;

	private int _t;

	private Value _lastValue;

	public Session() {
		_calls = new ArrayList<MethodCall>();
		_t = 0;
	}

	public void log(MethodCall m) {
		_calls.add(m);
	}

	public MethodCall test(MethodCall actual) {
		if (_t < _calls.size()) {
			MethodCall expected = _calls.get(_t++);
			expected.test(actual);
			return expected;
		} else {
			throw new OutOfCallsException();
		}
	}

	public void rewind() {
		_t = 0;
	}

	public void print() {
		for (MethodCall m : _calls) {
			m.print();
		}
	}

}