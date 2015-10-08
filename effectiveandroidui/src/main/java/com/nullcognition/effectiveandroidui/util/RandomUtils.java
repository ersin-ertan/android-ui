package com.nullcognition.effectiveandroidui.util;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.

import java.util.Random;

public class RandomUtils {

	private static final Random RANDOM       = new Random();
	private static final int    CENT_PERCENT = 101;

	private RandomUtils(){
		//Empty
	}

	/**
	 * Return true of false using a random value generated and the percentage passed as parameter.
	 *
	 * @param percentage to evaluate.
	 * @return true fifty percent of the times it's executed if the percentage parameter is 50.
	 */
	public static boolean percent(final int percentage) {
		return (RANDOM.nextInt(CENT_PERCENT) < percentage);
	}

	/**
	 * Returns a random integer between 0 and the maxValue argument, included maxValue.
	 */
	public static int getRandomValueBelow(final int maxValue) {
		return RANDOM.nextInt(maxValue + 1);
	}
}
