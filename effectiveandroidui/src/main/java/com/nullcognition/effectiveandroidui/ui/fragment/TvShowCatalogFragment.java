package com.nullcognition.effectiveandroidui.ui.fragment;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.activity.BaseActivity;
import com.nullcognition.effectiveandroidui.ui.presenter.TvShowCatalogPresenter;
import com.nullcognition.effectiveandroidui.ui.renderer.tvshow.TvShowCollection;
import com.nullcognition.effectiveandroidui.ui.renderer.tvshow.TvShowRendererAdapterFactory;
import com.nullcognition.effectiveandroidui.util.ToastUtils;
import com.pedrogomez.renderers.RendererAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;

public class TvShowCatalogFragment extends BaseFragment implements TvShowCatalogPresenter.View{

	private static final String EXTRA_TV_SHOW_CATALOG = "extra_tv_show_catalog";

	@Inject TvShowCatalogPresenter       tvShowCatalogPresenter;
	@Inject TvShowRendererAdapterFactory tvShowRendererAdapterFactory;

	private RendererAdapter<TvShow> adapter;
	private TvShowCollection tvShows = new TvShowCollection();

	@Bind(R.id.pb_loading)   ProgressBar pb_loading;
	@Bind(R.id.gv_tv_shows)  GridView    gv_tv_shows;
	@Bind(R.id.v_empty_case) View        v_empty_case;

	@Override protected void injectDependencies(){ ((BaseActivity) getActivity()).getActivityComponent().inject(this); }

	@Override public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		initializeGridView();
		tvShowCatalogPresenter.setView(this);
		tvShowCatalogPresenter.initialize();
	}

	@Override public void onAttach(Context context){
		super.onAttach(context);
	}

	@Override public void onResume(){
		super.onResume();
		tvShowCatalogPresenter.resume();
	}

	@Override public void onPause(){
		super.onPause();
		tvShowCatalogPresenter.pause();
	}

	@Override public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putSerializable(EXTRA_TV_SHOW_CATALOG, tvShowCatalogPresenter.getCurrentTvShows());
	}

	@Override public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(savedInstanceState != null){
			final TvShowCollection tvShowCollection =
					(TvShowCollection) savedInstanceState.getSerializable(EXTRA_TV_SHOW_CATALOG);
			updatePresenterWithSavedTvShow(tvShowCollection);
		}
	}

	@Override public void hideLoading(){
		pb_loading.setVisibility(View.GONE);
	}

	@Override public void showLoading(){
		pb_loading.setVisibility(View.VISIBLE);
	}

	@Override public void renderVideos(final Collection<TvShow> tvShows){
		this.tvShows.clear();
		this.tvShows.addAll(tvShows);
		refreshAdapter();
	}

	@Override public void updateTitleWithCountOfTvShows(final int counter){
		String actionBarTitle = getString(R.string.app_name_with_chapter_counter, counter);
		getActivity().setTitle(actionBarTitle);
	}

	@Override public void showConnectionErrorMessage(){
		String connectionError = getString(R.string.connection_error_message);
		ToastUtils.showShortMessage(connectionError, getActivity());
	}

	@Override public void showEmptyCase(){
		v_empty_case.setVisibility(View.VISIBLE);
	}

	@Override public void showDefaultTitle(){
		getActivity().setTitle(R.string.app_name);
	}

	@Override public void showTvShowTitleAsMessage(TvShow tvShow){
		ToastUtils.showShortMessage(tvShow.getTitle(), getActivity());
	}

	@Override public boolean isReady(){
		return isAdded();
	}

	@Override public boolean isAlreadyLoaded(){
		return adapter.getCount() > 0;
	}

	@Override protected int getFragmentLayout(){
		return R.layout.fragment_tv_shows;
	}

	private void initializeGridView(){
		adapter = tvShowRendererAdapterFactory.getTvShowRendererAdapter(tvShows);
		gv_tv_shows.setAdapter(adapter);
	}

	private void updatePresenterWithSavedTvShow(TvShowCollection tvShowCollection){
		if(tvShowCollection != null){
			tvShowCatalogPresenter.loadCatalog(tvShowCollection);
		}
	}

	private void refreshAdapter(){
		adapter.notifyDataSetChanged();
	}
}
