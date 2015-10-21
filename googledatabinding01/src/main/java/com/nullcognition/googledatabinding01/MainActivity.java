package com.nullcognition.googledatabinding01;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nullcognition.googledatabinding01.databinding.ActivityMainDbBinding;

public class MainActivity extends AppCompatActivity{

	private ActivityMainDbBinding binding;
	ViewModel vm;
	Controller controller = new Controller(this);

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);

		String imageUrl = "https://pbs.twimg.com/profile_images/606585229034135553/2NqZJYQI_400x400.png";

		binding = DataBindingUtil.setContentView(this, R.layout.activity_main_db);
		vm = new ViewModel();
		updateUi();
	}

	private void updateUi(){
		binding.setUser(vm);
		binding.setController(controller);
	}

	public  void updateUser(User user){
		vm.updateUser(user);
		binding.setUser(vm);
	}


}
