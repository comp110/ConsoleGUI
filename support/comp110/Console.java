package comp110;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import comp110.controls.FutureValue;
import comp110.controls.Shell;

public class Console {

  private Shell _shell;

  private double _speed;

  public Console(String title) {
    _speed = 1.0;
    _shell = AppBase.instance().loadWindow(title, Shell.class, "Shell.fxml");
  }

  public Console() {
    this("CarolinaConsole");
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

//  public String askForString(String prompt) {
//    CountDownLatch cdl = new CountDownLatch(1);
//    run(() -> {
//      _shell.promptString(prompt, cdl);
//    });
//
//    try {
//      cdl.await();
//
//      return _shell.readString();
//
//    } catch (Exception e) {
//
//    }
//
//    return null;
//  }
//  
  public String askForString2(String prompt) {
    FutureValue<String> result = new FutureValue<>();
    run(() -> {
      _shell.promptString2(prompt, result);
    });
    return result.get();
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