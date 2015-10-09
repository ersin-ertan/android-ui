package com.nullcognition.practice00;
// ersin 01/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

@ParcelablePlease
public class Retainable extends BaseObservable implements Parcelable{

	int state = 0;

	@Bindable public int getState(){
		return state;
	}

	public void setState(int s){
		state = s;
		notifyPropertyChanged(BR.state);
	}

	@Override public int describeContents(){ return 0; }
	@Override public void writeToParcel(Parcel dest, int flags){RetainableParcelablePlease.writeToParcel(this, dest, flags);}
	public static final Creator<Retainable> CREATOR = new Creator<Retainable>(){
		public Retainable createFromParcel(Parcel source){
			Retainable target = new Retainable();
			RetainableParcelablePlease.readFromParcel(target, source);
			return target;
		}
		public Retainable[] newArray(int size){return new Retainable[size];}
	};
}
