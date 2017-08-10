package com.kovac.rolltable.impl;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.interfaces.RollTable;
import com.kovac.rolltable.utils.RollTableCallable;

public abstract class AbstractRollTable<E> implements RollTable<E> {

	private static final int DEFAULT_DICE_NUMBER = 1;
	private static final int DEFAULT_DICE_SIZE = 10;

	private final String name;
	private final int diceSize;
	private final int nbDices;

	private SortedMap<Integer, Callable<E>> rollResultsMap;


	public AbstractRollTable(String name) throws RollTableInvalidException  {
		this(name, DEFAULT_DICE_NUMBER, DEFAULT_DICE_SIZE);
	}

	public AbstractRollTable(String name, int diceSize) throws RollTableInvalidException {
		this(name, DEFAULT_DICE_NUMBER, diceSize);
	}

	public AbstractRollTable(String name, int nbDices, int diceSize) throws RollTableInvalidException {
		if (nbDices <= 0 || diceSize < 1) {
			throw new RollTableInvalidException();
		}
		this.nbDices = nbDices;
		this.diceSize = diceSize;
		this.name = name;
		this.rollResultsMap = new TreeMap<>();
		for (int i : getPossibleRolls()) {
			this.rollResultsMap.put(i, null);
		}
	}

	@Override
	public void addRollResult(int minRoll, int maxRoll, E rollResult) throws RollTableInvalidException {
		int minFixed = minRoll < maxRoll ? minRoll : maxRoll;
		int maxFixed = maxRoll > minRoll ? maxRoll : minRoll;
		checkBounds(minFixed, maxFixed);
		for (int i = minFixed ; i <= maxFixed ; i++) {
			this.rollResultsMap.put(i, new RollTableCallable<>(rollResult));
		}
	}

	@Override
	public void linkOtherTable(int minRoll, int maxRoll, RollTable<E> otherTable) throws RollTableInvalidException {
		int minFixed = minRoll < maxRoll ? minRoll : maxRoll;
		int maxFixed = maxRoll > minRoll ? maxRoll : minRoll;
		checkBounds(minFixed, maxFixed);
		for (int i = minFixed ; i <= maxFixed ; i++) {
			this.rollResultsMap.put(i, new RollTableCallable<>(otherTable));
		}
	}

	private void checkBounds(int minFixed, int maxFixed) throws RollTableInvalidException {
		if (minFixed < rollResultsMap.firstKey() || maxFixed > rollResultsMap.lastKey()) {
			throw new RollTableInvalidException();
		}
	}

	@Override
	public E rollOnTable() throws RollTableInvalidException {
		if (!isRollTableValid()) {
			throw new RollTableInvalidException();
		}
		try {
			return rollResultsMap.get(getRollTableDice()).call();
		} catch (Exception e) {
			throw new RollTableInvalidException(e);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getName()).append("\n\n");
		for (Entry<Integer, Callable<E>> entry : rollResultsMap.entrySet()) {
			builder.append(String.format("%4s", entry.getKey())).append(" | ").append(entry.getValue()).append('\n');
		}
		return builder.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	protected int getDiceSize() {
		return diceSize;
	}

	protected int getNbDices() {
		return nbDices;
	}

	private boolean isRollTableValid() {
		for (Callable<E> rollResult : rollResultsMap.values()) {
			if (rollResult == null) {
				return false;
			}
		}
		return true;
	}

	protected abstract int[] getPossibleRolls();

	protected abstract int getRollTableDice();

}
