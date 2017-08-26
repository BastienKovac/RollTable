package com.kovac.rolltable.utils.range;

import java.util.Collection;
import java.util.Objects;

public class Range implements Comparable<Range> {

	private int minRange, maxRange;

	/**
	 * Builds a Range. Bounds are both inclusive
	 *
	 * @param minRange The lower bound
	 * @param maxRange The upper bound
	 */
	public Range(int minRange, int maxRange) {
		this.minRange = Math.min(minRange, maxRange);
		this.maxRange = Math.max(minRange, maxRange);
	}

	public boolean isInRange(int value) {
		return minRange <= value && value <= maxRange;
	}

	public boolean overlaps(Collection<Range> otherRanges) {
		for (Range range : otherRanges) {
			if (overlaps(range)) {
				return true;
			}
		}
		return false;
	}

	public boolean overlaps(Range other) {
		return minRange <= other.maxRange && maxRange <= other.minRange;
	}

	@Override
	public String toString() {
		return minRange + " - " + maxRange;
	}

	@Override
	public int hashCode() {
		return Objects.hash(minRange, maxRange);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && (obj instanceof Range)) {
			return minRange == ((Range) obj).minRange && maxRange == ((Range) obj).maxRange;
		}
		return false;
	}

	@Override
	public int compareTo(Range o) {
		if (minRange == o.minRange) {
			return Integer.compare(minRange, o.minRange);
		}
		return Integer.compare(maxRange, o.maxRange);
	}

}
