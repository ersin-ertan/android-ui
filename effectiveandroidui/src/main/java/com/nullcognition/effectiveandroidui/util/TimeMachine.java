package com.nullcognition.effectiveandroidui.util;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Handler;

public class TimeMachine{

	private static Handler handler = new Handler();

	private TimeMachine(){
		//Empty
	}

	/**
	 Execute a Runnable implementation some milliseconds in the future.

	 @param runnable to execute.
	 @param delay    in miliseconds.
	 */
	public static void sendMessageToTheFuture(final Runnable runnable, final int delay){
		handler.postDelayed(runnable, delay);
	}

	/**
	 Cancel a message already sent to the future.
	 */
	public static void cancelMessage(Runnable runnable){
		handler.removeCallbacks(runnable);
	}
}
