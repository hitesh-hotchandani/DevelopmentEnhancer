package org.development.enhancer.analysis.exception.apis;

public class ExceptionAPIs {

	public static void searchException(Exception e) {
		String message;
		if (e.getCause() != null) {
			message = e.getCause().getMessage();
		} else {
			message = e.getMessage();
		}
		internalForward(message);
	}

	public static void searchException(String message) {
		internalForward(message);
	}

	private static void internalForward(String message) {
		ExceptionHandler.handleException(message);
	}

}
