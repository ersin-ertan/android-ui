package com.nullcognition.joebirchmvvm.view.activity;
// ersin 03/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nullcognition.joebirchmvvm.HackerNewsApplication;
import com.nullcognition.joebirchmvvm.R;
import com.nullcognition.joebirchmvvm.data.DataManager;
import com.nullcognition.joebirchmvvm.model.Comment;
import com.nullcognition.joebirchmvvm.model.Post;
import com.nullcognition.joebirchmvvm.util.DataUtils;
import com.nullcognition.joebirchmvvm.util.DialogFactory;
import com.nullcognition.joebirchmvvm.view.adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class CommentsActivity extends BaseActivity{

	@Bind(R.id.progress_indicator) LinearLayout progressBar;


	@Bind(R.id.layout_offline)    LinearLayout   offlineLayout;
	@Bind(R.id.recycler_comments) RecyclerView   commentsRecycler;
	@Bind(R.id.layout_comments)   RelativeLayout commentsLayout;
	@Bind(R.id.text_no_comments)  TextView       noCommentsText;
	@Bind(R.id.toolbar)           Toolbar        toolbar;

	public static final String EXTRA_POST =
			"com.nullcognition.mvvm_hackernews.ui.activity.CommentsActivity.EXTRA_POST";

	private ArrayList<Comment>    comments;
	private CommentAdapter        commentAdapter;
	private DataManager           dataManager;
	private CompositeSubscription subscription;
	private Post                  post;

	public static Intent getStartIntent(Context context, Post post){
		Intent intent = new Intent(context, CommentsActivity.class);
		intent.putExtra(EXTRA_POST, post);
		return intent;
	}

	@Override protected void onCreate(final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comments);
		ButterKnife.bind(this);
		post = getIntent().getParcelableExtra(EXTRA_POST);
		if(post == null){
			throw new IllegalArgumentException("commentsActivity requires a post object");
		}

		dataManager = HackerNewsApplication.get(this).getApplicationComponent().dataManager();
		subscription = new CompositeSubscription();
		comments = new ArrayList<>();
		setUpToolbar();
		setupRecyclerView();
		loadStoriesIfNetworkConnected();
	}

	@Override protected void onDestroy(){
		subscription.unsubscribe();
		super.onDestroy();
	}

	@OnClick(R.id.button_try_again) void onTryAgainClick(final View view){
		loadStoriesIfNetworkConnected();
	}

	private void setUpToolbar(){
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if(actionBar != null){
			String title = post.title;
			if(title != null){
				actionBar.setTitle(title);
			}
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupRecyclerView(){
		commentsRecycler.setLayoutManager(new LinearLayoutManager(this));
		commentAdapter = new CommentAdapter(this, post, comments);
		commentsRecycler.setAdapter(commentAdapter);
	}

	private void loadStoriesIfNetworkConnected(){
		if(DataUtils.isNetworkAvailable(this)){
			showHideOfflineLayout(false);
			getStoryComments(post.kids);
		}
		else{
			showHideOfflineLayout(true);
		}
	}

	private void getStoryComments(List<Long> commentsIds){
		if(commentsIds != null && !commentsIds.isEmpty()){
			subscription.add(dataManager.getPostComments(commentsIds, 0)
			                            .observeOn(AndroidSchedulers.mainThread())
			                            .subscribeOn(dataManager.getSubscribeScheduler())
			                            .subscribe(new Subscriber<Comment>(){
				                            @Override public void onCompleted(){
					                            progressBar.setVisibility(View.GONE);
				                            }
				                            @Override public void onError(final Throwable e){
					                            progressBar.setVisibility(View.GONE);
					                            Timber.e("There was an error retrieving the comments " + e);
					                            DialogFactory.createSimpleOkErrorDialog(
							                            CommentsActivity.this,
							                            getString(R.string.error_comments)
					                            ).show();
				                            }

				                            @Override
				                            public void onNext(Comment comment){
					                            addCommentViews(comment);
				                            }
			                            }));
		}
		else{
			progressBar.setVisibility(View.GONE);
			commentsRecycler.setVisibility(View.GONE);
			noCommentsText.setVisibility(View.VISIBLE);
		}
	}

	private void addCommentViews(Comment comment){
		comments.add(comment);
		comments.addAll(comment.comments);
		commentAdapter.notifyDataSetChanged();
	}

	private void showHideOfflineLayout(boolean isOffline){
		offlineLayout.setVisibility(isOffline ? View.VISIBLE : View.GONE);
		commentsRecycler.setVisibility(isOffline ? View.GONE : View.VISIBLE);
		progressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
	}
}
