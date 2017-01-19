package comp110;

import comp110.controls.ParsedFutureValue;
import comp110.controls.Shell;
import comp110.controls.parsers.Parser;

public class Console {

  public static Console out;

  private String _title;
  private boolean _showing;
  private Shell _shell;
  private double _speed;

  static {
    out = new Console("COMP110 Console");
  }

  public Console(String title) {
    _title = title;
    _showing = false;
    _speed = 1.0;
  }

  public Console() {
    this("COMP110 Console");
  }

  public void speed(double speed) {
    _speed = speed;
  }

  public void print(String s) {
    run(() -> {
      _shell.print(s);
    });
  }

  public void print(int i) {
    run(() -> {
      _shell.print(i);
    });
  }

  public void print(double d) {
    run(() -> {
      _shell.print(d);
    });
  }

  public void print(char c) {
    run(() -> {
      _shell.print(c);
    });
  }

  public void print(boolean b) {
    run(() -> {
      _shell.print(b);
    });
  }

  public void print(Object o) {
    run(() -> {
      if (o == this) {
        _shell.print(_shell);
      } else {
        _shell.print(o);
      }
    });
  }

  public char promptChar() {
    return promptChar("Please enter a char value:");
  }

  public char promptChar(String prompt) {
    ParsedFutureValue<Character> result = new ParsedFutureValue<>(Parser.forChars());
    run(() -> {
      _shell.promptChar(prompt, result);
    });
    return result.get().charValue();
  }

  public boolean promptBoolean() {
    return promptBoolean("Please enter a boolean value:");
  }

  public boolean promptBoolean(String prompt) {
    ParsedFutureValue<Boolean> result = new ParsedFutureValue<>(Parser.forBooleans());
    run(() -> {
      _shell.promptBoolean(prompt, result);
    });
    return result.get().booleanValue();
  }

  public int promptInt() {
    return promptInt("Please enter an int value:");
  }

  public int promptInt(String prompt) {
    ParsedFutureValue<Integer> result = new ParsedFutureValue<>(Parser.forInts());
    run(() -> {
      _shell.promptInt(prompt, result);
    });
    return result.get().intValue();
  }

  public double promptDouble() {
    return promptDouble("Please enter a double value:");
  }

  public double promptDouble(String prompt) {
    ParsedFutureValue<Double> result = new ParsedFutureValue<>(Parser.forDoubles());
    run(() -> {
      _shell.promptDouble(prompt, result);
    });
    return result.get().doubleValue();
  }

  public String promptString() {
    return promptString("Please enter a String value:");
  }

  public String promptString(String prompt) {
    ParsedFutureValue<String> result = new ParsedFutureValue<>(Parser.forStrings());
    run(() -> {
      _shell.promptString(prompt, result);
    });
    return result.get();
  }

  private void run(VoidMethod m) {
    if (!_showing) {
      _shell = AppBase.instance().loadWindow(_title, Shell.class, "Shell.fxml");
      _showing = true;
    }

    AppBase.instance().runFX(() -> {
      m.run();
      return null;
    });

    if (_speed <= 1.0 && _speed > 0.0) {
      try {
        Thread.sleep((long) (1000L - (1000.0 * _speed)));
      } catch (InterruptedException e) {
      }
    }
  }

  public String toString() {
    return "Console (\"" + _title + "\")";
  }

}