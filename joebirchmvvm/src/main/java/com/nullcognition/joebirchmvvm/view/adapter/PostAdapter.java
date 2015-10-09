package com.nullcognition.joebirchmvvm.view.adapter;
// ersin 03/10/15 Copyright (c) 2015+ All rights reserved.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nullcognition.joebirchmvvm.R;
import com.nullcognition.joebirchmvvm.databinding.ItemPostBinding;
import com.nullcognition.joebirchmvvm.model.Post;
import com.nullcognition.joebirchmvvm.viewModel.PostViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.BindingHolder>{

	private List<Post> mPosts;
	private Context    mContext;
	private boolean    mIsUserPosts;

	public PostAdapter(Context context, boolean isUserPosts){
		mContext = context;
		mIsUserPosts = isUserPosts;
		mPosts = new ArrayList<>();
	}

	@Override
	public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType){
		ItemPostBinding postBinding = DataBindingUtil.inflate(
				LayoutInflater.from(parent.getContext()),
				R.layout.item_post,
				parent,
				false);
		return new BindingHolder(postBinding);
	}

	@Override
	public void onBindViewHolder(BindingHolder holder, int position){
		ItemPostBinding postBinding = holder.binding;
		postBinding.setViewModel(new PostViewModel(mContext, mPosts.get(position), mIsUserPosts));
	}

	@Override
	public int getItemCount(){
		return mPosts.size();
	}

	public void setItems(List<Post> posts){
		mPosts = posts;
		notifyDataSetChanged();
	}

	public void addItem(Post post){
		if(!mPosts.contains(post)){
			mPosts.add(post);
			notifyItemInserted(mPosts.size() - 1);
		}
		else{
			mPosts.set(mPosts.indexOf(post), post);
			notifyItemChanged(mPosts.indexOf(post));
		}
	}

	public static class BindingHolder extends RecyclerView.ViewHolder{

		private ItemPostBinding binding;

		public BindingHolder(ItemPostBinding binding){
			super(binding.cardView);
			this.binding = binding;
		}
	}

}
