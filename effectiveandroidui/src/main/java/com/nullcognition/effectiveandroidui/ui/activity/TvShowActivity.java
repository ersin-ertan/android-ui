package com.nullcognition.effectiveandroidui.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowFragment;
import com.nullcognition.effectiveandroidui.util.StringUtils;

public class TvShowActivity extends BaseActivity{


	private static final String EXTRA_TV_SHOW_ID = "extra_tv_show_id";
	private String tvShowId;

	public static Intent getLaunchIntent(final Context context, final String tvShowId){
		if(StringUtils.isNullOrEmpty(tvShowId)){
			throwIllegalArgumentException();
		}
		Intent intent = new Intent(context, TvShowActivity.class);
		return intent.putExtra(EXTRA_TV_SHOW_ID, tvShowId);
	}

	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tv_show);
		mapExtras();
		initializeFragment();
	}

	@Override protected void injectDependencies(){ activityComponent.inject(this); }

//	@Override protected List<Object> getModules(){
//		List<Object> modules = new LinkedList<Object>();
//		modules.add(new TvShowUIModule());
//		return modules;
//	}

	private void mapExtras(){
		Bundle extras = getIntent().getExtras();
		if(extras == null){
			throwIllegalArgumentException();
		}
		tvShowId = extras.getString(EXTRA_TV_SHOW_ID);
		if(StringUtils.isNullOrEmpty(tvShowId)){
			throwIllegalArgumentException();
		}
	}

	private void initializeFragment(){
		TvShowFragment tvShowFragment =
				(TvShowFragment) getSupportFragmentManager().findFragmentById(R.id.f_tv_show);
		tvShowFragment.showTvShow(tvShowId);
	}

	private static void throwIllegalArgumentException(){
		throw new IllegalArgumentException(
				"TvShowActivity has to be launched using a TvShow identifier as extra");
	}
}
