package com.nullcognition.effectiveandroidui.util;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.

public class StringUtils {

	private static final String EMPTY_STRING = "";

	private StringUtils() {
		//Empty
	}

	public static boolean isNullOrEmpty(final String string) {
		return string == null || EMPTY_STRING.equals(string);
	}
}
