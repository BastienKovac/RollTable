package com.kovac.rolltable.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.interfaces.IncrementalRollTable;

public class IncrementalSimpleRollTable<E> extends SimpleRollTable<E> implements IncrementalRollTable<E> {

	private int currentIncrement = 0;
	private Map<Integer, Integer> incrementsMap;


	public IncrementalSimpleRollTable(String name) throws RollTableInvalidException {
		this(name, DEFAULT_DICE_NUMBER, DEFAULT_DICE_SIZE);
	}

	public IncrementalSimpleRollTable(String name, int diceSize) throws RollTableInvalidException {
		this(name, DEFAULT_DICE_NUMBER, diceSize);
	}

	public IncrementalSimpleRollTable(String name, int nbDices, int diceSize) throws RollTableInvalidException {
		super(name, nbDices, diceSize);
		this.incrementsMap = new HashMap<>();
		for (int i : getPossibleRolls()) {
			this.incrementsMap.put(i, 0);
		}
	}

	@Override
	public void addIncrement(int minRoll, int maxRoll, int increment) throws RollTableInvalidException {
		int minFixed = minRoll < maxRoll ? minRoll : maxRoll;
		int maxFixed = maxRoll > minRoll ? maxRoll : minRoll;
		checkBounds(minFixed, maxFixed);
		for (int i = minFixed ; i <= maxFixed ; i++) {
			this.incrementsMap.put(i, increment);
		}
	}

	@Override
	protected int getRollTableDice() {
		int diceRoll = super.getRollTableDice();
		int increment = incrementsMap.get(diceRoll);
		diceRoll += currentIncrement;
		if (diceRoll > getMaxDiceRoll()) {
			diceRoll = getMaxDiceRoll();
			currentIncrement = 0;
		} else {
			currentIncrement += increment;
		}
		return diceRoll;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getName()).append("\n\n");
		for (Entry<Integer, Callable<E>> entry : getRollResultsMap().entrySet()) {
			builder.append(String.format("%4s", entry.getKey())).append(" | ").append(entry.getValue());
			builder.append(" | ").append("Increment : ").append(incrementsMap.get(entry.getKey())).append("\n");
		}
		return builder.toString();
	}

}
