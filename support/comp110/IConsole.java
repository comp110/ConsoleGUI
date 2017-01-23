package comp110;

public interface IConsole {

	void setTitle(String title);

	void speed(double speed);

	void print(String s);

	void print(int i);

	void print(double d);

	void print(char c);

	void print(boolean b);

	void print(Object o);
	
	void setTheme(String[] theme);

	char promptChar();

	char promptChar(String prompt);

	boolean promptBoolean();

	boolean promptBoolean(String prompt);

	int promptInt();

	int promptInt(String prompt);

	double promptDouble();

	double promptDouble(String prompt);

	String promptString();

	String promptString(String prompt);

}
