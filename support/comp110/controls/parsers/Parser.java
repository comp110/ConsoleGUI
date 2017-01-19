package comp110.controls.parsers;

public class Parser {

  public static ParseFunction<Integer> forInts() {
    return Integer::parseInt;
  }

  public static ParseFunction<Double> forDoubles() {
    return Double::parseDouble;
  }

  public static ParseFunction<Character> forChars() {
    return (s) -> {
      if (s.length() == 1) {
        return s.charAt(0);
      } else {
        throw new RuntimeException("chars must be exactly one character");
      }
    };
  }

  public static ParseFunction<Boolean> forBooleans() {
    return (s) -> {
      switch (s.toLowerCase()) {
      case "t":
      case "true":
        return true;
      case "f":
      case "false":
        return false;
      default:
        throw new RuntimeException("Booleans must be t/true or f/false");
      }
    };
  }

  public static ParseFunction<String> forStrings() {
    return (s) -> s;
  }

}