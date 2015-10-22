package com.nullcognition.googledatabinding01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

	private static final String TAG = "MainACtivity";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		presentationModel = new PresentationModel(binding);

		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.linearLayout, new MainFragment(), "MainFragment")
			                           .commit();
		}
//		binding = DataBindingUtil.setContentView(this, R.layout.fragment_main);

	}
}
