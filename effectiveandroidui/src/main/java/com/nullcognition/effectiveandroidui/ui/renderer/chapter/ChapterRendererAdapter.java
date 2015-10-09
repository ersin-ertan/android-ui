package com.nullcognition.effectiveandroidui.ui.renderer.chapter;

import android.view.LayoutInflater;

import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

public class ChapterRendererAdapter extends RendererAdapter<Chapter>{

	public ChapterRendererAdapter(LayoutInflater layoutInflater, RendererBuilder rendererBuilder,
	                              AdapteeCollection<Chapter> collection){
		super(layoutInflater, rendererBuilder, collection);
	}

	@Override protected void updateRendererExtraValues(Chapter content, Renderer<Chapter> renderer,
	                                                   int position){
		super.updateRendererExtraValues(content, renderer, position);
		((ChapterRenderer) renderer).setPosition(position);
	}
}
