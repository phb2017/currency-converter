package br.com.cwi.exception;

public class QuotationException extends Exception {

	private static final long serialVersionUID = 8338866966421738688L;

	public QuotationException() {
		super();
	}

	public QuotationException(String message) {
		super(message);
	}

	public QuotationException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuotationException(Throwable cause) {
		super(cause);
	}
}
