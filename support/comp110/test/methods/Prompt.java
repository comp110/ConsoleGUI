package comp110.test.methods;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.containsString;

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

	public String toString() {
	  if (_response != null) {
		  return "(Prompt) " + _prompt + " " + _response;
	  } else {
	    return "(Prompt) " + _prompt;
	  }
	}
	
  public void print() {
    System.out.println(this);
  }

	public void test(MethodCall actual) {
		assertThat("Prompted user for input", actual, instanceOf(Prompt.class));
		Prompt actualPrompt = (Prompt) actual;
		assertThat("Prompt text must contain the expected String", actualPrompt._prompt.toLowerCase(), containsString(_prompt.toLowerCase()));
	}

	public void setResponse(Value response) {
		_response = response;
	}

}