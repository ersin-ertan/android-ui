package com.nullcognition.joebirchmvvm.injection;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.

import com.nullcognition.joebirchmvvm.data.DataManager;

import dagger.Subcomponent;


@PerDataManager
@Subcomponent(modules = DataManagerModule.class)
public interface DataManagerComponent{

	void inject(DataManager dataManager);
}
