package com.kovac.rolltable.utils.dices;

import java.util.concurrent.ThreadLocalRandom;

public class Dice implements Rollable {

	private int diceSize;
	private int modifier;


	public Dice(int diceSize) {
		this(diceSize, 0);
	}

	public Dice(int diceSize, int modifier) {
		this.diceSize = diceSize;
		this.modifier = modifier;
	}

	@Override
	public int roll() {
		return ThreadLocalRandom.current().nextInt(1, diceSize + 1) + modifier;
	}

	@Override
	public int getMinRoll() {
		return 1 + modifier;
	}

	@Override
	public int getMaxRoll() {
		return diceSize + modifier;
	}

	@Override
	public String toString() {
		return modifier == 0 ? "d" + diceSize : "d" + diceSize + " + " + modifier;
	}

	protected final int getDiceSize() {
		return diceSize;
	}

	protected final int getModifier() {
		return modifier;
	}

}
