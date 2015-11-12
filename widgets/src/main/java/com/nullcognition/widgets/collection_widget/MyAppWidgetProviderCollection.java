package com.nullcognition.widgets.collection_widget;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.nullcognition.widgets.MyRemoteViewsService;
import com.nullcognition.widgets.R;

import java.util.Random;

public class MyAppWidgetProviderCollection extends AppWidgetProvider{

	public static final  String REFRESH      = "refresh";
	public static final  String CLICK_ACTION = "clickaction";
	private static final String EXTRA_DAY_ID = "day";

	private static HandlerThread     workerThread;
	private static Handler           workerQueue;
	private static MyContentObserver dataObserver;

	public MyAppWidgetProviderCollection(){
		workerThread = new HandlerThread("mawpc-worker");
		workerThread.start();
		workerQueue = new Handler(workerThread.getLooper());
	}

	@Override public void onEnabled(final Context context){
		super.onEnabled(context);

		final ContentResolver cr = context.getContentResolver();
		if(dataObserver == null){
			final AppWidgetManager mgr = AppWidgetManager.getInstance(context);
			final ComponentName cn = new ComponentName(context, MyAppWidgetProviderCollection.class);

			dataObserver = new MyContentObserver(mgr, cn, workerQueue);
			cr.registerContentObserver(MyContentProvider.CONTENT_URI, true, dataObserver);
		}
	}

	@Override public void onReceive(final Context context, final Intent intent){
		super.onReceive(context, intent);

		final String action = intent.getAction();
		switch(action){
			case REFRESH:{
				final Context context1 = context;
				workerQueue.removeMessages(0);
				workerQueue.post(new Runnable(){
					@Override public void run(){
						final ContentResolver r     = context1.getContentResolver();
						final Cursor          c     = r.query(MyContentProvider.CONTENT_URI, null, null, null, null);
						final int             count = c.getCount();

						// disable data changed observer temp, each update will trigger onChange in
						// data observer, not what we need to see

						r.unregisterContentObserver(dataObserver);

						for(int i = 0; i < count; ++i){
							final Uri uri = ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, i);
							final ContentValues values = new ContentValues();
							values.put(MyContentProvider.COLUMN_INT,
									new Random().nextInt());
							r.update(uri, values, null, null);
						}

						r.registerContentObserver(MyContentProvider.CONTENT_URI, true, dataObserver);

						final AppWidgetManager mgr = AppWidgetManager.getInstance(context);
						final ComponentName    cn  = new ComponentName(context, MyContentProvider.class);

						mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.listview_widget);
					}
				});

				final int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
						AppWidgetManager.INVALID_APPWIDGET_ID);
				break;
			}
			case CLICK_ACTION:{
				final int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
				final String day = intent.getStringExtra(EXTRA_DAY_ID);
				final String formatStr = context.getResources().getString(R.string.toast_format_string);
				Toast.makeText(context, String.format(formatStr, day), Toast.LENGTH_SHORT).show();
			}
		}
		super.onReceive(context, intent);
	}

	private RemoteViews buildLayout(Context context, int appWidgetId, boolean largeLayout){
		RemoteViews rv = null;
		if(largeLayout){
			// Specify the service to provide data for the collection widget.  Note that we need to
			// embed the appWidgetId via the data otherwise it will be ignored.

			final Intent intent = new Intent(context, MyRemoteViewsService.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
			rv = new RemoteViews(context.getPackageName(), R.layout.listview_widget);
			rv.setRemoteAdapter(appWidgetId, R.id.listview_widget, intent);

			// Set the empty view to be displayed if the collection is empty.  It must be a sibling
			// view of the collection view.

//			rv.setEmptyView(R.id.listview_widget, R.id.empty_view); // uncomment this

			// Bind a click listener template for the contents of the weather list.  Note that we
			// need to update the intent's data if we set an extra, since the extras will be
			// ignored otherwise.

			final Intent onClickIntent = new Intent(context, MyContentProvider.class);
			onClickIntent.setAction(CLICK_ACTION);
			onClickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			onClickIntent.setData(Uri.parse(onClickIntent.toUri(Intent.URI_INTENT_SCHEME)));
			final PendingIntent onClickPendingIntent = PendingIntent.getBroadcast(context, 0, onClickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setPendingIntentTemplate(R.id.listview_widget, onClickPendingIntent);

			// Bind the click intent for the refresh button on the widget

			final Intent refreshIntent = new Intent(context, MyContentProvider.class);
			refreshIntent.setAction(REFRESH);
			final PendingIntent refreshPendingIntent = PendingIntent.getBroadcast(context, 0,
					refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			rv.setOnClickPendingIntent(R.id.btn_widget, refreshPendingIntent); // button is taken from the other layout for example sake

			// Restore the minimal header

//			rv.setTextViewText(R.id.city_name, context.getString(R.string.city_name)); // uncomment this
		}
		else{// else statement to create the small layout

//			rv = new RemoteViews(context.getPackageName(), R.layout.widget_layout_small);
//
//			// Update the header to reflect the weather for "today"
//
//			Cursor c = context.getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
//			if(c.moveToPosition(0)){
//				int tempColIndex = c.getColumnIndex(MyContentProvider.COLUMN_INT);
//				int temp = c.getInt(tempColIndex);
//				String formatStr = context.getResources().getString(R.string.header_format_string);
//				String header = String.format(formatStr, temp,
//						context.getString(R.string.city_name));
//				rv.setTextViewText(R.id.city_name, header);
//			}
//			c.close();
		}
		return rv;
	}

	boolean isLargeLayout = false; // added to complete example

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		// Update each of the widgets with the remote adapter
		for(int i = 0; i < appWidgetIds.length; ++i){
			RemoteViews layout = buildLayout(context, appWidgetIds[i], isLargeLayout);
			appWidgetManager.updateAppWidget(appWidgetIds[i], layout);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
	                                      int appWidgetId, Bundle newOptions){
		int         minWidth  = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH);
		int         maxWidth  = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
		int         minHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);
		int         maxHeight = newOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
		RemoteViews layout;
		if(minHeight < 100){
			isLargeLayout = false;
		}
		else{
			isLargeLayout = true;
		}
		layout = buildLayout(context, appWidgetId, isLargeLayout);
		appWidgetManager.updateAppWidget(appWidgetId, layout);
	}

}
