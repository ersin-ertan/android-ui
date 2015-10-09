package com.nullcognition.effectiveandroidui.domain;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;

public interface GetTvShowById{

	interface Callback{

		void onTvShowLoaded(final TvShow tvShow);

		void onTvShowNotFound();

		void onConnectionError();
	}

	void execute(final String tvShowId, final Callback callback);
}
