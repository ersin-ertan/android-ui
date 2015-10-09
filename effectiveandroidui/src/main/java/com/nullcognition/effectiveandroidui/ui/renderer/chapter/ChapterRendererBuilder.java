package com.nullcognition.effectiveandroidui.ui.renderer.chapter;

import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

public class ChapterRendererBuilder extends RendererBuilder<Chapter>{

	public ChapterRendererBuilder(Collection<Renderer<Chapter>> prototypes){
		super(prototypes);
	}

	@Override protected Class getPrototypeClass(Chapter content){
		return ChapterRenderer.class;
	}
}
