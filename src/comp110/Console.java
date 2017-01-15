package comp110;

public class Console {

  private Shell _shell;

  private double _speed;

  public Console() {
    _speed = 1.0;
    _shell = AppBase.instance().loadWindow("Console", Shell.class, "Shell.fxml");
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

  private void run(VoidMethod m) {

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

}