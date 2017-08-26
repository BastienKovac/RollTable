package com.kovac.rolltable.utils.range;

@SuppressWarnings("serial")
public class RangeOverlapException extends Exception {

	public RangeOverlapException() {
		super();
	}

	public RangeOverlapException(String message) {
		super(message);
	}

	public RangeOverlapException(Throwable cause) {
		super(cause);
	}

	public RangeOverlapException(String message, Throwable cause) {
		super(message, cause);
	}

}
