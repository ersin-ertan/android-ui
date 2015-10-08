package com.nullcognition.effectiveandroidui.di.executor;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.executor.Executor;
import com.nullcognition.effectiveandroidui.executor.MainThread;
import com.nullcognition.effectiveandroidui.executor.MainThreadImpl;
import com.nullcognition.effectiveandroidui.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module public class ExecutorModule{

	@Singleton
	@Provides Executor provideExecutor(ThreadExecutor threadExcutor){ return new ThreadExecutor();}

	@Singleton
	@Provides MainThread provideMainThread(MainThreadImpl mainThread){ return new MainThreadImpl(); }
}
