package comp110.test.methods;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class EndOfProgram extends MethodCall {

	public EndOfProgram() {
	}

	public String toString() {
		return "END OF PROGRAM";
	}

	public void print() {
		System.out.println(this);
	}

	public void test(MethodCall actual) {
		assertThat("End of Program Reached", actual, instanceOf(EndOfProgram.class));
	}

}