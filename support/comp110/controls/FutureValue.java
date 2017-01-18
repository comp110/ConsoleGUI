package comp110.controls;

import java.util.concurrent.CountDownLatch;

public class FutureValue<T> {

  private CountDownLatch _cdl;
  private T _value;
  
  public FutureValue() {
    _cdl = new CountDownLatch(1);
  }
  
  public void set(T value) {
    _value = value;
    _cdl.countDown();
  }
  
  public T get() {
    try {
      _cdl.await();
    } catch(Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return _value;
  }

}
