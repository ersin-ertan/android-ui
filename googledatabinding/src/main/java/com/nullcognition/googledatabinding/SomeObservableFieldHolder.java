package com.nullcognition.googledatabinding;
// ersin 17/09/15 Copyright (c) 2015+ All rights reserved.


import android.databinding.ObservableField;
import android.databinding.ObservableInt;

public class SomeObservableFieldHolder{

	public final ObservableField<String> lastName = new ObservableField<>();
	public final ObservableInt           age      = new ObservableInt();

	{
		age.set(1);
		// was used to check if the problem was with the constructor not initializing the value, thus the
		// binding framework might have been dealing with nulls, but that is not the case
	}

	public static String intToString(ObservableInt i){ return String.valueOf(i.get());}

}
