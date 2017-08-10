package com.kovac.rolltable.impl;

import java.util.concurrent.Callable;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.interfaces.RollTable;
import com.kovac.rolltable.utils.DiceUtils;
import com.kovac.rolltable.utils.RollTableCallable;

public class SimpleRollTable<E> extends AbstractRollTable<E> {

	public SimpleRollTable(String name) throws RollTableInvalidException  {
		super(name);
	}

	public SimpleRollTable(String name, int diceSize) throws RollTableInvalidException  {
		super(name, diceSize);
	}

	public SimpleRollTable(String name, int nbDices, int diceSize) throws RollTableInvalidException {
		super(name, nbDices, diceSize);
	}

	@Override
	protected int getRollTableDice() {
		int sum = 0;
		for (int i = 0 ; i < getNbDices() ; i++) {
			sum += DiceUtils.roll(getDiceSize());
		}
		return sum;
	}

	@Override
	protected int[] getPossibleRolls() {
		int[] rolls = new int[getNbDices() * getDiceSize() - (getNbDices() - 1)];
		for (int i = 0 ; i < rolls.length ; i++) {
			rolls[i] = i + getNbDices();
		}
		return rolls;
	}

	@Override
	protected Callable<E> getSimpleRollResultCallable(E rollResult) {
		return new RollTableCallable<>(rollResult);
	}

	@Override
	protected Callable<E> getLinkedRollResultCallable(RollTable<E> linkedRollTable) {
		return new RollTableCallable<>(linkedRollTable);
	}

}
