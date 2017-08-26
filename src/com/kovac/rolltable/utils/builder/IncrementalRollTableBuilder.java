package com.kovac.rolltable.utils.builder;

import com.kovac.rolltable.utils.range.Range;
import com.kovac.rolltable.utils.range.RangeMap;
import com.kovac.rolltable.utils.range.RangeOverlapException;

public class IncrementalRollTableBuilder<E> extends SimpleRollTableBuilder<E> {

	private RangeMap<Integer> incrementMap;

	public IncrementalRollTableBuilder() {
		this.incrementMap = new RangeMap<>();
	}

	@Override
	public IncrementalRollTableBuilder<E> withIncrement(int lower, int upper, int increment) throws RangeOverlapException {
		incrementMap.addRangeValueCombo(new Range(lower, upper), upper);
		return this;
	}

}
