package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.view.View;

public class Controller{

	public final String CONTROLLER_BUTTON_TEXT = "Controller Button";
	public PresentationModel presentationModel;


	Controller(final PresentationModel pm){
		presentationModel = pm;
	}

	public void controllerButtonClick(final View view){
		presentationModel.setText("$1.00");
	}
}
