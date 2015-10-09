package com.nullcognition.effectiveandroidui.ui.renderer.chapter;


import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChapterAdapteeCollection implements AdapteeCollection<Chapter>{

	private final List<Chapter> chapters;

	public ChapterAdapteeCollection(){
		this(new ArrayList<Chapter>());
	}

	public ChapterAdapteeCollection(List<Chapter> chapters){
		this.chapters = chapters;
	}

	@Override public int size(){
		return chapters.size();
	}

	@Override public Chapter get(int index){
		return chapters.get(index);
	}

	@Override public boolean add(Chapter chapter){
		return chapters.add(chapter);
	}

	@Override public boolean remove(Object chapter){
		return chapters.remove(chapter);
	}

	@Override public boolean addAll(Collection<? extends Chapter> chapters){
		return this.chapters.addAll(chapters);
	}

	@Override public boolean removeAll(Collection<?> chapters){
		return this.chapters.removeAll(chapters);
	}

	@Override
	public void clear(){
		chapters.clear();
	}
}
