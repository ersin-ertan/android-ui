package com.nullcognition.widgets.collection_widget;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nullcognition.widgets.R;

public class MyRemoteViewServiceCollection extends RemoteViewsService{

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent){
		return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
	}
}


class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

	private Context mContext;
	private Cursor  mCursor;
	private int     mAppWidgetId;

	public StackRemoteViewsFactory(Context context, Intent intent){
		mContext = context;
		mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
	}

	public void onCreate(){
		// Since we reload the cursor in onDataSetChanged() which gets called immediately after
		// onCreate(), we do nothing here.
	}

	public void onDestroy(){
		if(mCursor != null){
			mCursor.close();
		}
	}

	public int getCount(){
		return mCursor.getCount();
	}

	public RemoteViews getViewAt(int position){
		// Get the data for this position from the content provider
		String day  = "Unknown Day";
		int    temp = 0;
		if(mCursor.moveToPosition(position)){
			final int dayColIndex = mCursor.getColumnIndex(MyContentProvider.COLUMN_INT);
			final int tempColIndex = mCursor.getColumnIndex(
					MyContentProvider.COLUMN_INT); // MyContentProvider.Column_int used twice in repetition for example purposes
			day = mCursor.getString(dayColIndex);
			temp = mCursor.getInt(tempColIndex);
		}
		// Return a proper item with the proper day and temperature
//		final String formatStr = mContext.getResources().getString(R.string.item_format_string);
		final int   itemId = R.layout.listview_widget;
		RemoteViews rv     = new RemoteViews(mContext.getPackageName(), itemId);
//		rv.setTextViewText(R.id.widget_item, String.format(formatStr, temp, day));
//		 Set the click intent so that we can handle it and show a toast message
//		final Intent fillInIntent = new Intent();
//		final Bundle extras       = new Bundle();
//		extras.putString(WeatherWidgetProvider.EXTRA_DAY_ID, day);
//		fillInIntent.putExtras(extras);
//		rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
		return rv;
	}

	public RemoteViews getLoadingView(){
		// We aren't going to return a default loading view in this sample
		return null;
	}

	public int getViewTypeCount(){
		// Technically, we have two types of views (the dark and light background views)
		return 2;
	}

	public long getItemId(int position){
		return position;
	}

	public boolean hasStableIds(){
		return true;
	}

	public void onDataSetChanged(){
		// Refresh the cursor
		if(mCursor != null){
			mCursor.close();
		}
//		mCursor = mContext.getContentResolver().query(WeatherDataProvider.CONTENT_URI, null, null, null, null);
	}
}
