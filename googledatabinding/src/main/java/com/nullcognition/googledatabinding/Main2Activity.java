package com.nullcognition.googledatabinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.nullcognition.googledatabinding.databinding.ActivityMain2Binding;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends Activity{

	ActivityMain2Binding activityMain2Binding;
	SomeObservable       someObservable;

	SomeObservableFieldHolder someObservableFieldHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);



		activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
		ButterKnife.bind(this);


		someObservable = new SomeObservable();
		someObservableFieldHolder = new SomeObservableFieldHolder();


// testing as to the need for initialization for data binding to work

		activityMain2Binding.setSofh(someObservableFieldHolder);
		activityMain2Binding.setSomeObservable(someObservable);
	}

	@OnClick(R.id.btn_chgVar) void chgVar(final View view){
		someObservable.setFirstName("new value from class");

		someObservableFieldHolder.lastName.set("lastnameSet from class");
		someObservableFieldHolder.age.set(2);
	}

}
