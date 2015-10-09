package com.nullcognition.joebirchmvvm.injection;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.joebirchmvvm.data.DataManager;
import com.nullcognition.joebirchmvvm.view.activity.MainActivity;
import com.nullcognition.joebirchmvvm.view.fragment.StoriesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class) public interface ApplicationComponent{

	void inject(MainActivity mainActivity);

	DataManagerComponent plus(DataManagerModule dataManagerModule);
//	Application application(); no need due to sub component usage
	DataManager dataManager();
}
