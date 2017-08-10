package com.kovac.rolltable.utils;

import java.util.concurrent.ThreadLocalRandom;

public class DiceUtils {

	private DiceUtils() {

	}

	public static int roll(int diceSize) {
		return ThreadLocalRandom.current().nextInt(1, diceSize + 1);
	}

}
