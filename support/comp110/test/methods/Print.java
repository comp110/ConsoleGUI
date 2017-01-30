package comp110.test.methods;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import comp110.test.values.StringTester;

public class Print<T> extends MethodCall {

  private T _value;

  public Print(T value) {
    _value = value;
  }

  public String toString() {
    return _value.toString();
  }

  public void print() {
    System.out.println(this);
  }

  public void test(MethodCall actual) {

    if (!(actual instanceof Print)) {
      throw new MethodCallMatchError(this, actual);
    }

    Print<T> actualPrint = (Print<T>) actual;
    if (_value instanceof StringTester) {
      StringTester tester = (StringTester) _value;
      if (tester.test(actualPrint._value.toString())) {
        return; // Short-circuit
      }
    }
    assertThat("Print output must match expected output. Check: spelling, punctuation, capitalization.",
        actualPrint._value.toString(), equalTo(_value.toString()));
  }

}