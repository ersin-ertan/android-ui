package com.nullcognition.googledatabinding;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nullcognition.googledatabinding.databinding.Changed;


public class DataBinding00 extends Fragment{


	User           user;
	MyClickHandler handler;
	//	NameLastBinding binding; // see data layout for name change syntax
	Changed        binding;

	public static final String TAG = DataBinding00.class.getSimpleName();
	public DataBinding00(){}

	public static DataBinding00 newInstance(){

		Bundle args = new Bundle();

		DataBinding00 fragment = new DataBinding00();
		fragment.setArguments(args);
		return fragment;
	}

	@Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		final View rootView = inflater.inflate(R.layout.name_last, container, false);

//		NameLastBinding binding  = NameLastBinding.inflate(inflater, container, false);
		binding = DataBindingUtil.setContentView(getActivity(), R.layout.name_last);

		user = new User("First", "Last", true);
		binding.setUser(user);
		handler = new MyClickHandler();
		binding.setHandler(handler);

		return rootView;
	}

	@Override public void onViewCreated(final View view, final Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

	}
	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	}

}
