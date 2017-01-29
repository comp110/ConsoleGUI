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

	public boolean hasNext() {
		return _t < _calls.size();
	}

	public MethodCall next() {
		return _calls.get(_t++);
	}

	public int size() {
		return _calls.size();
	}

	public int getStep() {
		return _t;
	}

	public Session subSession(int end) {
		Session sub = new Session();
		for (int i = 0; i < end; i++) {
			sub.log(_calls.get(i));
		}
		return sub;
	}

	public MethodCall test(MethodCall actual) {
		if (hasNext()) {
			MethodCall expected = next();
			expected.test(actual);
			return expected;
		} else {
			throw new OutOfCallsException(actual);
		}
	}

	public void rewind() {
		_t = 0;
	}

	public void print() {
		System.out.println(this);
	}

	public String toString() {
		String result = "";
		for (MethodCall m : _calls) {
			result += m.toString() + "\n";
		}
		return result;
	}

}