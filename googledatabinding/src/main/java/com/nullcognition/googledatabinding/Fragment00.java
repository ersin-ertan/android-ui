package com.nullcognition.googledatabinding;
// ersin 01/09/15 Copyright (c) 2015+ All rights reserved.

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import android.databinding.DataBindingUtil;

//import com.nullcognition.googledatabinding.databinding.Changed;


public class Fragment00 extends Fragment{

	public static final String TAG = Fragment00.class.getSimpleName();
	User           user;
	//	NameLastBinding binding; // see data layout for name change syntax
	//Changed        binding;
	MyClickHandler handler;
	public Fragment00(){}

	public static Fragment00 newInstance(){

		Bundle args = new Bundle();

		Fragment00 fragment = new Fragment00();
		fragment.setArguments(args);
		return fragment;
	}

	@Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		final View rootView = inflater.inflate(R.layout.name_last, container, false);

		// another way to bind
		////		NameLastBinding binding  = NameLastBinding.inflate(inflater, container, false);
		//		binding = DataBindingUtil.setContentView(getActivity(), R.layout.name_last);
		//
		//		user = new User("First", "Last", true);
		//		binding.setUser(user);
		//		// click handler allows for custom logic to be call due to textviews onClick calling it
		//		handler = new MyClickHandler();
		//		binding.setHandler(handler);

		return rootView;
	}

	@Override public void onViewCreated(final View view, final Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

	}
	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	}

}
