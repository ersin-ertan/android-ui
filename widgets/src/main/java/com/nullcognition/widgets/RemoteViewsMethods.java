package com.nullcognition.widgets;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.PendingIntent;
import android.content.Intent;
import android.widget.RemoteViews;

public class RemoteViewsMethods{

	RemoteViews r;

	{
		PendingIntent pendingIntent = null; //		= PendingIntent.getActivity();
		r.setPendingIntentTemplate(R.id.btn_widget,  pendingIntent); // set pending intent on individual views are costly, thus use this for collection based
		// widgets, to differentiate the widges use

		r.setOnClickFillInIntent(R.id.btn_widget, new Intent()); // views with the fillInIntent will get their pending intent overwritten, to which the Intent.fillIn
		// method will then populate the views with the correct data

		// fillIn - Copy the contents of other in to this object, but only where fields are not defined by this object.
		// For purposes of a field being defined, the following pieces of data in the Intent are considered to be separate fields:


	}
}
