package com.nullcognition.widgetswithcontentproviders.contentProvider;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.queries.Query;

public class TweetsTable{

	@NonNull
	public static final String TABLE = "tweets";

	@NonNull
	public static final String COLUMN_ID = "_id";

	@NonNull
	public static final String COLUMN_CONTENT = "content";

	// Yep, with StorIO you can safely store queries as objects and reuse them, they are immutable
	@NonNull
	public static final Query QUERY_ALL = Query.builder().uri(TweetMeta.CONTENT_URI).build();

	// This is just class with Meta Data, we don't need instances
	private TweetsTable(){
		throw new IllegalStateException("No instances please");
	}

	// Better than static final field -> allows VM to unload useless String
	// Because you need this string only once per application life on the device
	@NonNull
	public static String getCreateTableQuery(){
		return "CREATE TABLE " + TABLE + "("
				+ COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
				+ COLUMN_CONTENT + " TEXT NOT NULL"
				+ ");";
	}
}
