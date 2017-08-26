package com.kovac.rolltable.utils.dices;

import java.util.concurrent.ThreadLocalRandom;

public class MultiDice extends Dice {

	private int nbDices;


	public MultiDice(int diceSize) {
		this(diceSize, 0, 1);
	}

	public MultiDice(int diceSize, int modifier) {
		this(diceSize, modifier, 1);
	}

	public MultiDice(int diceSize, int modifier, int nbDices) {
		super(diceSize, modifier);
		this.nbDices = nbDices;
	}

	@Override
	public int roll() {
		int sum = 0;
		for (int i = 0 ; i < nbDices ; i++) {
			sum += ThreadLocalRandom.current().nextInt(1, getDiceSize());
		}
		return sum + getModifier();
	}

	@Override
	public int getMinRoll() {
		return nbDices + getModifier();
	}

	@Override
	public int getMaxRoll() {
		return nbDices * getDiceSize() + getModifier();
	}

	@Override
	public String toString() {
		return nbDices + super.toString();
	}

}
