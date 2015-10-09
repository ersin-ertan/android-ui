package com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel;

import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.pedrogomez.renderers.AdapteeCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChapterViewModelCollection implements AdapteeCollection<ChapterViewModel>{

	private final List<ChapterViewModel> chapters;

	public ChapterViewModelCollection(){
		this(new ArrayList<ChapterViewModel>());
	}

	public ChapterViewModelCollection(List<ChapterViewModel> chapters){
		this.chapters = chapters;
	}

	@Override public int size(){
		return chapters.size();
	}

	@Override public ChapterViewModel get(int index){
		return chapters.get(index);
	}

	@Override public boolean add(ChapterViewModel chapter){
		return chapters.add((ChapterViewModel) chapter);
	}

	@Override public boolean remove(Object chapter){
		return chapters.remove(chapter);
	}

	@Override public boolean addAll(Collection<? extends ChapterViewModel> chapters){
		return this.chapters.addAll((Collection<? extends ChapterViewModel>) chapters);
	}

	@Override public boolean removeAll(Collection<?> chapters){
		return this.chapters.removeAll(chapters);
	}

	public void clear(){
		chapters.clear();
	}
}
