package com.nullcognition.mosby.di.activity;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import android.app.Activity;
import android.content.Context;

import com.nullcognition.mosby.model.ErrorMessageDeterminer;
import com.nullcognition.mosby.model.GithubApi;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Module public class ActivityModule{

	final private Activity activity;
	public ActivityModule(Activity a){activity = a;}

	@Provides public Context provideContext(){
		return activity;
	}

	@ActivityScope
	@Provides Picasso providesPicasso(){
		return Picasso.with(activity);
	}

	@ActivityScope
	@Provides public GithubApi providesGithubApi(){

		OkHttpClient client = new OkHttpClient();
		client.setCache(new Cache(activity.getCacheDir(), 10 * 1024 * 1024));

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setClient(new OkClient(client))
				.setEndpoint("https://api.github.com")
				.build();

		return restAdapter.create(GithubApi.class);
	}

	@ActivityScope
	@Provides public ErrorMessageDeterminer providesErrorMessageDeterminer(){ return new ErrorMessageDeterminer(); }
}
