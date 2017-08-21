package com.kovac.rolltable.interfaces;

import com.kovac.rolltable.RollTableInvalidException;

public interface IncrementalRollTable<E> extends RollTable<E> {

	void addIncrement(int minRoll, int maxRoll, int increment) throws RollTableInvalidException;
	
}
