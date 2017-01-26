package comp110;

public abstract class ConsoleFactory {

	private static Class<? extends IConsole> _klass;

	private static IConsole _last;

	private static boolean _singleton;

	static {
		_klass = FXConsole.class;
		_singleton = false;
	}

	public static IConsole getConsole() {
		try {
			if (_last == null || !_singleton) {
				_last = _klass.newInstance();
			}
			return _last;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void setConsoleClass(Class<? extends IConsole> klass) {
		_klass = klass;
	}

	public static void setSingleton(boolean singleton) {
		_singleton = singleton;
	}

	public static boolean getSingleton() {
		return _singleton;
	}

	public static IConsole getLast() {
		return _last;
	}

	public static void resetLast() {
		_last = null;
	}

}