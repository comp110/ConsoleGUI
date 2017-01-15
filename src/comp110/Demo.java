package comp110;

public class Demo {

  public static void main(String[] args) {

    Console console = new Console();

    console.print("Hello, world");
    console.speed(1.0);
    console.print('c');

    console.speed(0.5);
    for (int i = 0; i < 4; i++) {
      console.print(i);
    }

    Console c2 = new Console();
    for (int i = 0; i < 100; i++) {
      c2.print("i:" + i);
    }

  }

}
