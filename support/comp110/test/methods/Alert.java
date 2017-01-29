package comp110.test.methods;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import comp110.test.values.StringTester;

public class Alert<T> extends MethodCall {

	private T _value;

	public Alert(T value) {
		_value = value;
	}

	public String toString() {
		return "(Alert) " + _value.toString();
	}

	public void print() {
		System.out.println(this);
	}

	public void test(MethodCall actual) {
		assertThat("Alert", actual, instanceOf(Alert.class));
		Alert<T> actualAlert = (Alert<T>) actual;
		if (_value instanceof StringTester) {
			StringTester tester = (StringTester) _value;
			if (tester.test(actualAlert._value.toString())) {
				return; // Short-circuit
			}
		}
		assertThat("Alerted must match expected output. Check: spelling, punctuation, capitalization.",
		    actualAlert._value.toString(), equalTo(_value.toString()));
	}

}