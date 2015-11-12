package com.nullcognition.widgets;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

public class MyRemoteViewsService extends RemoteViewsService{

	// display collections that are backed by remote data, such as from a content provider

	// collection views: ListView, GridView, StackView, AdapterViewFlipper(uses a view animator flip through / shows one view at a time)

	// uses an Adapter to bind data to individual views, RemoteViewsFactory is a thin adapter wrapper with framework hooks,
	// on item request creates and returns item for collection as RemoteViews object

	// implement both RemoteViewsService and RemoteViewsFactory

	// this service allows remote adapters to request remoteviews, factory is the interface,

	@Override public RemoteViewsFactory onGetViewFactory(final Intent intent){
		return new MyRemoteViewsFactory(this.getApplicationContext(), intent);
	}
}


class WidgetItem{

	final public String text;

	WidgetItem(final String text){this.text = text;}
}


class MyRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

	private static final int              count          = 10;
	private              List<WidgetItem> widgetItemList = new ArrayList<WidgetItem>();
	private Context context;
	private int     appWidgetId;

	public MyRemoteViewsFactory(final Context applicationContext, final Intent intent){
		context = applicationContext;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	@Override public void onCreate(){
		for(int i = 0; i < count; i++){
			widgetItemList.add(new WidgetItem(i + "!"));
		}
	}

	@Override public void onDataSetChanged(){
//		triggered when calling AppWidgetManager notifyAppWidgetViewDataChanged
	}

	@Override public void onDestroy(){
		widgetItemList.clear();
	}

	@Override public int getCount(){
		return widgetItemList.size();
	}

	@Override public RemoteViews getViewAt(final int position){
		// position will always range from 0 to getCount() - 1.
		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		rv.setTextViewText(R.id.txt_widget, widgetItemList.get(position).text);

		Bundle extras = new Bundle();
		extras.putInt(MyAppWidgetProvider.EXTRA_ITEM, position);
		Intent fillInIntent = new Intent();
		fillInIntent.putExtras(extras);
		rv.setOnClickFillInIntent(R.id.appWidget, fillInIntent);

		return rv;
	}

	@Override public RemoteViews getLoadingView(){
		return null;
	}

	@Override public int getViewTypeCount(){
		return 1;
	}

	@Override public long getItemId(final int position){
		return position;
	}

	@Override public boolean hasStableIds(){
		return true;
	}


}
