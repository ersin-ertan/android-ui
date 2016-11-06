package com.nullcognition.googledatabinding01.view;
// ersin 22/10/15 Copyright (c) 2015+ All rights reserved.

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nullcognition.googledatabinding01.controller.Controller;
import com.nullcognition.googledatabinding01.view.viewmodel.PresentationModel;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

//import com.nullcognition.googledatabinding01.databinding.FragmentMainBinding;

/* Notes - do not wrap the model object in the presentation model, use a facade to hide properties and pick the correct
facade to populate the view model base on configuration...
*/


public class MainFragment extends Fragment{

	//private   FragmentMainBinding binding;

	@Retained PresentationModel   presentationModel;

	// @Inject
	private   Controller          controller;

	public void setPresModel(@NonNull PresentationModel presModel){
		presentationModel = presModel;
		//if(binding != null){ binding.setPresModel(presentationModel); }
	}

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){

		//binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
		//binding.setPresModel(presentationModel);
		//
		//controller = new Controller(binding.getRoot(), presentationModel);
		//binding.setController(controller);
		//
		//return binding.getRoot();
		return null;
	}

	@Override public void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Akatsuki.restore(this, savedInstanceState);
		if(presentationModel == null){ presentationModel = new PresentationModel(); }
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
}
