package com.nullcognition.joebirchmvvm.data.remote;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.joebirchmvvm.model.Comment;
import com.nullcognition.joebirchmvvm.model.Post;
import com.nullcognition.joebirchmvvm.model.User;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface HackerNewsService{

	String ENDPOINT = "https://hacker-news.firebaseio.com/v0/";

	@GET("/topstories.json") Observable<List<Long>> getTopStories();

	/**
	 Return a list of a users post IDs.
	 */
	@GET("/user/{user}.json") Observable<User> getUser(@Path("user") String user);

	/**
	 Return story item.
	 */
	@GET("/item/{itemId}.json") Observable<Post> getStoryItem(@Path("itemId") String itemId);

	/**
	 Returns a comment item.
	 */
	@GET("/item/{itemId}.json") Observable<Comment> getCommentItem(@Path("itemId") String itemId);
}
