package com.nullcognition.widgets;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider{ // extends BroadcastReceiver
	// receiving update, delete, enable, disable
	// not guaranteed to keep the process running after callbacks return, perhaps starting a service in onUpdate
	// to prevent ANR dialog if doing io/computation

	private static final int    REQUEST_CODE = 0;
	public static final  String EXTRA_ITEM   = "extra";

	public MyAppWidgetProvider(){
		super();
	}

	@Override public void onReceive(final Context context, final Intent intent){
		super.onReceive(context, intent);
		// called prior to any other callback is called, usually not implemented
	}

	@Override public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		// update based on the update millis in the xml, set up should also be done here, like define event handlers for Views and start a temporary Service
		// as is called when used adds the widget, if a configuration activity is used, then this is only to be used as the onUpdate and is not called on app creation

		exampleLaunchActivityOnClick(context, appWidgetManager, appWidgetIds);
	}

	private void exampleLaunchActivityOnClick(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds){
		final int n = appWidgetIds.length;

		for(int i = 0; i < n; ++i){
			int appWidgetId = appWidgetIds[i];


//			if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN){
			//			Bundle testBundle = new Bundle();
			// 			testBundle.putInt("int", 1);
//				PendingIntent piApi16 = PendingIntent.getActivity(context, REQUEST_CODE, new Intent(context, MainActivity.class), 0, testBundle);
//			}

			PendingIntent pendingIntent = PendingIntent.getActivity(context, REQUEST_CODE, new Intent(context, MainActivity.class), 0);

			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget);
			remoteViews.setOnClickPendingIntent(R.id.btn_widget, pendingIntent);

			appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
		}
	}

	@Override public void onAppWidgetOptionsChanged(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId, final Bundle newOptions){
		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

		// called when app is first placed and when resized, used to show/hide values based on widgets size range via getAppWidgetOptions() api 16, 4.1 returning a bundle of
		// OPTION_APPWIDGET_MIN_WIDTH /MAX_HEIGHT values in dp
	}

	@Override public void onDeleted(final Context context, final int[] appWidgetIds){
		super.onDeleted(context, appWidgetIds);
		// when the widget is deleted from the host
	}

	@Override public void onEnabled(final Context context){
		super.onEnabled(context);
		// on creation of only the first instance of the widget
	}

	@Override public void onDisabled(final Context context){
		super.onDisabled(context);
		// on destruction of the last instance of the widget
	}

	@Override public void onRestored(final Context context, final int[] oldWidgetIds, final int[] newWidgetIds){
		super.onRestored(context, oldWidgetIds, newWidgetIds); // api 21
		// ACTION_APPWIDGET_RESTORED broadcast when instances of this AppWidget provider have been restored from backup.
		// If your provider maintains any persistent data about its widget instances,
		// override this method to remap the old AppWidgetIds to the new values and update any other app state that may be relevant
	}
}
