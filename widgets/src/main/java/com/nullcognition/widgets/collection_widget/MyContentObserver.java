package com.nullcognition.widgets.collection_widget;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.database.ContentObserver;
import android.os.Handler;

import com.nullcognition.widgets.R;

public class MyContentObserver extends ContentObserver{



	private AppWidgetManager appWidgetManager;
	private ComponentName    componentName;

	MyContentObserver(AppWidgetManager mgr, ComponentName cn, Handler h){
		super(h);
		appWidgetManager = mgr;
		componentName = cn;
	}

	@Override
	public void onChange(boolean selfChange){
		// notify widget that the collection view needs to be updated, the factory's onDataSetChanged() will be called which will re-query the
		// cursor for the new data.
		appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.listview_widget); // for use of collections of widgets
		// see MyAppProviderCollection
	}
}
