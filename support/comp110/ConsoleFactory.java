package comp110;

public abstract class ConsoleFactory {

	private static Class<? extends IConsole> _klass;

	static {
		_klass = FXConsole.class;
	}

	public static IConsole getConsole() {
		try {
			return _klass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setConsoleClass(Class<? extends IConsole> klass) {
		_klass = klass;
	}

}