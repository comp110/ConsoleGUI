package comp110.test;

import comp110.IConsole;
import comp110.test.methods.MethodCall;
import comp110.test.methods.Print;
import comp110.test.methods.Prompt;
import comp110.test.values.DoubleValue;

public class TestConsole implements IConsole {

	private Session _expected;
	private Session _actual;

	public TestConsole() {
		_actual = new Session();
	}

	public void setExpected(Session expected) {
		_expected = expected;
	}

	public Session getSession() {
		return _actual;
	}

	public void setTitle(String title) {
	}

	public void speed(double speed) {
	}
	
	public void endOfTest() {
	  if(_expected.hasNext()) {
	    throw new AssertionError("Output Missing\nExpected: " + _expected.next().toString());
	  }
	}

	public void alert(String message) {
	}

	public boolean confirm(String message) {
		return false;
	}

	public void print(String s) {
		Print<String> actual = new Print<String>(s);
		_actual.log(actual);
		_expected.test(actual);
	}

	public void print() {
	}

	public void print(int i) {
		System.out.println(i);
	}

	public void print(double d) {
	}

	@Override
	public void print(char c) {
	}

	@Override
	public void print(boolean b) {
	}

	@Override
	public void print(Object o) {
	}

	@Override
	public char promptChar() {
		return 0;
	}

	@Override
	public char promptChar(String prompt) {
		return 0;
	}

	@Override
	public boolean promptBoolean() {
		return false;
	}

	@Override
	public boolean promptBoolean(String prompt) {
		return false;
	}

	@Override
	public int promptInt() {
		return 0;
	}

	@Override
	public int promptInt(String prompt) {
		return 0;
	}

	@Override
	public double promptDouble() {
		return 0;
	}

	@Override
	public double promptDouble(String prompt) {
		Prompt actual = new Prompt(prompt);
		_actual.log(actual);
		Prompt expected = (Prompt) _expected.test(actual);
		DoubleValue expectedValue = (DoubleValue) expected.getValue();
		actual.setResponse(expectedValue);
		return expectedValue.get();
	}

	@Override
	public String promptString() {
		return null;
	}

	@Override
	public String promptString(String prompt) {
		return null;
	}

}
