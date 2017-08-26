package com.kovac.rolltable.utils.builder;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.interfaces.RollTable;
import com.kovac.rolltable.utils.dices.Rollable;
import com.kovac.rolltable.utils.range.RangeOverlapException;

public interface RollTableBuilder<E> {

	RollTable<E> build() throws RollTableInvalidException;

	RollTableBuilder<E> withName(String name);

	RollTableBuilder<E> withRollable(Rollable rollable);

	RollTableBuilder<E> withRollable(String rollableToParse) throws IllegalArgumentException;

	RollTableBuilder<E> withResult(int lower, int upper, E result) throws RangeOverlapException;

	RollTableBuilder<E> withLinkedTable(int lower, int upper, RollTable<E> linkedTable) throws RangeOverlapException;

	RollTableBuilder<E> withIncrement(int lower, int upper, int increment) throws RangeOverlapException;

}
