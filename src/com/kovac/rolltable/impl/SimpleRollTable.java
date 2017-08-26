package com.kovac.rolltable.impl;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.impl.results.RollTableResult;
import com.kovac.rolltable.interfaces.RollTable;
import com.kovac.rolltable.utils.dices.Rollable;
import com.kovac.rolltable.utils.range.RangeMap;

public class SimpleRollTable<E> implements RollTable<E> {

	private final String name;
	private final int minRoll, maxRoll;
	private final Rollable rollable;
	private final RangeMap<RollTableResult<E>> resultsMap;


	public SimpleRollTable(String name, Rollable rollable, RangeMap<RollTableResult<E>> resultsMap) throws RollTableInvalidException {
		checkValidity(rollable, resultsMap);
		this.name = name;
		this.rollable = rollable;
		this.minRoll = rollable.getMinRoll();
		this.maxRoll = rollable.getMaxRoll();
		this.resultsMap = resultsMap;
	}

	@Override
	public E rollOnTable() {
		return getResultForRoll(rollable.roll());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "\n" + resultsMap.toString();
	}

	protected void checkValidity(Rollable rollable, RangeMap<?> resultsMap) throws RollTableInvalidException {
		if (rollable == null) {
			throw new RollTableInvalidException("The given Rollable is null");
		}
		checkRangeMapFull(resultsMap);
	}

	protected final void checkRangeMapFull(RangeMap<?> rangeMap) throws RollTableInvalidException {
		if (!rangeMap.isRangeMapFull(getMinRoll(), getMaxRoll())) {
			throw new RollTableInvalidException("The given range map isn't complete");
		}
	}

	protected final E getResultForRoll(int roll) {
		return resultsMap.getAssociatedResult(roll).getRollResult();
	}

	protected final Rollable getRollable() {
		return rollable;
	}

	protected final int getMinRoll() {
		return minRoll;
	}

	protected final int getMaxRoll() {
		return maxRoll;
	}

}
