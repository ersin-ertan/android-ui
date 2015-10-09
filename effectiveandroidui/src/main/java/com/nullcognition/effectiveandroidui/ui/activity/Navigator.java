package com.nullcognition.effectiveandroidui.ui.activity;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.di.activity.ActivityScope;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowDraggableFragment;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowFragment;

import javax.inject.Inject;

@ActivityScope
public class Navigator{

	private TvShowFragment          tvShowFragment;
	private TvShowDraggableFragment tvShowDraggableFragment;

	private final Context activityContext;

	@Inject public Navigator(Context activityContext){
		this.activityContext = activityContext;
	}

	public void openTvShowDetails(TvShow tvShow){

		if(canInteractWithFragments()){
			showTvShowOnTvShowDraggableFragment(tvShow);
			showTvShowOnTvShowFragment(tvShow);
		}
		else{
			openTvShowActivity(tvShow.getTitle());
		}
	}

	private FragmentManager getFragmentManager(){
		return ((FragmentActivity) activityContext).getSupportFragmentManager();
	}

	private boolean canInteractWithFragments(){
		tvShowFragment = (TvShowFragment) getFragmentManager().findFragmentById(R.id.f_tv_show);
		tvShowDraggableFragment =
				(TvShowDraggableFragment) getFragmentManager().findFragmentById(R.id.f_tv_show_draggable);

		return tvShowDraggableFragment != null || tvShowFragment != null;
	}

	private void showTvShowOnTvShowDraggableFragment(TvShow tvShow){
		if(isFragmentAvailable(tvShowDraggableFragment)){
			tvShowDraggableFragment.showTvShow(tvShow.getTitle());
		}
	}

	private void showTvShowOnTvShowFragment(TvShow tvShow){
		if(isFragmentAvailable(tvShowFragment)){
			tvShowFragment.showTvShow(tvShow.getTitle());
		}
	}

	private boolean isFragmentAvailable(Fragment fragment){
		return fragment != null && fragment.isAdded();
	}

	public void openTvShowActivity(final String tvShowId){
		Intent intent = TvShowActivity.getLaunchIntent(activityContext, tvShowId);
		startActivity(intent);
	}

	private void startActivity(Intent intent){
		activityContext.startActivity(intent);
	}
}
