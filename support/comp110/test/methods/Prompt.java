package comp110.test.methods;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;

import comp110.test.values.Value;

public class Prompt extends MethodCall {

	private String _prompt;

	private Value _response;

	public Prompt() {
		this("");
	}

	public Prompt(String prompt) {
		this(prompt, null);
	}

	public Prompt(Value response) {
		this("", response);
	}

	public Prompt(String prompt, Value response) {
		_prompt = prompt;
		_response = response;
	}

	public String getPrompt() {
		return _prompt;
	}

	public Value getValue() {
		return _response;
	}

	public void print() {
		System.out.println("(Prompt) " + _prompt + " " + _response);
	}

	public void test(MethodCall actual) {
		assertThat("Prompted user for input", actual, is(instanceOf(Prompt.class)));
		Prompt actualPrompt = (Prompt) actual;
		assertThat("Prompt matches expected string", actualPrompt._prompt, is(equalTo(_prompt)));
	}

	public void setResponse(Value response) {
		_response = response;
	}

}
