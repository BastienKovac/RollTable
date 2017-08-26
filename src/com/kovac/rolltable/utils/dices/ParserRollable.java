package com.kovac.rolltable.utils.dices;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParserRollable {

	private static final Pattern PATTERN_DICE = Pattern.compile("(\\d+)d(\\d+)(\\+(\\d+))*");

	private ParserRollable() {
	}

	public static Rollable parseRollable(String stringToParse) throws IllegalArgumentException {
		String trimmedString = stringToParse.trim().replaceAll(" ", "");
		Matcher matcher = PATTERN_DICE.matcher(trimmedString);
		List<Rollable> rollableList = new ArrayList<>();
		while (matcher.find()) {
			rollableList.add(extractRollable(matcher));
		}
		return new DiceGroup(rollableList);
	}

	public static void main(String[] args) {
		System.out.println(parseRollable("3d10+5"));
	}

	private static Rollable extractRollable(Matcher matcher) throws IllegalArgumentException  {
		if (matcher.groupCount() == 4) {
			if (matcher.group(3) == null) {
				return extractSimpleRollable(matcher);
			} else {
				return extractModifiedRollable(matcher);
			}
		}
		throw new IllegalArgumentException("Can't match the given string");
	}

	private static Rollable extractSimpleRollable(Matcher matcher) {
		int nbDices = Integer.parseInt(matcher.group(1));
		int diceSize = Integer.parseInt(matcher.group(2));
		return buildRollable(nbDices, diceSize);
	}

	private static Rollable extractModifiedRollable(Matcher matcher) {
		int nbDices = Integer.parseInt(matcher.group(1));
		int diceSize = Integer.parseInt(matcher.group(2));
		int modifier = Integer.parseInt(matcher.group(4));
		return buildRollable(nbDices, diceSize, modifier);
	}

	private static Rollable buildRollable(int nbDices, int diceSize) {
		if (nbDices == 1) {
			return new Dice(diceSize);
		}
		return new MultiDice(diceSize, nbDices);
	}

	private static Rollable buildRollable(int nbDices, int diceSize, int modifier) {
		if (nbDices == 1) {
			return new Dice(diceSize, modifier);
		}
		return new MultiDice(diceSize, modifier, nbDices);
	}

}
