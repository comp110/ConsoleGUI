package comp110.test;

import comp110.test.methods.MethodCall;

public class OutOfCallsException extends RuntimeException {

	private MethodCall _method;

	public OutOfCallsException() {
		_method = null;
	}

	public OutOfCallsException(MethodCall method) {
		_method = method;
	}

	public String getMessage() {
		return _method.toString();
	}

	public String toString() {
		return _method.toString();
	}

}