package com.nullcognition.mosby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullcognition.mosby.di.activity.ActivityComponent;
import com.nullcognition.mosby.repos.ReposFragment;

// working with retrofit v 1.9.0, when updating to 2.0.0 be sure to check the differences in the api like:
// client(client), setUrl("http..."), requirement to set your own adapterConverter(RxAdapterConverterFactory.create())

// setRetainInstance(true) in fragment nullifies this point
// on rotation not working -
// ViewState RetainingLceViewState of ReposFragment{281388fe #0 id=0x7f0c0051} is not Restorable (can not be
// serialized in bundle, must implement RestorableViewState) nor is retaining (in memory) ViewState feature enabled.
// That means that the ViewState can not survive orientation changes and ViewState will always be lost.
// Hence using Mosby's ViewState feature makes no sense. Either fix your ViewState settings (make ViewState restorable
// or turn retaining feature on) or if you don't need the ViewState feature you should use the classes without
// viewstate from Mosby's mvp module


public class MainActivity extends AppCompatActivity{

	private ActivityComponent activityComponent;
	public ActivityComponent getActivityComponent(){return activityComponent;}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		injectDependencies();

		if(savedInstanceState == null){
			getSupportFragmentManager().beginTransaction()
			                           .add(R.id.fragmentContainer, new ReposFragment())
			                           .commit();
		}
	}
	private void injectDependencies(){
		if(activityComponent == null){
			activityComponent = MosbyApplication.get(this).createActivityComponent(this);
		}
		activityComponent.inject(this);
	}

	@Override public void finish(){
		MosbyApplication.get(this).releaseActivityComponent();
		super.finish();
	}
}
