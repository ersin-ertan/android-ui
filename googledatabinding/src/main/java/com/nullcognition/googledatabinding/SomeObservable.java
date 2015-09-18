package com.nullcognition.googledatabinding;
// ersin 17/09/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SomeObservable extends BaseObservable{

	private String firstName;
	@Bindable public String getFirstName(){return this.firstName;}

	private String lastName;
	@Bindable public String getLastName(){return this.lastName;}

	public void setFirstName(String firstName){
		this.firstName = firstName;
		notifyPropertyChanged(BR.firstName);
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
		notifyPropertyChanged(BR.lastName);
	}
}
