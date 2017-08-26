package com.kovac.rolltable.utils.dices;

public class ModifiedDice extends Dice {

	private int modifier;

	public ModifiedDice(int diceSize, int modifier) {
		super(diceSize);
		this.modifier = modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	@Override
	public int roll() {
		return super.roll() + modifier;
	}

	@Override
	public int getMinRoll() {
		return super.getMinRoll() + modifier;
	}

	@Override
	public int getMaxRoll() {
		return super.getMaxRoll() + modifier;
	}

}
