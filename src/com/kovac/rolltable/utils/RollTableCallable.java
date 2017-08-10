package com.kovac.rolltable.utils;

import java.util.concurrent.Callable;

import com.kovac.rolltable.interfaces.RollTable;

public class RollTableCallable<E> implements Callable<E> {

	private RollTable<E> linkedRollTable;
	private E rollResult;

	public RollTableCallable(E result) {
		this.rollResult = result;
	}

	public RollTableCallable(RollTable<E> linkedRollTable) {
		this.linkedRollTable = linkedRollTable;
	}

	@Override
	public E call() throws Exception {
		return linkedRollTable != null ? linkedRollTable.rollOnTable() : rollResult;
	}

	@Override
	public String toString() {
		return linkedRollTable != null ? "Roll on " + linkedRollTable.getName() : rollResult.toString();
	}

}
