package com.nullcognition.googledatabinding01.view.viewmodel;
// ersin 23/10/15 Copyright (c) 2015+ All rights reserved.

import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;
import com.nullcognition.googledatabinding01.BR;

@ParcelablePlease
public class ConcreteViewModel extends ViewModelBase{ // implements Parcelable in base class

	String                      firstName;
	String                      lastName;
	ObservableArrayList<String> observableArrayList;

	@Bindable
	public String getFirstName(){
		return this.firstName;
	}

	@Bindable
	public String getLastName(){
		return this.lastName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
		notifyPropertyChanged(BR.firstName);
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
		notifyPropertyChanged(BR.lastName);
	}

	public void setObservableArrayList(ObservableArrayList<String> oal){
		observableArrayList = oal;

		//		notifyPropertyChanged(BR.concreteViewModel);
		// verify that by updating the concrete view model, if observablearraylist is assigned a new reference
		// that the list will update
	}

	public void addObservableItem(String s){
		observableArrayList.add(s);
	}


	// Parcelable Please

	@Override public int describeContents(){ return 0; }

	@Override public void writeToParcel(Parcel dest, int flags){com.nullcognition.googledatabinding01.view.viewmodel.ConcreteViewModelParcelablePlease.writeToParcel(this, dest, flags);}

	public static final Creator<ConcreteViewModel> CREATOR = new Creator<ConcreteViewModel>(){
		public ConcreteViewModel createFromParcel(Parcel source){
			ConcreteViewModel target = new ConcreteViewModel();
			com.nullcognition.googledatabinding01.view.viewmodel.ConcreteViewModelParcelablePlease.readFromParcel(target, source);
			return target;
		}

		public ConcreteViewModel[] newArray(int size){return new ConcreteViewModel[size];}
	};
}
