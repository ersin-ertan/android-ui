package com.nullcognition.effectiveandroidui.di.executor;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.executor.Executor;
import com.nullcognition.effectiveandroidui.executor.MainThread;
import com.nullcognition.effectiveandroidui.executor.MainThreadImpl;
import com.nullcognition.effectiveandroidui.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


// the threadexecutor and meainthreadimpl are required as dependencies and will get created due to the
// @Inject annotation, one would assume that this is for flexibility by allowing the provided type to be
// changed easily, and as of now there is only one dependency for the object to fulfil thus @Inject works
// and the is provided for this instance

@Module public class ExecutorModule{

	@Singleton
	@Provides Executor provideExecutor(ThreadExecutor threadExecutor){ return threadExecutor;}

	@Singleton
	@Provides MainThread provideMainThread(MainThreadImpl mainThread){ return mainThread; }
}
