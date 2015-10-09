package com.nullcognition.joebirchmvvm.data.remote;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import com.google.gson.GsonBuilder;
import com.nullcognition.joebirchmvvm.HackerNewsApplication;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper{

	public HackerNewsService newHackerNewsService(){
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(HackerNewsService.ENDPOINT)
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setConverter(new GsonConverter((new GsonBuilder().create())))
				.build();
		return restAdapter.create(HackerNewsService.class);
	}
}
