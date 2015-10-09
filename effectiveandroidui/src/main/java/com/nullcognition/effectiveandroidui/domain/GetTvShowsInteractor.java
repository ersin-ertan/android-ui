package com.nullcognition.effectiveandroidui.domain;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.tvshow.Catalog;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.executor.Executor;
import com.nullcognition.effectiveandroidui.executor.Interactor;
import com.nullcognition.effectiveandroidui.executor.MainThread;
import com.nullcognition.effectiveandroidui.util.RandomUtils;

import java.util.Collection;

import javax.inject.Inject;

public class GetTvShowsInteractor implements Interactor, GetTvShows{

	private static final int PERCENTAGE_OF_FAILS = 50;
	public static final  int WAIT_TIME           = 1500;

	private final Catalog    catalog;
	private final Executor   executor;
	private final MainThread mainThread;

	private Callback callback;

	@Inject public GetTvShowsInteractor(Catalog catalog, Executor executor, MainThread mainThread){
		this.catalog = catalog;
		this.executor = executor;
		this.mainThread = mainThread;
	}

	@Override public void execute(final Callback callback){
		if(callback == null){
			throw new IllegalArgumentException(
					"Callback can't be null, the client of this interactor needs to get the response "
							+ "in the callback");
		}
		this.callback = callback;
		this.executor.run(this);
	}

	@Override public void run(){
		waitToDoThisSampleMoreInteresting();

		if(haveToShowError()){
			notifyError();
		}
		else{
			Collection<TvShow> tvShows = catalog.getTvShows();
			nofityTvShowsLoaded(tvShows);
		}
	}

	/**
	 To simulate a we are getting the TvShows data from internet we are going to force a 1.5
	 seconds
	 delay using Thread.sleep.
	 */
	private void waitToDoThisSampleMoreInteresting(){
		try{
			Thread.sleep(WAIT_TIME);
		}
		catch(InterruptedException e){
			//Empty
		}
	}

	private boolean haveToShowError(){
		return RandomUtils.percent(PERCENTAGE_OF_FAILS);
	}

	private void notifyError(){
		mainThread.post(new Runnable(){
			@Override public void run(){
				callback.onConnectionError();
			}
		});
	}

	private void nofityTvShowsLoaded(final Collection<TvShow> tvShows){
		mainThread.post(new Runnable(){
			@Override public void run(){
				callback.onTvShowsLoaded(tvShows);
			}
		});
	}
}
