package com.nullcognition.practice00;
// ersin 01/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.nullcognition.practice00.databinding.FragBinding;
import com.sora.util.akatsuki.Akatsuki;
import com.sora.util.akatsuki.Retained;

public class Frag extends Fragment{

	public static final String TAG = Frag.class.getSimpleName();

	@Retained @Arg public int state = 0;
	@Retained @Arg public Retainable    retainable;
	public                Obj           obj;
	public                FragViewModel fragViewModel;

	public Frag(){}

	@Override public void onAttach(final Context context){
		super.onAttach(context);
		Log.e(TAG, "onAttach() Frag reference:" + this.toString());
		Log.e(TAG, "onAttach() called with: " + "context = [" + context + "]");
	}

	@Override public void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this);
		Akatsuki.restore(this, savedInstanceState); // because fragmentargs restores onCreate, the value
		// of state will be what was first passed to it during FragBuilder construction, thus the akatsuki
		// must overwrite that value with the retained value if it has changed since

		fragViewModel = new FragViewModel(retainable);

		Log.e(TAG, "onCreate() Frag reference:" + this.toString());
		Log.e(TAG, "onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
		Log.e(TAG, "onCreate() Frag State: " + String.valueOf(state));
		if(retainable == null){retainable = new Retainable();}
		Log.e(TAG, "onCreate Retainable State: " + String.valueOf(retainable.getState()));
		if(obj == null){ obj = new Obj(); }
		Log.e(TAG, "onCreate Obj State: " + String.valueOf(obj.state));

//		setRetainInstance(true);
	}


	@Nullable @Override public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
		Akatsuki.restore(this, savedInstanceState);
		Log.e(TAG, "onCreateView() called with: " + "inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
		Log.e(TAG, "onCreateView() Frag State" + String.valueOf(state));
		Log.e(TAG, "onCreateView() Retainable State: " + String.valueOf(retainable.getState()));
		Log.e(TAG, "onCreateView() Obj State: " + String.valueOf(obj.state));


//		View rootView = inflater.inflate(R.layout.frag, container, false);
		FragBinding fragBinding = DataBindingUtil.inflate(inflater, R.layout.frag, container, false);
		fragBinding.setRet(retainable);
		fragBinding.setFragViewModel(fragViewModel);
		return fragBinding.getRoot();
//		fragBinding.setVariable()inflate()bind()invalidateAll()hasPendingBindings()getRoot()getRet()setRet(); and more
	}

	@Override public void onSaveInstanceState(final Bundle outState){
		super.onSaveInstanceState(outState);
		Akatsuki.save(this, outState);
	}
	@Override public void onDestroyView(){
		Log.e(TAG, "onDestroyView() called with: " + "");
		super.onDestroyView();
	}

	@Override public void onDetach(){
		Log.e(TAG, "onDetach() called with: " + "");
		super.onDetach();
	}

	@Override public void onDestroy(){
		Log.e(TAG, "onDestroy() called with: " + "");
		super.onDestroy();
	}

	public static class Obj{

		public int state = 0;
	}
}
