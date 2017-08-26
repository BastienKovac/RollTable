package com.kovac.rolltable.utils.dices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiceGroup implements Rollable {

	private List<Rollable> rollableList;
	private int modifier;

	public DiceGroup(Collection<Rollable> rollables) {
		this(rollables, 0);
	}

	public DiceGroup(int nbDices, int diceSize) {
		this(buildListDices(nbDices, diceSize), 0);
	}

	public DiceGroup(int nbDices, int diceSize, int modifier) {
		this(buildListDices(nbDices, diceSize), modifier);
	}

	public DiceGroup(Collection<Rollable> rollables, int modifier) {
		this.rollableList = new ArrayList<>(rollables);
		this.modifier = modifier;
	}

	public void addRollable(Rollable rollable) {
		this.rollableList.add(rollable);
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

	@Override
	public String toString() {
		return modifier == 0 ? rollableList.toString() : rollableList.toString() + " + " + modifier;
	}

	private static List<Rollable> buildListDices(int nbDices, int diceSize) {
		List<Rollable> listRollables = new ArrayList<>();
		for (int i = 0 ; i < nbDices ; i++) {
			listRollables.add(new Dice(diceSize));
		}
		return listRollables;
	}

}
