package comp110;

public class TitleCloseDemo {

	public static void main(String[] args) {

		Console console = new Console();
		console.print("Hello");
		console.setTitle("Test");
		console.alert("Awaiting instruction");
		console.close();

	}

}
