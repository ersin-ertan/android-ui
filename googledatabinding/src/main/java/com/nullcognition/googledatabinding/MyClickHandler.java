package com.nullcognition.googledatabinding;
// ersin 02/09/15 Copyright (c) 2015+ All rights reserved.


import android.graphics.Color;
import android.view.View;

public class MyClickHandler{

	int color = Color.RED;

	public void isOn(View view){
		color = Color.CYAN;
		view.setBackgroundColor(color);
	}
	public void isOff(View view){
		color = Color.LTGRAY;
		view.setBackgroundColor(color);
	}

}
