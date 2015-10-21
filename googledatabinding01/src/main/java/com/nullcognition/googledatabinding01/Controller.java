package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.app.Activity;
import android.view.View;
import android.widget.Toast;

public class Controller{

	MainActivity context;
	public final String CONTROLLER_BUTTON_TEXT = "Controller Button";

	Controller(MainActivity c){context = c;}

	public void controllerButtonClick(final View view){
		Toast.makeText(context, "controllerButtonClick", Toast.LENGTH_SHORT).show();
		context.updateUser(new User("set via controllerbuttonClick"));
	}
}
