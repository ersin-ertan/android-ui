package com.nullcognition.googledatabinding01;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nullcognition.googledatabinding01.view.ConcreteFragment;
import com.nullcognition.googledatabinding01.view.MainFragment;


public class MainActivity extends AppCompatActivity{

	private static final String TAG = "MainACtivity";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		DataBindingUtil.setContentView(this, R.layout.activity_main);
	}

	public void startFragment(View view){

		int      id = view.getId();
		Fragment startFragment;
		switch(id){
			case R.id.btn_c00:
				startFragment = new MainFragment();
				break;
			case R.id.btn_c01:
				startFragment = new ConcreteFragment();
				break;
			case R.id.btn_c02:
				startFragment = new MainFragment();
				break;
			case R.id.btn_c03:
				startFragment = new MainFragment();
				break;
			default:
				throw new IllegalAccessError();
		}

		getSupportFragmentManager().beginTransaction()
		                           .replace(R.id.content,
				                           startFragment,
				                           startFragment.getClass().getSimpleName())
		                           .commit();
	}

}
