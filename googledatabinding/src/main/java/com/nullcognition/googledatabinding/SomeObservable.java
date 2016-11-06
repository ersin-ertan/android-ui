package com.nullcognition.googledatabinding;
// ersin 17/09/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SomeObservable extends BaseObservable{

	private String firstName;
	private String lastName;

	@Bindable public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
		//notifyPropertyChanged(BR.firstName);
	}

	@Bindable public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
		//notifyPropertyChanged(BR.lastName);
	}
}
