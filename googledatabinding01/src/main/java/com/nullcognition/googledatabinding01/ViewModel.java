package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ViewModel extends BaseObservable{

	private String price;
	private User   user;

	public ViewModel(final User u){ user = u; }

	public ViewModel(){}

	@Bindable public String getPrice(){
		if(user != null){return user.getPrice();}
		return null;
	}

	public void setPrice(String in){
		user.setPrice(in);
		price = user.getPrice();
		notifyPropertyChanged(BR.price);
	}

	public void updateUser(final User user){
		this.user = user;
		notifyPropertyChanged(BR.user);
	}
}
