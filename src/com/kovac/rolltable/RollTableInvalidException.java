package com.kovac.rolltable;

@SuppressWarnings("serial")
public class RollTableInvalidException extends Exception {

	public RollTableInvalidException() {
		super();
	}

	public RollTableInvalidException(String message) {
		super(message);
	}

	public RollTableInvalidException(Throwable cause) {
		super(cause);
	}

	public RollTableInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

}
