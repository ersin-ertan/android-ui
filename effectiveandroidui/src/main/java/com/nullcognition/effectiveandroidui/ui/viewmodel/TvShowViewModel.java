package com.nullcognition.effectiveandroidui.ui.viewmodel;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import com.nullcognition.effectiveandroidui.domain.GetTvShowById;
import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.nullcognition.effectiveandroidui.domain.tvshow.ChapterCollection;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.fragment.TvShowFragment;
import com.nullcognition.effectiveandroidui.ui.viewmodel.action.ActionCommand;
import com.nullcognition.effectiveandroidui.ui.viewmodel.action.ShowTvShowOnBrowserActionCommand;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class TvShowViewModel{

	private final GetTvShowById                    getTvShowById;
	private final ShowTvShowOnBrowserActionCommand showTvShowOnBrowserActionCommand;

	private Listener listener;
	private boolean  isReady;

	@Inject	public TvShowViewModel(GetTvShowById getTvShowById,
	                       ShowTvShowOnBrowserActionCommand showTvShowOnBrowserActionCommand){
		this.getTvShowById = getTvShowById;
		this.showTvShowOnBrowserActionCommand = showTvShowOnBrowserActionCommand;
	}

	public void initialize(){
		listener.onEmptyCaseVisibilityChanged(true);
	}

	public void loadTvShow(final String tvShowId){
		listener.onLoadVisibilityChanged(true);
		listener.onEmptyCaseVisibilityChanged(false);
		getTvShowById.execute(tvShowId, new GetTvShowById.Callback(){
			@Override public void onTvShowLoaded(TvShow tvShow){
				notifyTvShowLoaded(tvShow);
			}

			@Override public void onTvShowNotFound(){
				notifyTvShowNotFound();
			}

			@Override public void onConnectionError(){
				notifyConnectionError();
			}
		});
	}

	public ActionCommand getTvShowClickedCommand(){
		return showTvShowOnBrowserActionCommand;
	}

	public void setListener(TvShowFragment listener){
		this.listener = listener;
	}

	public Listener getListener(){
		return listener;
	}

	public void setReady(boolean isReady){
		this.isReady = isReady;
	}

	private void notifyConnectionError(){
		if(isReady){
			listener.onLoadVisibilityChanged(false);
			listener.onVisibilityChanged(false);
			listener.onEmptyCaseVisibilityChanged(true);
			listener.onConnectionErrorMessageNotFound();
		}
	}

	private void notifyTvShowNotFound(){
		if(isReady){
			listener.onLoadVisibilityChanged(false);
			listener.onVisibilityChanged(false);
			listener.onEmptyCaseVisibilityChanged(true);
			listener.onTvShowMessageNotFound();
		}
	}

	private void notifyTvShowLoaded(TvShow tvShow){
		showTvShowOnBrowserActionCommand.setTvShowUrl(tvShow.getPoster());
		if(isReady){
			listener.onFanArtLoaded(tvShow.getFanArt());
			listener.onTvShowTitleLoaded(tvShow.getTitle());
			listener.onChaptersLoaded(getChaptersViewModel(tvShow.getChapters()));
			listener.onVisibilityChanged(true);
			listener.onLoadVisibilityChanged(false);
			listener.onEmptyCaseVisibilityChanged(false);
		}
	}

	private List<ChapterViewModel> getChaptersViewModel(ChapterCollection chapterCollection){
		List<ChapterViewModel> chapterViewModels = new LinkedList<ChapterViewModel>();
		for(Chapter chapter : chapterCollection){
			chapterViewModels.add(new ChapterViewModel(chapter));
		}
		return chapterViewModels;
	}

	/**
	 Interface created to work as ViewModel listener.
	 Every change in the view model will be
	 notified to Listener implementation.
	 */
	public interface Listener{

		void onFanArtLoaded(final String fanArt);

		void onTvShowTitleLoaded(final String tvShowTitle);

		void onChaptersLoaded(final List<ChapterViewModel> chapters);

		void onVisibilityChanged(final boolean visible);

		void onLoadVisibilityChanged(final boolean visible);

		void onEmptyCaseVisibilityChanged(final boolean visible);

		void onTvShowMessageNotFound();

		void onConnectionErrorMessageNotFound();
	}
}
