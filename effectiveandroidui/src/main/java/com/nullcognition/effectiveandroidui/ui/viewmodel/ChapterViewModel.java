package com.nullcognition.effectiveandroidui.ui.viewmodel;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.nullcognition.effectiveandroidui.util.RandomUtils;
import com.nullcognition.effectiveandroidui.util.TimeMachine;

public class ChapterViewModel{

	private static final int MAX_RATE_VALUE = 10;
	private static final int TWO_SECONDS    = 2000;

	private final Chapter chapter;

	private Listener listener = new NullListener();

	private Runnable updateRateRunnable = new Runnable(){
		@Override public void run(){
			int randomRate = RandomUtils.getRandomValueBelow(MAX_RATE_VALUE);
			listener.onRateChanged(randomRate);
		}
	};

	public ChapterViewModel(Chapter chapter){
		this.chapter = chapter;
	}

	public String getTitle(){
		return chapter.getTitle();
	}

	public String getPublishDate(){
		return chapter.getPublishDate();
	}

	public void registerListener(final Listener listener){
		this.listener = listener;
		TimeMachine.sendMessageToTheFuture(updateRateRunnable, TWO_SECONDS);
	}

	public void unregisterListener(){
		this.listener = new NullListener();
		TimeMachine.cancelMessage(updateRateRunnable);
	}

	public interface Listener{

		void onRateChanged(final int rate);
	}


	private class NullListener implements Listener{

		@Override public void onRateChanged(int rate){
		}
	}
}
