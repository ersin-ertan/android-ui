package com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel;

import android.view.LayoutInflater;

import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

public class ChapterViewModelRendererAdapterFactory{

	private final ChapterViewModelRendererBuilder rendererBuilder;
	private final LayoutInflater                  layoutInflater;

	@Inject public ChapterViewModelRendererAdapterFactory(ChapterViewModelRendererBuilder rendererBuilder,
	                                                      LayoutInflater layoutInflater){
		this.rendererBuilder = rendererBuilder;
		this.layoutInflater = layoutInflater;
	}

	public RendererAdapter<ChapterViewModel> getChapterRendererAdapter(
			final ChapterViewModelCollection chapterCollection){
		return new ChapterViewModelRendererAdapter(layoutInflater, rendererBuilder, chapterCollection);
	}
}
