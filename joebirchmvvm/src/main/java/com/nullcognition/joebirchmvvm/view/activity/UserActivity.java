package com.nullcognition.joebirchmvvm.view.activity;
// ersin 03/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nullcognition.joebirchmvvm.R;
import com.nullcognition.joebirchmvvm.view.fragment.StoriesFragment;

public class UserActivity extends BaseActivity{

	public static final String EXTRA_USER =
			"com.nullcognition.mvvm_hackernews.ui.activity.UserActivity.EXTRA_USER";

	public static Intent getStartIntent(Context context, String user){
		Intent intent = new Intent(context, UserActivity.class);
		intent.putExtra(EXTRA_USER, user);
		return intent;
	}

	@Override protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		String username = getIntent().getStringExtra(EXTRA_USER);
		if(username == null){
			throw new IllegalArgumentException("UserActivity requires a user object!");
		}
		addStoriesFragment(username);
	}

	private void addStoriesFragment(String username){
		Fragment storiesFragment = StoriesFragment.newInstance(username);
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, storiesFragment)
				.commit();
	}
}
