package com.nullcognition.mosby.model;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import java.util.List;

import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

public interface GithubApi{

	@GET("/repositories")
	@Headers("Cache-Control: no-cache")
	Observable<List<Repo>> getRepos();
}
