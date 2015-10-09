package com.nullcognition.effectiveandroidui.ui.activity;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullcognition.effectiveandroidui.TvShowsApplication;
import com.nullcognition.effectiveandroidui.di.activity.ActivityComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity{

	ActivityComponent activityComponent;
	public ActivityComponent getActivityComponent(){ return activityComponent; }

	@Override protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		createActivityComponent();
		injectDependencies();
		injectViews();
	}
	protected abstract void injectDependencies();

	protected void injectViews(){ ButterKnife.bind(this); }

	private void createActivityComponent(){

		if(activityComponent == null){
			activityComponent = TvShowsApplication.get(this)
			                                      .createActivityComponent(this);
		}
	}

	@Override public void finish(){
		TvShowsApplication.get(this)
		                  .releaseActivityComponent();
		super.finish();
	}

//	protected abstract List<Object> getModules();
}
