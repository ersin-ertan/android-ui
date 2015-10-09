package com.nullcognition.effectiveandroidui.ui.fragment;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;
import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.domain.tvshow.ChapterCollection;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.activity.BaseActivity;
import com.nullcognition.effectiveandroidui.ui.presenter.TvShowPresenter;
import com.nullcognition.effectiveandroidui.ui.renderer.chapter.ChapterAdapteeCollection;
import com.nullcognition.effectiveandroidui.ui.renderer.chapter.ChapterRendererAdapter;
import com.nullcognition.effectiveandroidui.ui.renderer.chapter.ChapterRendererAdapterFactory;
import com.nullcognition.effectiveandroidui.util.ToastUtils;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;

public class TvShowDraggableFragment extends BaseFragment implements TvShowPresenter.View{

	private static final String EXTRA_TV_SHOW = "extra_tv_show";

	@Inject TvShowPresenter               tvShowPresenter;
	@Inject ChapterRendererAdapterFactory chapterRendererAdapterFactory;

	private ChapterRendererAdapter adapter;
	private ChapterAdapteeCollection chapterAdapteeCollection = new ChapterAdapteeCollection();

	private boolean useSaveInstanceState = true;

	@Bind(R.id.draggable_view) DraggableView draggable_view;
	@Bind(R.id.iv_fan_art)     ImageView     iv_fan_art;
	@Bind(R.id.lv_chapters)    ListView      lv_chapters;
	@Bind(R.id.pb_loading)     ProgressBar   pb_loading;

	private TextView header_tv_show_chapters;

	@Override protected void injectDependencies(){ ((BaseActivity) getActivity()).getActivityComponent().inject(this); }

	@Override public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		tvShowPresenter.setView(this);
		initializeDraggableView();
		initializeListView();
	}

	public void disableSaveInstanceState(){
		useSaveInstanceState = false;
	}

	private void initializeListView(){
		header_tv_show_chapters = (TextView) LayoutInflater.from(getActivity())
		                                                   .inflate(R.layout.header_tv_show_chapters, null);
		lv_chapters.addHeaderView(header_tv_show_chapters);
		adapter = (ChapterRendererAdapter) chapterRendererAdapterFactory.getChapterRendererAdapter(
				chapterAdapteeCollection);
		lv_chapters.setAdapter(adapter);
	}

	public void showTvShow(final String tvShowId){
		if(isAdded()){
			tvShowPresenter.loadTvShow(tvShowId);
		}
	}

	@Override public void showLoading(){
		pb_loading.setVisibility(View.VISIBLE);
	}

	@Override public void showFanArt(final String tvShowFanArtUrl){
		iv_fan_art.setVisibility(View.VISIBLE);
		Picasso.with(getActivity())
		       .load(tvShowFanArtUrl)
		       .placeholder(R.color.main_color)
		       .into(iv_fan_art);
	}

	@Override public void showTvShowTitle(final String tvShowTitle){
		String tvShowHeaderTitle = getString(R.string.tv_show_title, tvShowTitle);
		header_tv_show_chapters.setText(tvShowHeaderTitle);
	}

	@Override public boolean isReady(){
		return isAdded();
	}

	@Override public boolean isAlreadyLoaded(){
		return adapter.getCount() > 0;
	}

	@Override public void showChapters(ChapterCollection chapters){
		chapterAdapteeCollection.clear();
		chapterAdapteeCollection.addAll(chapters.getChapters());
		adapter.notifyDataSetChanged();
	}

	@Override public void hideLoading(){
		pb_loading.setVisibility(View.GONE);
	}

	@Override public void showTvShowNotFoundMessage(){
		String tvShowNotFoundMessage = getString(R.string.tv_show_not_found);
		ToastUtils.showShortMessage(tvShowNotFoundMessage, getActivity());
	}

	@Override public void showConnectionErrorMessage(){
		String connectionErrorMessage = getString(R.string.connection_error_message);
		ToastUtils.showError(connectionErrorMessage, getActivity());
	}

	@Override public void showTvShow(){
		draggable_view.setVisibility(View.VISIBLE);
		draggable_view.maximize();
	}

	@Override public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		if(useSaveInstanceState){
			outState.putSerializable(EXTRA_TV_SHOW, tvShowPresenter.getCurrentTvShow());
		}
	}

	@Override public void onViewStateRestored(Bundle savedInstanceState){
		super.onViewStateRestored(savedInstanceState);
		if(savedInstanceState != null){
			final TvShow tvShow = (TvShow) savedInstanceState.getSerializable(EXTRA_TV_SHOW);
			updatePresenterWithSavedTvShow(tvShow);
		}
	}

	private void updatePresenterWithSavedTvShow(final TvShow tvShow){
		if(tvShow != null){
			draggable_view.post(new Runnable(){
				@Override public void run(){
					tvShowPresenter.loadTvShow(tvShow);
				}
			});
		}
	}

	@Override protected int getFragmentLayout(){
		return R.layout.fragment_draggable_tv_show;
	}

	private void initializeDraggableView(){
		draggable_view.post(new Runnable(){
			@Override
			public void run(){
				draggable_view.closeToRight();
			}
		});
		draggable_view.setDraggableListener(new DraggableListener(){
			@Override public void onMaximized(){
				//Empty
			}

			@Override public void onMinimized(){
				//Empty
			}

			@Override public void onClosedToLeft(){
				tvShowPresenter.tvShowClosed();
			}

			@Override public void onClosedToRight(){
				tvShowPresenter.tvShowClosed();
			}
		});
	}
}
