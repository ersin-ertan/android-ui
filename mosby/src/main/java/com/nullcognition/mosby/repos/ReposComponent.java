package com.nullcognition.mosby.repos;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.mosby.SampleModule;
import com.nullcognition.mosby.model.ReposAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = SampleModule.class)
public interface ReposComponent{

	void inject(ReposFragment fragment);

	ReposPresenter presenter();

	ReposAdapter adapter();
}
