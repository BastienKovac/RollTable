package com.kovac.rolltable.utils.range;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class RangeMap<E> {

	private final SortedMap<Range, E> rangeMap;
	private E defaultValue;

	public RangeMap() {
		this.rangeMap = new TreeMap<>();
	}

	public void addRangeValueCombo(Range range, E result) throws RangeOverlapException {
		checkOverlaps(range);
		this.rangeMap.put(range, result);
	}

	public void setDefaultValue(E defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isRangeMapFull(int lowerBound, int upperBound) {
		for (int i = Math.min(lowerBound, upperBound) ; i <= Math.max(lowerBound, upperBound) ; i++) {
			if (getAssociatedResult(i) == null) {
				return false;
			}
		}
		return true;
	}

	public E getAssociatedResult(int value) {
		for (Entry<Range, E> entry : rangeMap.entrySet()) {
			if (entry.getKey().isInRange(value)) {
				return entry.getValue();
			}
		}
		return defaultValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Entry<Range, E> entry : rangeMap.entrySet()) {
			builder.append(entry.getKey()).append(" -> ");
			builder.append(entry.getValue() == null ? defaultValue : entry.getValue()).append("\n");
		}
		return builder.toString();
	}

	private void checkOverlaps(Range range) throws RangeOverlapException {
		if (range.overlaps(rangeMap.keySet())) {
			throw new RangeOverlapException("The range " + range + " overlaps with an already existing range");
		}
	}

}
