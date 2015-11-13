package com.nullcognition.widgetswithcontentproviders.stackEx;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nullcognition.widgetswithcontentproviders.R;
import com.nullcognition.widgetswithcontentproviders.contentProvider.TweetMeta;
import com.nullcognition.widgetswithcontentproviders.contentProvider.TweetsTable;

import java.util.ArrayList;
import java.util.List;

public class StackWidgetService extends RemoteViewsService{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent){
		return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
	}
}


class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

	private static int              count       = 0;
	private        List<WidgetItem> widgetItems = new ArrayList<WidgetItem>();
	private Context context;
	private int     mAppWidgetId;

	public StackRemoteViewsFactory(Context context, Intent intent){
		this.context = context;
		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	public void onCreate(){
		// In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
		// for example downloading or creating content etc, should be deferred to onDataSetChanged()
		// or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

		ContentResolver contentResolver = context.getContentResolver();
		Cursor cursor = contentResolver.query(
				TweetMeta.CONTENT_URI,
				new String[]{ TweetsTable.COLUMN_CONTENT },
				TweetsTable.COLUMN_CONTENT + " = ?",
				new String[]{ "x" },
				null
		);

		if(cursor != null && cursor.getCount() > 0){
			count = cursor.getCount();

			while(cursor.moveToNext()){
//			for(int i = 0; i < cursor.getCount(); i++){
				widgetItems.add(
						new WidgetItem(
								cursor.getString(
										cursor.getColumnIndexOrThrow(TweetsTable.COLUMN_CONTENT))));
			}
		}
//		for(int i = 0; i < count; i++){
//			widgetItems.add(new WidgetItem(i + "!"));
//		}
		// We sleep for 3 seconds here to show how the empty view appears in the interim.
		// The empty view is set in the StackWidgetProvider and should be a sibling of the
		// collection view.
		if(cursor != null){
			cursor.close();
		}
		try{
			Thread.sleep(3000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void onDestroy(){
		// In onDestroy() you should tear down anything that was setup for your data source,
		// eg. cursors, connections, etc.
		widgetItems.clear();
	}

	public int getCount(){
		return count;
	}

	public RemoteViews getViewAt(int position){
		// position will always range from 0 to getCount() - 1.
		// We construct a remote views item based on our widget item xml file, and set the
		// text based on the position.
		RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
		rv.setTextViewText(R.id.widget_item, widgetItems.get(position).text);
		// Next, we set a fill-intent which will be used to fill-in the pending intent template
		// which is set on the collection view in StackWidgetProvider.
		Bundle extras = new Bundle();
		extras.putInt(StackWidgetProvider.EXTRA_ITEM, position);
		Intent fillInIntent = new Intent();
		fillInIntent.putExtras(extras);
		rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
		// You can do heaving lifting in here, synchronously. For example, if you need to
		// process an image, fetch something from the network, etc., it is ok to do it here,
		// synchronously. A loading view will show up in lieu of the actual contents in the
		// interim.
		try{
			System.out.println("Loading view " + position);
			Thread.sleep(500);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		// Return the remote views object.
		return rv;
	}

	public RemoteViews getLoadingView(){
		// You can create a custom loading view (for instance when getViewAt() is slow.) If you
		// return null here, you will get the default loading view.
		return null;
	}

	public int getViewTypeCount(){
		return 1;
	}

	public long getItemId(int position){
		return position;
	}

	public boolean hasStableIds(){
		return true;
	}

	public void onDataSetChanged(){
		// This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
		// on the collection view corresponding to this factory. You can do heaving lifting in
		// here, synchronously. For example, if you need to process an image, fetch something
		// from the network, etc., it is ok to do it here, synchronously. The widget will remain
		// in its current state while work is being done here, so you don't need to worry about
		// locking up the widget.
	}
}
