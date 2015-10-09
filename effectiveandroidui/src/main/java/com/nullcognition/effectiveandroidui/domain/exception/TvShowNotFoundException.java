package com.nullcognition.effectiveandroidui.domain.exception;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


public class TvShowNotFoundException extends Exception{

	public TvShowNotFoundException(){
		super();
	}

	public TvShowNotFoundException(final String message){
		super(message);
	}

	public TvShowNotFoundException(final String message, final Throwable cause){
		super(message, cause);
	}

	public TvShowNotFoundException(final Throwable cause){
		super(cause);
	}
}

