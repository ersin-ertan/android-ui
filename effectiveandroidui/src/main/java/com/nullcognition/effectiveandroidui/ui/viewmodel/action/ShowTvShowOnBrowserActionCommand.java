package com.nullcognition.effectiveandroidui.ui.viewmodel.action;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.nullcognition.effectiveandroidui.di.activity.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public class ShowTvShowOnBrowserActionCommand implements ActionCommand{

	private final Context context;

	private String tvShowUrl;

	@Inject public ShowTvShowOnBrowserActionCommand(Context context){
		this.context = context;
	}

	public void setTvShowUrl(String tvShowUrl){
		this.tvShowUrl = tvShowUrl;
	}

	@Override public void execute(){
		if(tvShowUrl != null){
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tvShowUrl));
			context.startActivity(browserIntent);
		}
	}
}
