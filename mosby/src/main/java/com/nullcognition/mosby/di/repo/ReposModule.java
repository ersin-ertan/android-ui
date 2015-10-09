package com.nullcognition.mosby.di.repo;
// ersin 09/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;

import com.nullcognition.mosby.repos.ReposAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module public class ReposModule{

	public ReposModule(){}

	@Provides ReposAdapter provideAdapter(Context context, Picasso picasso){ return new ReposAdapter(context, picasso); }
}
