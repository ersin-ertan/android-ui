package com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel;

import android.view.LayoutInflater;

import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;


public class ChapterViewModelRendererAdapter extends RendererAdapter<ChapterViewModel>{

	public ChapterViewModelRendererAdapter(LayoutInflater layoutInflater,
	                                       RendererBuilder rendererBuilder, AdapteeCollection<ChapterViewModel> collection){
		super(layoutInflater, rendererBuilder, collection);
	}

	@Override protected void updateRendererExtraValues(ChapterViewModel content,
	                                                   Renderer<ChapterViewModel> renderer, int position){
		super.updateRendererExtraValues(content, renderer, position);
		((ChapterViewModelRenderer) renderer).setPosition(position);
	}
}
