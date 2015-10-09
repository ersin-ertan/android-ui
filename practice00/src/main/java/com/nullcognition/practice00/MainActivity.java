package com.nullcognition.practice00;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

	public static final String TAG = MainActivity.class.getSimpleName();
	Frag frag;

	@OnClick(R.id.createFrag) void btnCreateFrag(final View view){
		if(frag == null){
			Log.e(TAG, "new Frag()");

			Retainable retainable = getRetainable();

			frag = new FragBuilder(retainable, 2).build();
			getSupportFragmentManager().beginTransaction().add(R.id.linearLayout, frag, Frag.TAG).commit();
		}
	}

	@OnClick(R.id.setStates) void btnSetStates(final View view){
		frag.state = 1;
		Log.e(TAG, "Activity Frag State:" + String.valueOf(frag.state));

		frag.fragViewModel.setState(1); // calling view model instead of retained state
		Log.e(TAG, "Activity Retainable State:" + String.valueOf(frag.retainable.getState()));

		frag.obj.state = 1;
		Log.e(TAG, "Activity Obj State: " + String.valueOf(frag.obj.state));
	}


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		Frag existing = (Frag) getSupportFragmentManager().findFragmentByTag(Frag.TAG);
		if(existing != null){
			Log.e(TAG, "Frag from fragmentManager");
			frag = existing;
		}
	}

	public Retainable getRetainable(){

		String json = "{\"state\": \"3\"}";

		Moshi                   moshi       = new Moshi.Builder().build();
		JsonAdapter<Retainable> jsonAdapter = moshi.adapter(Retainable.class);

		Retainable retainable = null;
		try{ retainable = jsonAdapter.fromJson(json);}
		catch(IOException e){ e.printStackTrace();}
		return retainable;
	}


}
