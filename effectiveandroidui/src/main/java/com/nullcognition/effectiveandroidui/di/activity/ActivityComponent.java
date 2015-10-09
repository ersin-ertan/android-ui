package com.nullcognition.effectiveandroidui.di.activity;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.di.presenter.TvShowUIModule;
import com.nullcognition.effectiveandroidui.ui.activity.MainActivity;
import com.nullcognition.effectiveandroidui.ui.activity.TvShowActivity;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowCatalogFragment;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowDraggableFragment;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = { ActivityModule.class, TvShowUIModule.class })
public interface ActivityComponent{

	void inject(MainActivity mainActivity);
	void inject(TvShowActivity tvShowActivity);

	void inject(TvShowCatalogFragment tvShowCatalogFragment);
	void inject(TvShowDraggableFragment tvShowDraggableFragment);
	void inject(TvShowFragment TvShowFragment);

}
