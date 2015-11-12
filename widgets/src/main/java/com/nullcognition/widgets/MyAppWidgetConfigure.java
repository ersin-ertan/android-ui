package com.nullcognition.widgets;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RemoteViews;

public class MyAppWidgetConfigure extends AppCompatActivity{

	int mAppWidgetId;
	private Object appWidgetId;

	@Override protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setResult(RESULT_CANCELED); // if back button is pressed, will cause the widget host
		// to cancel the widget placement

		getAppWidgetId();
		doConfiguration();

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
		// update widget with remote views layout
		RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.appwidget);
		appWidgetManager.updateAppWidget(mAppWidgetId, views);

		// call finishConfiguration when input data has ben received and updates have been made
	}

	private void doConfiguration(){
		// ...
	}

	public void getAppWidgetId(){
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null){
			mAppWidgetId = extras.getInt(
					AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
		}
	}

	private void finishConfiguration(){
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_OK, resultValue);
		finish();
	}
}
