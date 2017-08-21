package com.kovac.rolltable.utils;

import com.kovac.rolltable.RollTableInvalidException;
import com.kovac.rolltable.impl.IncrementalSimpleRollTable;
import com.kovac.rolltable.impl.SimpleRollTable;
import com.kovac.rolltable.interfaces.IncrementalRollTable;
import com.kovac.rolltable.interfaces.RollTable;

public enum RollTableFactory {

	FACTORY;

	public RollTable<String> getTestSimpleRollTable() throws RollTableInvalidException {
		RollTable<String> rollTable = new SimpleRollTable<>("Table 1");
		rollTable.addRollResult(1, 2, "Roll is either 1 or 2");
		rollTable.addRollResult(3, 9, "Roll is between 3 and 9");
		rollTable.linkOtherTable(10, 10, getTestIncrementalRollTable());
		return rollTable;
	}

	public RollTable<String> getTestIncrementalRollTable() throws RollTableInvalidException {
		IncrementalRollTable<String> rollTable = new IncrementalSimpleRollTable<>("Table 2", 2, 5);
		rollTable.addRollResult(2, 8, "Roll between 2 and 8");
		rollTable.addIncrement(10, 10, 5);
		rollTable.addRollResult(9, 10, "Roll is either 9 or 10");
		return rollTable;
	}

	public static void main(String[] args) {
		try {
			System.out.println(FACTORY.getTestSimpleRollTable());
			System.out.println(FACTORY.getTestIncrementalRollTable());
		} catch (RollTableInvalidException e) {
			e.printStackTrace();
		}
	}

}
