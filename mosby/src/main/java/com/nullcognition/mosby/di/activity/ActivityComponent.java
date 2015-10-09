package com.nullcognition.mosby.di.activity;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.mosby.MainActivity;
import com.nullcognition.mosby.di.repo.ReposComponent;
import com.nullcognition.mosby.di.repo.ReposModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = { ActivityModule.class }) public interface ActivityComponent{


	void inject(MainActivity mainActivity);

	ReposComponent plus(ReposModule reposModule);
}
