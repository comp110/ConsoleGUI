package comp110.controls;

import java.util.concurrent.CountDownLatch;

import comp110.controls.parsers.ParseFunction;

public class ParsedFutureValue<T> {

  private CountDownLatch _cdl;

  private ParseFunction<T> _parser;

  private T _value;

  private boolean _exiting;

  public ParsedFutureValue(ParseFunction<T> parser) {
    _cdl = new CountDownLatch(1);
    _parser = parser;
    _exiting = false;
  }

  public boolean test(String input) {
    try {
      _parser.parse(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean set(String input) {
    try {
      _value = _parser.parse(input);
      _cdl.countDown();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public T get() {
    try {
      _cdl.await();
      if (_exiting) {
        System.exit(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return _value;
  }

  public void exit() {
    _exiting = true;
    _cdl.countDown();
  }

}
