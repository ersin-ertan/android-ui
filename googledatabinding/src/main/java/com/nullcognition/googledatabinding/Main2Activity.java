package com.nullcognition.googledatabinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nullcognition.googledatabinding.databinding.ActivityMain2Binding;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends Activity{

	ActivityMain2Binding activityMain2Binding;
	SomeObservable       someObservable;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		activityMain2Binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
		ButterKnife.bind(this);

		someObservable = new SomeObservable();
		activityMain2Binding.setSomeObservable(someObservable);
	}

	@OnClick(R.id.btn_chgVar) void chgVar(final View view){
		someObservable.setFirstName("new value from class");
	}

}
