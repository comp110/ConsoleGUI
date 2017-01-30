package comp110.test.methods;

public class MethodCallMatchError extends AssertionError {

  public MethodCallMatchError(MethodCall expected, MethodCall actual) {
    super("Expected: " + expected.toString() + "\n" + "Actual: " + actual.toString());
  }

}