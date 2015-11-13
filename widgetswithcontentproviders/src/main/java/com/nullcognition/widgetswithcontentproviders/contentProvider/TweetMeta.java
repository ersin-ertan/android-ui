package com.nullcognition.widgetswithcontentproviders.contentProvider;
// ersin 12/11/15 Copyright (c) 2015+ All rights reserved.


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.GetResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.PutResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

public class TweetMeta{

	public static final DefaultStorIOContentResolver getDefaultStorIOContentResolver(Context context){
		return DefaultStorIOContentResolver
				.builder()
				.contentResolver(context.getContentResolver())
				.addTypeMapping(Tweet.class, ContentResolverTypeMapping.<Tweet>builder()
				                                                       .putResolver(TweetMeta.PUT_RESOLVER)
				                                                       .getResolver(TweetMeta.GET_RESOLVER)
				                                                       .deleteResolver(TweetMeta.DELETE_RESOLVER)
				                                                       .build())
				.build();

	}

	@NonNull
	public static final Uri CONTENT_URI = Uri.parse("content://" + SampleContentProvider.AUTHORITY + "/tweets");

	@NonNull
	public static final PutResolver<Tweet> PUT_RESOLVER = new DefaultPutResolver<Tweet>(){
		@NonNull
		@Override
		protected InsertQuery mapToInsertQuery(@NonNull Tweet object){
			return InsertQuery.builder()
			                  .uri(CONTENT_URI)
			                  .build();
		}

		@NonNull
		@Override
		protected UpdateQuery mapToUpdateQuery(@NonNull Tweet tweet){
			return UpdateQuery.builder()
			                  .uri(CONTENT_URI)
			                  .where(TweetsTable.COLUMN_ID + " = ?")
			                  .whereArgs(tweet.id())
			                  .build();
		}

		@NonNull
		@Override
		protected ContentValues mapToContentValues(@NonNull Tweet object){
			ContentValues contentValues = new ContentValues();

			contentValues.put(TweetsTable.COLUMN_ID, object.id());
			contentValues.put(TweetsTable.COLUMN_CONTENT, object.content());

			return contentValues;
		}
	};

	@NonNull
	public static final GetResolver<Tweet> GET_RESOLVER = new DefaultGetResolver<Tweet>(){
		@NonNull
		@Override
		public Tweet mapFromCursor(@NonNull Cursor cursor){
			return Tweet.newTweet(
					cursor.getLong(cursor.getColumnIndexOrThrow(TweetsTable.COLUMN_ID)),
					cursor.getString(cursor.getColumnIndexOrThrow(TweetsTable.COLUMN_CONTENT))
			);
		}
	};

	@NonNull
	public static final DeleteResolver<Tweet> DELETE_RESOLVER = new DefaultDeleteResolver<Tweet>(){
		@NonNull
		@Override
		protected DeleteQuery mapToDeleteQuery(@NonNull Tweet tweet){
			return DeleteQuery.builder()
			                  .uri(CONTENT_URI)
			                  .where(TweetsTable.COLUMN_ID + " = ?")
			                  .whereArgs(tweet.id())
			                  .build();
		}
	};
}
