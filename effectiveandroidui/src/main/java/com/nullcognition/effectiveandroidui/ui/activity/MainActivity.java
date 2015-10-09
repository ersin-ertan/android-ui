package com.nullcognition.effectiveandroidui.ui.activity;

import android.os.Bundle;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowDraggableFragment;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowFragment;

public class MainActivity extends BaseActivity{

	private TvShowDraggableFragment tvShowDraggableFragment;
	private TvShowFragment          tvShowFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		initTvShowDraggableFragment();
		initTvShowFragment();
	}

	@Override protected void injectDependencies(){ activityComponent.inject(this); }

	private void initTvShowFragment(){
		tvShowFragment = (TvShowFragment) getSupportFragmentManager().findFragmentById(R.id.f_tv_show);
	}

	private void initTvShowDraggableFragment(){
		tvShowDraggableFragment =
				(TvShowDraggableFragment) getSupportFragmentManager().findFragmentById(R.id.f_tv_show_draggable);

		if(tvShowFragment != null && tvShowDraggableFragment != null){
			tvShowDraggableFragment.disableSaveInstanceState();
		}
	}


}
