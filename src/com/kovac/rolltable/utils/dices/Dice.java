package com.kovac.rolltable.utils.dices;

import java.util.concurrent.ThreadLocalRandom;

public class Dice implements Rollable {

	private int diceSize;

	public Dice(int diceSize) {
		this.diceSize = diceSize;
	}

	@Override
	public int roll() {
		return ThreadLocalRandom.current().nextInt(1, diceSize + 1);
	}

	@Override
	public int getMinRoll() {
		return 1;
	}

	@Override
	public int getMaxRoll() {
		return diceSize;
	}

}
