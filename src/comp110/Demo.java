package comp110;

public class Demo {

	public static void main(String[] args) {

		// Default Console (must be imported if not in comp110 package)
		Console.out.print("Hello, world");

		Console.out.alert("This is an alert.");

		while (Console.out.confirm("Continue?")) {
			Console.out.print(":D");
		}

		Console.out.print("bye");
	}

}