package com.nullcognition.effectiveandroidui.ui.renderer.tvshow;

import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.pedrogomez.renderers.AdapteeCollection;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TvShowCollection implements AdapteeCollection<TvShow>, Serializable{

	private static final long serialVersionUID = 8799677673292716638L;

	private final List<TvShow> tvShows;

	public TvShowCollection(){
		this(new LinkedList<TvShow>());
	}

	public TvShowCollection(Collection<TvShow> tvShows){
		this.tvShows = new LinkedList<TvShow>();
		this.tvShows.addAll(tvShows);
	}

	@Override public int size(){
		return tvShows.size();
	}

	@Override public TvShow get(int index){
		return tvShows.get(index);
	}

	@Override public boolean add(TvShow tvShow){
		tvShows.add(tvShow);
		return true;
	}

	@Override public boolean remove(Object tvShow){
		return tvShows.remove(tvShow);
	}

	@Override public boolean addAll(Collection<? extends TvShow> tvShows){
		return this.tvShows.addAll(tvShows);
	}

	@Override public boolean removeAll(Collection<?> tvShows){
		return this.tvShows.removeAll(tvShows);
	}

	public void clear(){
		tvShows.clear();
	}

	public List<TvShow> getAsList(){
		return (List<TvShow>) ((LinkedList<TvShow>) tvShows).clone();
	}
}
