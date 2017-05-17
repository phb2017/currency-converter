package br.com.cwi.exception;

public class InvalidParameterException extends Exception {

	private static final long serialVersionUID = -5934202033449977198L;

	public InvalidParameterException() {
		super();
	}

	public InvalidParameterException(String message) {
		super(message);
	}

	public InvalidParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidParameterException(Throwable cause) {
		super(cause);
	}
}
