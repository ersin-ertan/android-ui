package com.nullcognition.googledatabinding01.view;
// ersin 23/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullcognition.googledatabinding01.R;
import com.nullcognition.googledatabinding01.databinding.FragmentMainBinding;
import com.nullcognition.googledatabinding01.view.viewmodel.ConcreteViewModel;
import com.sora.util.akatsuki.Akatsuki;

public class ConcreteFragment extends Fragment{

	FragmentMainBinding binding;
	ConcreteViewModel   concreteViewModel;

	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
		binding.setConcreteViewModel(concreteViewModel);

//		controller = new Controller(binding.getRoot(), concreteViewModel);
//		binding.setController(controller);

		return binding.getRoot();
	}

	@Override public void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Akatsuki.restore(this, savedInstanceState);
		if(concreteViewModel == null){ concreteViewModel = new ConcreteViewModel(); }
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
}
