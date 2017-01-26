package comp110;

public class InfiniteLoopDemo {

	public static void main(String[] args) {

		Console c1 = new Console();
		Console c2 = new Console();
		c1.speed(0.1);

		while (true) {
			c1.print(1);
			c2.print(2);
		}

	}

}