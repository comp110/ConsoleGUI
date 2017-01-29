package comp110.test;

import comp110.test.methods.Alert;
import comp110.test.methods.EndOfProgram;
import comp110.test.methods.Print;
import comp110.test.methods.Prompt;
import comp110.test.values.DoubleValue;
import comp110.test.values.IntValue;
import comp110.test.values.StringTester;
import comp110.test.values.StringValue;

public class RecordedConsole {

	private Session _expected;

	public RecordedConsole() {
		_expected = new Session();
	}

	public Session getSession() {
		return _expected;
	}

	public void setTitle(String title) {

	}

	public void speed(double speed) {

	}

	public void alert(String s) {
		_expected.log(new Alert(s));
	}

	public void print(String s) {
		_expected.log(new Print<String>(s));
	}

	public void print(StringTester s) {
		_expected.log(new Print<StringTester>(s));
	}

	public void print(int i) {
		_expected.log(new Print<Integer>(i));
	}

	public void print(double d) {
		_expected.log(new Print<Double>(d));
	}

	public void print(char c) {
		_expected.log(new Print<Character>(c));
	}

	public void print(boolean b) {
		_expected.log(new Print<Boolean>(b));
	}

	public void print(Object o) {
		_expected.log(new Print<Object>(o));
	}

	public char promptChar() {
		// TODO Auto-generated method stub
		return 0;
	}

	public char promptChar(String prompt) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean promptBoolean() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean promptBoolean(String prompt) {
		// TODO Auto-generated method stub
		return false;
	}

	public void promptInt(int i) {
		_expected.log(new Prompt(new IntValue(i)));
	}

	public void promptInt(String string, int i) {
		_expected.log(new Prompt(string, new IntValue(i)));
	}

	public void promptDouble(double input) {
		_expected.log(new Prompt(new DoubleValue(input)));
	}

	public void promptDouble(String prompt, double input) {
		_expected.log(new Prompt(prompt, new DoubleValue(input)));
	}

	public String promptString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String promptString(String prompt) {
		// TODO Auto-generated method stub
		return null;
	}

	public void promptString(String prompt, String input) {
		_expected.log(new Prompt(prompt, new StringValue(input)));
	}

	public void endOfProgram() {
		_expected.log(new EndOfProgram());
	}

}
