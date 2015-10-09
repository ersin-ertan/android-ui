package com.nullcognition.mosby.repos;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.mosby.model.GithubApi;
import com.nullcognition.mosby.model.Repo;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ReposPresenter extends MvpLceRxPresenter<ReposView, List<Repo>>{

	GithubApi githubapi;

	@Inject public ReposPresenter(GithubApi githubapi){
		this.githubapi = githubapi;
	}

	public void loadRepos(boolean pullToRefresh){

		Observable<List<Repo>> observable =
				githubapi.getRepos().flatMap(repos -> {
					Collections.shuffle(repos);
					return Observable.just(repos);
				});

		subscribe(observable, pullToRefresh);
	}
}
