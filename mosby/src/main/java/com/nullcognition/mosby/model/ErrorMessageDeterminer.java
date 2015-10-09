package com.nullcognition.mosby.model;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.

import retrofit.RetrofitError;

public class ErrorMessageDeterminer {

	public String getErrorMessage(Throwable e, boolean pullToRefresh) {
		if (e instanceof RetrofitError && ((RetrofitError) e).getKind() == RetrofitError.Kind.NETWORK) {
			return "Network Error: Are you connected to the internet?";
		}

		return "An unknown error has occurred";
	}
}
