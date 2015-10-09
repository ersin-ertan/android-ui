package com.nullcognition.effectiveandroidui.domain;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;

import java.util.Collection;

public interface GetTvShows{

	interface Callback{

		void onTvShowsLoaded(final Collection<TvShow> tvShows);

		void onConnectionError();
	}

	void execute(Callback callback);
}
