package com.nullcognition.joebirchmvvm.injection;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.joebirchmvvm.data.remote.HackerNewsService;
import com.nullcognition.joebirchmvvm.data.remote.RetrofitHelper;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module public class DataManagerModule{

	public DataManagerModule(){}

	@PerDataManager
	@Provides HackerNewsService provideHackerNewsService(){
		return new RetrofitHelper().newHackerNewsService();
	}

	@PerDataManager
	@Provides Scheduler provideSubscribScheduler(){ return Schedulers.io();}

}
