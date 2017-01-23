package comp110;

import comp110.controls.ParsedFutureValue;
import comp110.controls.Shell;
import comp110.controls.parsers.Parser;

/**
 * Console is a proxy to a backing implementation. We do this so that we can
 * instrument one console for user interactivity and another for grading.
 * 
 * @author Kris Jordan <kris@cs.unc.edu>
 */
public class Console implements IConsole {

	public static IConsole out;

	private IConsole _impl;

	static {
		out = ConsoleFactory.getConsole();
		out.setTheme(Theme.COMP110);
		out.setTitle("COMP110 Console");
	}

	public Console(String title) {
		_impl = ConsoleFactory.getConsole();
		_impl.setTheme(Theme.COMP110);
		_impl.setTitle(title);
	}
	
	public Console(String title, String[] theme) {
		_impl = ConsoleFactory.getConsole();
		_impl.setTheme(theme);
		_impl.setTitle(title);
	}

	public Console() {
		this("COMP110 Console");
	}

	public String toString() {
		return _impl.toString();
	}

	@Override
	public void setTitle(String title) {
		_impl.setTitle(title);
	}

	@Override
	public void speed(double speed) {
		_impl.speed(speed);
	}

	@Override
	public void print(String s) {
		_impl.print(s);
	}

	@Override
	public void print(int i) {
		_impl.print(i);
	}

	@Override
	public void print(double d) {
		_impl.print(d);
	}

	@Override
	public void print(char c) {
		_impl.print(c);
	}

	@Override
	public void print(boolean b) {
		_impl.print(b);
	}

	@Override
	public void print(Object o) {
		_impl.print(o);
	}
	
	public void setTheme(String[] theme) {
		_impl.setTheme(theme);
	}

	@Override
	public char promptChar() {
		return _impl.promptChar();
	}

	@Override
	public char promptChar(String prompt) {
		return _impl.promptChar(prompt);
	}

	@Override
	public boolean promptBoolean() {
		return _impl.promptBoolean();
	}

	@Override
	public boolean promptBoolean(String prompt) {
		return _impl.promptBoolean(prompt);
	}

	@Override
	public int promptInt() {
		return _impl.promptInt();
	}

	@Override
	public int promptInt(String prompt) {
		return _impl.promptInt(prompt);
	}

	@Override
	public double promptDouble() {
		return _impl.promptDouble();
	}

	@Override
	public double promptDouble(String prompt) {
		return _impl.promptDouble(prompt);
	}

	@Override
	public String promptString() {
		return _impl.promptString();
	}

	@Override
	public String promptString(String prompt) {
		return _impl.promptString(prompt);
	}

}