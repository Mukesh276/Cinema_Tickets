package uk.gov.dwp.uc.pairtest.exception;

import java.util.List;

public class InvalidPurchaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPurchaseException(String msg) {
		super(msg);
	}

	public InvalidPurchaseException(List<String> validationMessages) {
		super(String.join("\n", validationMessages));
	}
}
