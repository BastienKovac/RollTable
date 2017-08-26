package com.kovac.rolltable.impl.results;

public class SimpleRollTableResult<E> implements RollTableResult<E> {

	private E result;

	public SimpleRollTableResult(E result) {
		this.result = result;
	}

	@Override
	public E getRollResult() {
		return result;
	}

	@Override
	public String toString() {
		return result.toString();
	}

}
