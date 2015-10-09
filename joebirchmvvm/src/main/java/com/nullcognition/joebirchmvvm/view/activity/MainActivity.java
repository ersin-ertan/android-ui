package com.nullcognition.joebirchmvvm.view.activity;
// ersin 03/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nullcognition.joebirchmvvm.R;
import com.nullcognition.joebirchmvvm.view.fragment.StoriesFragment;

public class MainActivity extends BaseActivity{

	public static Intent getStartIntent(Context context){
		return new Intent(context, MainActivity.class);
	}

	@Override protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addStoriesFragment();
	}

	@Override public boolean onCreateOptionsMenu(final Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.action_view_on_github:
				//TODO: Add github link
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void addStoriesFragment(){
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.content_frame, new StoriesFragment())
				.commit();
	}
}
