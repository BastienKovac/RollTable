package com.kovac.rolltable.impl;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.impl.results.RollTableResult;
import com.kovac.rolltable.utils.dices.Rollable;
import com.kovac.rolltable.utils.range.RangeMap;

public class IncrementalSimpleRollTable<E> extends SimpleRollTable<E> {

	private final RangeMap<Integer> incrementsMap;

	private int currentIncrement = 0;


	public IncrementalSimpleRollTable(String name, Rollable rollable, RangeMap<RollTableResult<E>> resultsMap, RangeMap<Integer> incrementsMap) throws RollTableInvalidException {
		super(name, rollable, resultsMap);
		this.incrementsMap = incrementsMap;
		this.incrementsMap.setDefaultValue(0);
	}

	@Override
	public E rollOnTable() {
		return getResultForRoll(getIncrementedRoll(getRollable().roll()));
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + incrementsMap;
	}

	private int getIncrementedRoll(int originalRoll) {
		int increment = incrementsMap.getAssociatedResult(originalRoll);
		originalRoll += currentIncrement;
		if (originalRoll > getMaxRoll()) {
			originalRoll = getMaxRoll();
		} else if (originalRoll < getMinRoll()) {
			originalRoll = getMinRoll();
			currentIncrement = 0;
		} else {
			currentIncrement += increment;
		}
		return originalRoll;
	}

}
