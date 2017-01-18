package comp110.controls;

import java.util.concurrent.CountDownLatch;

public class FutureValue {

  private CountDownLatch _cdl;

  public FutureValue() {
    _cdl = new CountDownLatch(1);
  }

}
