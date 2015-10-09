package com.nullcognition.effectiveandroidui.domain.tvshow;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import java.io.Serializable;

public class TvShow implements Serializable{

	private static final long serialVersionUID = 8799656478674716638L;

	private final String            title;
	private final String            poster;
	private final String            fanArt;
	private final int               numberOfSeasons;
	private final ChapterCollection episodes;

	public TvShow(String title, String poster, String fanArt, int numberOfSeasons){
		this.title = title;
		this.poster = poster;
		this.fanArt = fanArt;
		this.numberOfSeasons = numberOfSeasons;
		this.episodes = new ChapterCollection();
	}

	/**
	 Add an episode to the tvShowViewModel.
	 */
	public void addEpisode(Chapter chapterViewModel){
		episodes.add(chapterViewModel);
	}

	/**
	 @return the tv show title.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 @return the tv show poster.
	 */
	public String getPoster(){
		return poster;
	}

	/**
	 @return the tv show fan art.
	 */
	public String getFanArt(){
		return fanArt;
	}

	/**
	 @return the tv show number of seasons.
	 */
	public int getNumberOfSeasons(){
		return numberOfSeasons;
	}

	/**
	 @return the tv show ChapterCollection.
	 */
	public ChapterCollection getChapters(){
		return episodes;
	}

	@Override
	public boolean equals(Object o){
		if(this == o){ return true; }
		if(!(o instanceof TvShow)){ return false; }

		TvShow tvShow = (TvShow) o;

		if(!title.equals(tvShow.title)){ return false; }

		return true;
	}

	@Override
	public int hashCode(){
		return title.hashCode();
	}
}
