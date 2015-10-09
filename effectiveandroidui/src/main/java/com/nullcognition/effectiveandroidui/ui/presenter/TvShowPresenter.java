package com.nullcognition.effectiveandroidui.ui.presenter;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.GetTvShowById;
import com.nullcognition.effectiveandroidui.domain.tvshow.ChapterCollection;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TvShowPresenter extends Presenter{

	private final GetTvShowById getTvShowById;
	private       TvShow        currentTvShow;

	@Inject public TvShowPresenter(GetTvShowById getTvShowById){
		this.getTvShowById = getTvShowById;
	}

	private View view;

	@Override public void initialize(){
	}

	@Override public void resume(){
	}

	@Override public void pause(){
	}

	public void setView(View view){
		this.view = view;
	}

	public TvShow getCurrentTvShow(){
		return currentTvShow;
	}

	public void tvShowClosed(){
		currentTvShow = null;
	}

	public void loadTvShow(final TvShow tvShow){
		showTvShow(tvShow);
	}

	public void loadTvShow(final String tvShowId){
		view.showLoading();
		getTvShowById.execute(tvShowId, new GetTvShowById.Callback(){
			@Override public void onTvShowLoaded(TvShow tvShow){
				showTvShow(tvShow);
			}

			@Override public void onTvShowNotFound(){
				showTvShowNotFound();
			}

			@Override public void onConnectionError(){
				showConnectionError();
			}
		});
	}

	private void showConnectionError(){
		if(view.isReady() && !view.isAlreadyLoaded()){
			currentTvShow = null;
			view.hideLoading();
			view.showConnectionErrorMessage();
		}
	}

	private void showTvShowNotFound(){
		if(view.isReady()){
			currentTvShow = null;
			view.hideLoading();
			view.showTvShowNotFoundMessage();
		}
	}

	private void showTvShow(TvShow tvShow){
		if(view.isReady()){
			currentTvShow = tvShow;
			view.showFanArt(tvShow.getFanArt());
			view.showTvShowTitle(tvShow.getTitle().toUpperCase());
			view.showChapters(tvShow.getChapters());
			view.hideLoading();
			view.showTvShow();
		}
	}

	public interface View{

		void showLoading();

		void showFanArt(final String tvShowFanArtUrl);

		void showChapters(final ChapterCollection episodes);

		void hideLoading();

		void showTvShowNotFoundMessage();

		void showConnectionErrorMessage();

		void showTvShow();

		void showTvShowTitle(String tvShowTitle);

		boolean isReady();

		boolean isAlreadyLoaded();
	}
}
