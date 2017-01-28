package comp110.test.values;

import java.util.function.Predicate;

public class StringTester extends Value {

  private Predicate<String> _test;
  private String _example;
  
  public StringTester(Predicate<String> test, String example) {
    _test = test;
    _example = example;
  }

  public boolean test(String actual) {
    return _test.test(actual);
  }
  
  public String toString() {
    return _example;
  }
  
}