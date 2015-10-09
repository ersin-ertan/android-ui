package com.nullcognition.effectiveandroidui.ui.renderer.tvshow;

import android.view.LayoutInflater;

import com.nullcognition.effectiveandroidui.di.activity.ActivityScope;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

public class TvShowRendererAdapterFactory{

	private final TvShowRendererBuilder rendererBuilder;
	private final LayoutInflater        layoutInflater;

	@Inject	public TvShowRendererAdapterFactory(TvShowRendererBuilder rendererBuilder,
	                                    LayoutInflater layoutInflater){
		this.rendererBuilder = rendererBuilder;
		this.layoutInflater = layoutInflater;
	}

	public RendererAdapter<TvShow> getTvShowRendererAdapter(final TvShowCollection tvShowCollection){
		return new RendererAdapter<TvShow>(layoutInflater, rendererBuilder, tvShowCollection);
	}
}
