package com.nullcognition.joebirchmvvm.data;
// ersin 02/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;

import com.nullcognition.joebirchmvvm.HackerNewsApplication;
import com.nullcognition.joebirchmvvm.data.remote.HackerNewsService;
import com.nullcognition.joebirchmvvm.injection.DataManagerComponent;
import com.nullcognition.joebirchmvvm.injection.DataManagerModule;
import com.nullcognition.joebirchmvvm.model.Comment;
import com.nullcognition.joebirchmvvm.model.Post;
import com.nullcognition.joebirchmvvm.model.User;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class DataManager{

	@Inject protected HackerNewsService hackerNewsService;
	@Inject protected Scheduler         subscribeScheduler;

	private DataManagerComponent dataManagerComponent;

	public DataManagerComponent getDataManagerComponent(){
		return dataManagerComponent;
	}

	public void setDataManagerComponent(final DataManagerComponent dataManagerComponent){
		this.dataManagerComponent = dataManagerComponent;
	}

	public void releaseDataManagerComponent(){ dataManagerComponent = null; }

	public DataManager(Context context){ injectDepdendencies(context); }

	protected void injectDepdendencies(Context context){

		if(dataManagerComponent == null){
			dataManagerComponent = HackerNewsApplication.get(context)
			                                            .getApplicationComponent()
			                                            .plus(new DataManagerModule());
		}
		dataManagerComponent.inject(this);
	}

	public Scheduler getSubscribeScheduler(){ return subscribeScheduler; }

	public Observable<Post> getTopStories(){
		return hackerNewsService.getTopStories()
		                        .concatMap(new Func1<List<Long>, Observable<? extends Post>>(){
			                        @Override
			                        public Observable<? extends Post> call(List<Long> longs){
				                        return getPostsFromIds(longs);
			                        }
		                        });
	}

	public Observable<Post> getUserPosts(String user){
		return hackerNewsService.getUser(user)
		                        .concatMap(new Func1<User, Observable<? extends Post>>(){
			                        @Override
			                        public Observable<? extends Post> call(User user){
				                        return getPostsFromIds(user.submitted);
			                        }
		                        });
	}

	public Observable<Post> getPostsFromIds(List<Long> storyIds){
		return Observable.from(storyIds)
		                 .concatMap(new Func1<Long, Observable<Post>>(){
			                 @Override
			                 public Observable<Post> call(Long aLong){
				                 return hackerNewsService.getStoryItem(String.valueOf(aLong));
			                 }
		                 }).flatMap(new Func1<Post, Observable<Post>>(){
					@Override
					public Observable<Post> call(Post post){
						return post.title != null ? Observable.just(post) : Observable.<Post>empty();
					}
				});
	}

	public Observable<Comment> getPostComments(final List<Long> commentIds, final int depth){
		return Observable.from(commentIds)
		                 .concatMap(new Func1<Long, Observable<Comment>>(){
			                 @Override
			                 public Observable<Comment> call(Long aLong){
				                 return hackerNewsService.getCommentItem(String.valueOf(aLong));
			                 }
		                 }).concatMap(new Func1<Comment, Observable<Comment>>(){
					@Override
					public Observable<Comment> call(Comment comment){
						comment.depth = depth;
						if(comment.kids == null || comment.kids.isEmpty()){
							return Observable.just(comment);
						}
						else{
							return Observable.just(comment)
							                 .mergeWith(getPostComments(comment.kids, depth + 1));
						}
					}
				}).filter(new Func1<Comment, Boolean>(){
					@Override
					public Boolean call(Comment comment){
						return (comment.by != null && !comment.by.trim().isEmpty()
								&& comment.text != null && !comment.text.trim().isEmpty());
					}
				});
	}
}
