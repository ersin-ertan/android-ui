package com.nullcognition.mosby.model;
// ersin 30/09/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.nullcognition.mosby.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class ReposAdapter extends SupportAnnotatedAdapter implements ReposAdapterBinder{

	@ViewType(
			layout = R.layout.list_repo,
			views = {
					@ViewField(id = R.id.avatar, type = ImageView.class, name = "avatar"),
					@ViewField(id = R.id.name, type = TextView.class, name = "name"),
					@ViewField(id = R.id.description, type = TextView.class, name = "description")
			}) public final int repo = 0;

	List<Repo> repos;

	Picasso picasso;

	@Inject public ReposAdapter(Context context, Picasso picasso){
		super(context);
		this.picasso = picasso;
	}

	@Override public int getItemCount(){
		return repos == null ? 0 : repos.size();
	}

	public List<Repo> getRepos(){
		return repos;
	}

	public void setRepos(List<Repo> repos){
		this.repos = repos;
	}

	@Override public void bindViewHolder(ReposAdapterHolders.RepoViewHolder vh, int position){
		Repo repo = repos.get(position);

		vh.name.setText(repo.getName());
		vh.description.setText(repo.getDescription());

		picasso.load(repo.getOwner().getAvatarUrl())
		       .placeholder(android.R.color.darker_gray)
		       .error(android.R.color.darker_gray)
		       .into(vh.avatar);
	}
}
