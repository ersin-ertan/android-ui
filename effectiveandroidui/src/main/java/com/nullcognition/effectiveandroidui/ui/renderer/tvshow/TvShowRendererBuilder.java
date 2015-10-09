package com.nullcognition.effectiveandroidui.ui.renderer.tvshow;

import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

public class TvShowRendererBuilder extends RendererBuilder<TvShow>{

	public TvShowRendererBuilder(Collection<Renderer<TvShow>> prototypes){
		super(prototypes);
	}

	@Override protected Class getPrototypeClass(TvShow tvShow){
		return TvShowRenderer.class;
	}
}
