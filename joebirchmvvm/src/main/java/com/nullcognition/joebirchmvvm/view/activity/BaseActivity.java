package com.nullcognition.joebirchmvvm.view.activity;
// ersin 03/10/15 Copyright (c) 2015+ All rights reserved.


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity{

	@Override public boolean onOptionsItemSelected(final MenuItem item){
		switch(item.getItemId()){
			case android.R.id.home:
				FragmentManager fm = getSupportFragmentManager();
				if(fm.getBackStackEntryCount() > 0){
					fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				}
				else{finish();}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
