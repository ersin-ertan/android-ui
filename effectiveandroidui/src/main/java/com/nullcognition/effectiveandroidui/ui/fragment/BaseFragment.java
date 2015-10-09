package com.nullcognition.effectiveandroidui.ui.fragment;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment{

	@Override public void onAttach(final Context context){
		super.onAttach(context);
		injectDependencies();
	}
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                                   Bundle savedInstanceState){
		return inflater.inflate(getFragmentLayout(), container, false);
	}

	@Override public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		injectViews(view);
	}

	protected abstract int getFragmentLayout();

	protected abstract void injectDependencies();

	private void injectViews(final View view){
		ButterKnife.bind(this, view);
	}
}
