package com.kovac.rolltable.impl.results;

import com.kovac.rolltable.interfaces.RollTable;

public class LinkRollTableResult<E> implements RollTableResult<E> {

	private RollTable<E> linkedRollTable;

	public LinkRollTableResult(RollTable<E> linkedRollTable) {
		this.linkedRollTable = linkedRollTable;
	}

	@Override
	public E getRollResult() {
		return linkedRollTable.rollOnTable();
	}

	@Override
	public String toString() {
		return "Roll on " + linkedRollTable.getName();
	}

}
