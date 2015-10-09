package com.nullcognition.effectiveandroidui.domain.tvshow;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ChapterCollection implements Iterable<Chapter>, Serializable{

	private static final long serialVersionUID = 8799656478677673292L;

	private final Set<Chapter> chapters;

	public ChapterCollection(){
		this.chapters = new LinkedHashSet<Chapter>();
	}

	public Collection<Chapter> getChapters(){
		return (Collection<Chapter>) ((LinkedHashSet<Chapter>) chapters).clone();
	}

	public void add(Chapter chapter){
		this.chapters.add(chapter);
	}

	@Override public Iterator<Chapter> iterator(){
		return chapters.iterator();
	}
}
