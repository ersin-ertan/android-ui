package com.nullcognition.googledatabinding01;
// ersin 22/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullcognition.googledatabinding01.databinding.FragmentMainBinding;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

public class MainFragment extends Fragment{

	private   FragmentMainBinding binding;
	@Retained PresentationModel   presentationModel;
	private   Controller          controller;

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
		setPresModel();

		controller = new Controller(binding.getRoot(), presentationModel);
		binding.setController(controller);

		return binding.getRoot();
	}

	private void setPresModel(){
		if(binding != null && presentationModel != null){binding.setPresModel(presentationModel); }
	}

	public void setPresModel(@NonNull PresentationModel presModel){
		presentationModel = presModel;
		if(binding != null){ binding.setPresModel(presentationModel);}
	}


	@Override public void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Akatsuki.restore(this, savedInstanceState);
		if(presentationModel == null){
			presentationModel = new PresentationModel();
		}
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
}
