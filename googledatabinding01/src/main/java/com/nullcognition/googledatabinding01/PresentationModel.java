package com.nullcognition.googledatabinding01;
// ersin 21/10/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

// if  binding is done the Presentation Model,
// you add a dependency to the view in the Presentation Model
// which means more coupling and stubbing.


// this is the subscribing class, to which controller will call operations which will start their own threads
// and return results to this


@ParcelablePlease
public class PresentationModel implements Parcelable{

//	User user = new User("user text");


	public ObservableField<String> editText = new ObservableField<>(null);
	// must be public, cannot be final
	// will default as null and thus the @string/resource directed by the layout file
	public ObservableField<String> text     = new ObservableField<>(null);

	// based on this value, the view will be/not be visible, propagated to view
	public ObservableBoolean isShowing = new ObservableBoolean(false);

	@Override public int describeContents(){ return 0; }

	@Override public void writeToParcel(Parcel dest, int flags){PresentationModelParcelablePlease.writeToParcel(this, dest, flags);}

	public static final Creator<PresentationModel> CREATOR = new Creator<PresentationModel>(){
		public PresentationModel createFromParcel(Parcel source){
			PresentationModel target = new PresentationModel();
			PresentationModelParcelablePlease.readFromParcel(target, source);
			return target;
		}

		public PresentationModel[] newArray(int size){return new PresentationModel[size];}
	};
}
