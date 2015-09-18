package com.nullcognition.googledatabinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityMain extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
		int                             id = item.getItemId();
		if(id == R.id.action_load_fragment00){
			ft.add(R.id.frameLayout, Fragment00.newInstance(), Fragment00.TAG).commit();
			return true;
		}
		else if(id == R.id.action_new_activity){
			startActivity(new Intent(this, Main2Activity.class));
		}
		return super.onOptionsItemSelected(item);
	}
}
