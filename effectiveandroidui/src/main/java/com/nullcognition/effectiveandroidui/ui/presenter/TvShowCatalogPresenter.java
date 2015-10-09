package com.nullcognition.effectiveandroidui.ui.presenter;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.di.activity.ActivityScope;
import com.nullcognition.effectiveandroidui.domain.GetTvShows;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.activity.Navigator;
import com.nullcognition.effectiveandroidui.ui.renderer.tvshow.TvShowCollection;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

@ActivityScope
public class TvShowCatalogPresenter extends Presenter{

	private GetTvShows getTvShowsInteractor;
	private Navigator  navigator;

	private View             view;
	private TvShowCollection currentTvShowCollection;

	@Inject	public TvShowCatalogPresenter(GetTvShows getTvShowsInteractor, Navigator navigator){
		this.getTvShowsInteractor = getTvShowsInteractor;
		this.navigator = navigator;
	}

	public void setView(View view){
		if(view == null){
			throw new IllegalArgumentException("You can't set a null view");
		}
		this.view = view;
	}

	@Override
	public void initialize(){
		checkViewAlreadySetted();
		loadTvShows();
	}

	@Override
	public void resume(){}

	@Override
	public void pause(){}

	public void loadCatalog(final TvShowCollection tvShowCollection){
		currentTvShowCollection = tvShowCollection;
		showTvShows(tvShowCollection.getAsList());
	}

	public void onTvShowThumbnailClicked(final TvShow tvShow){
		navigator.openTvShowDetails(tvShow);
	}

	public void onTvShowClicked(final TvShow tvShow){
		view.showTvShowTitleAsMessage(tvShow);
	}

	public TvShowCollection getCurrentTvShows(){
		return currentTvShowCollection;
	}

	private void loadTvShows(){
		if(view.isReady()){
			view.showLoading();
		}
		getTvShowsInteractor.execute(new GetTvShows.Callback(){
			@Override public void onTvShowsLoaded(final Collection<TvShow> tvShows){
				currentTvShowCollection = new TvShowCollection(tvShows);
				showTvShows(tvShows);
			}

			@Override public void onConnectionError(){
				notifyConnectionError();
			}
		});
	}

	private void notifyConnectionError(){
		if(view.isReady() && !view.isAlreadyLoaded()){
			view.hideLoading();
			view.showConnectionErrorMessage();
			view.showEmptyCase();
			view.showDefaultTitle();
		}
	}

	private void showTvShows(Collection<TvShow> tvShows){
		if(view.isReady()){
			view.renderVideos(tvShows);
			view.hideLoading();
			view.updateTitleWithCountOfTvShows(tvShows.size());
		}
	}

	private void checkViewAlreadySetted(){
		if(view == null){
			throw new IllegalArgumentException("Remember to set a view for this presenter");
		}
	}

	public interface View{

		void hideLoading();

		void showLoading();

		void renderVideos(final Collection<TvShow> tvShows);

		void updateTitleWithCountOfTvShows(final int counter);

		void showConnectionErrorMessage();

		void showEmptyCase();

		void showDefaultTitle();

		void showTvShowTitleAsMessage(TvShow tvShow);

		boolean isReady();

		boolean isAlreadyLoaded();
	}
}
