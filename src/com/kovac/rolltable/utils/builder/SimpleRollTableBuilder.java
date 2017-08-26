package com.kovac.rolltable.utils.builder;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.impl.SimpleRollTable;
import com.kovac.rolltable.impl.results.LinkRollTableResult;
import com.kovac.rolltable.impl.results.RollTableResult;
import com.kovac.rolltable.impl.results.SimpleRollTableResult;
import com.kovac.rolltable.interfaces.RollTable;
import com.kovac.rolltable.utils.dices.Rollable;
import com.kovac.rolltable.utils.range.Range;
import com.kovac.rolltable.utils.range.RangeMap;
import com.kovac.rolltable.utils.range.RangeOverlapException;

public class SimpleRollTableBuilder<E> implements RollTableBuilder<E> {

	private Rollable rollable;
	private String name;

	private RangeMap<RollTableResult<E>> resultsMap;


	public SimpleRollTableBuilder() {
		this.resultsMap = new RangeMap<>();
	}

	@Override
	public RollTable<E> build() throws RollTableInvalidException {
		return new SimpleRollTable<>(name, rollable, resultsMap);
	}

	@Override
	public RollTableBuilder<E> withName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public RollTableBuilder<E> withRollable(Rollable rollable) {
		this.rollable = rollable;
		return this;
	}

	@Override
	public RollTableBuilder<E> withResult(int lower, int upper, E result) throws RangeOverlapException {
		this.resultsMap.addRangeValueCombo(new Range(lower, upper), new SimpleRollTableResult<>(result));
		return this;
	}

	@Override
	public RollTableBuilder<E> withLinkedTable(int lower, int upper, RollTable<E> linkedTable)
			throws RangeOverlapException {
		this.resultsMap.addRangeValueCombo(new Range(lower, upper), new LinkRollTableResult<>(linkedTable));
		return this;
	}

	@Override
	public RollTableBuilder<E> withIncrement(int lower, int upper, int increment) throws RangeOverlapException {
		throw new UnsupportedOperationException("This builder isn't fit to add increments");
	}

}
