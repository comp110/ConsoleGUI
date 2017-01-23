package comp110;

public class Demo {

  public static void main(String[] args) {

    // Default Console (must be imported if not in comp110 package)
    Console.out.print("Hello, world");

    if (!Console.out.promptBoolean("Proceed? (true or false)")) {
      Console.out.print("goodbye.");
      return;
    }

    Console console = new Console("Another window");
    console.speed(0.1);
    console.print(console);
    console.print("Drag me over so you can see both consoles...");

    String name = Console.out.promptString("First name") + " " + Console.out.promptString("Last name");
    console.print("You entered: " + name);

    while (console.promptBoolean("Continue? (true/false)")) {
      double x = console.promptDouble();
      console.print(x * x);
    }

    console.print("goodbye.");
	  
    //Theme-related stuff...
    
    Console c = new Console("COMP110", Theme.GOOGLE_LIGHT); //Set theme with Constructor
  
    c.setTheme(Theme.COMP110); //Set theme using setTheme method (has effect on theme)
  
    char chario = c.promptChar();
  
    c.setTheme(Theme.UNC); //No effect on theme because FXConsole's run method has already been called
  
    c.print(chario);
	  
  }

}