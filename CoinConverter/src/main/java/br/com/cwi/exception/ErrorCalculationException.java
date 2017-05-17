package br.com.cwi.exception;

public class ErrorCalculationException extends Exception {

	private static final long serialVersionUID = 8816846491288542857L;

	public ErrorCalculationException() {
		super();
	}

	public ErrorCalculationException(String message) {
		super(message);
	}

	public ErrorCalculationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorCalculationException(Throwable cause) {
		super(cause);
	}
}
