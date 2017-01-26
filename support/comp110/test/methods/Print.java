package comp110.test.methods;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Print<T> extends MethodCall {

	private T _value;

	public Print(T value) {
		_value = value;
	}

	public void print() {
		System.out.println(_value);
	}

	public void test(MethodCall actual) {
		assertThat("Printed output", actual, is(instanceOf(Print.class)));
		Print<T> actualPrint = (Print<T>) actual;
		assertThat("Print matches expected string", actualPrint._value, is(equalTo(_value)));
	}

}