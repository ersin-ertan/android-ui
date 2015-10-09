package com.nullcognition.effectiveandroidui.di.presenter;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.renderer.chapter.ChapterRenderer;
import com.nullcognition.effectiveandroidui.ui.renderer.chapter.ChapterRendererBuilder;
import com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel.ChapterViewModelRenderer;
import com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel.ChapterViewModelRendererBuilder;
import com.nullcognition.effectiveandroidui.ui.renderer.tvshow.TvShowRenderer;
import com.nullcognition.effectiveandroidui.ui.renderer.tvshow.TvShowRendererBuilder;
import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.pedrogomez.renderers.Renderer;

import java.util.LinkedList;

import dagger.Module;
import dagger.Provides;

@Module public class TvShowUIModule{

	@Provides TvShowRendererBuilder provideTvShowRendererBuilder(TvShowRenderer tvShowRenderer){
		LinkedList<Renderer<TvShow>> renderers = new LinkedList<Renderer<TvShow>>();
		renderers.add(tvShowRenderer);
		return new TvShowRendererBuilder(renderers);
	}

	@Provides ChapterRendererBuilder provideChapterRendererBuilder(ChapterRenderer chapterRenderer){
		LinkedList<Renderer<Chapter>> renderers = new LinkedList<Renderer<Chapter>>();
		renderers.add(chapterRenderer);
		return new ChapterRendererBuilder(renderers);
	}

	@Provides ChapterViewModelRendererBuilder provideChapterViewModelRendererBuilder(ChapterViewModelRenderer chapterRenderer){
		LinkedList<Renderer<ChapterViewModel>> renderers = new LinkedList<Renderer<ChapterViewModel>>();
		renderers.add(chapterRenderer);
		return new ChapterViewModelRendererBuilder(renderers);
	}
}
