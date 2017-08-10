package com.kovac.rolltable;

@SuppressWarnings("serial")
public class RollTableInvalidException extends Exception {

	public RollTableInvalidException() {
		super();
	}

	public RollTableInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RollTableInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public RollTableInvalidException(String message) {
		super(message);
	}

	public RollTableInvalidException(Throwable cause) {
		super(cause);
	}

}
