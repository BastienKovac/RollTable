package com.kovac.rolltable.interfaces;

import com.kovac.rolltable.RollTableInvalidException;

public interface RollTable<E> {

	String getName();

	void addRollResult(int minRoll, int maxRoll, E rollResult) throws RollTableInvalidException;

	void linkOtherTable(int minRoll, int maxRoll, RollTable<E> otherTable) throws RollTableInvalidException;

	E rollOnTable() throws RollTableInvalidException;

}
