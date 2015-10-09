package com.nullcognition.mosby.di.repo;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.mosby.repos.ReposFragment;
import com.nullcognition.mosby.repos.ReposPresenter;

import dagger.Component;
import dagger.Subcomponent;

@ReposScope @Subcomponent(modules = ReposModule.class)
public interface ReposComponent{

	void inject(ReposFragment fragment);

	ReposPresenter reposPresenter();
}
