package com.nullcognition.effectiveandroidui.di.domain;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.GetTvShowById;
import com.nullcognition.effectiveandroidui.domain.GetTvShowByIdInteractor;
import com.nullcognition.effectiveandroidui.domain.GetTvShows;
import com.nullcognition.effectiveandroidui.domain.GetTvShowsInteractor;
import com.nullcognition.effectiveandroidui.domain.tvshow.Catalog;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module public class TvShowsModule{

	@Singleton
	@Provides Catalog provideCatalog(){ return new Catalog(); }

	@Provides GetTvShows provideGetTvShowsInteractor(GetTvShowsInteractor interactor){
		return interactor;
	}

	@Provides GetTvShowById provideGetTvShowsByIdInteractor(GetTvShowByIdInteractor interactor){
		return interactor;
	}

}
