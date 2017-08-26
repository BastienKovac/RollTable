package com.kovac.rolltable.utils.dices;

import java.util.Collection;
import java.util.List;

public class DiceGroup implements Rollable {

	private List<Rollable> rollableList;
	private int modifier;

	public DiceGroup(Collection<Rollable> rollables) {
		this(rollables, 0);
	}

	public DiceGroup(Collection<Rollable> rollables, int modifier) {
		this.rollableList.addAll(rollables);
		this.modifier = modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	@Override
	public int roll() {
		int sum = 0;
		for (Rollable rollable : rollableList) {
			sum += rollable.roll();
		}
		return sum + modifier;
	}

	@Override
	public int getMinRoll() {
		int sum = 0;
		for (Rollable rollable : rollableList) {
			sum += rollable.getMinRoll();
		}
		return sum + modifier;
	}

	@Override
	public int getMaxRoll() {
		int sum = 0;
		for (Rollable rollable : rollableList) {
			sum += rollable.getMaxRoll();
		}
		return sum + modifier;
	}

}
