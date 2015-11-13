package com.nullcognition.widgetswithcontentproviders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullcognition.widgetswithcontentproviders.contentProvider.ContentProviderFragment;

public class MainActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getFragmentManager().beginTransaction().add(R.id.frameLayout, new ContentProviderFragment()).commit();
	}
}
