package com.nullcognition.effectiveandroidui.ui.fragment;
// ersin 08/10/15 Copyright (c) 2015+ All rights reserved.


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.ui.activity.BaseActivity;
import com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel.ChapterViewModelCollection;
import com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel.ChapterViewModelRendererAdapter;
import com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel.ChapterViewModelRendererAdapterFactory;
import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.nullcognition.effectiveandroidui.ui.viewmodel.TvShowViewModel;
import com.nullcognition.effectiveandroidui.ui.viewmodel.action.ActionCommand;
import com.nullcognition.effectiveandroidui.util.ToastUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class TvShowFragment extends BaseFragment implements TvShowViewModel.Listener{

	@Inject TvShowViewModel                        tvShowViewModel;
	@Inject ChapterViewModelRendererAdapterFactory chapterRendererAdapterFactory;

	private ChapterViewModelRendererAdapter adapter;
	private ChapterViewModelCollection chapterAdapteeCollection = new ChapterViewModelCollection();

	@Bind(R.id.iv_fan_art)   ImageView   iv_fan_art;
	@Bind(R.id.lv_chapters)  ListView    lv_chapters;
	@Bind(R.id.pb_loading)   ProgressBar pb_loading;
	@Bind(R.id.v_empty_case) View        v_empty_case;

	private TextView header_tv_show_chapters;

	@Override protected void injectDependencies(){ ((BaseActivity) getActivity()).getActivityComponent().inject(this); }

	@Override public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		initializeListView();
		bindViewModel();
	}

	@Override public void onAttach(Context context){
		super.onAttach(context);
		tvShowViewModel.setReady(true);
	}

	@Override public void onDetach(){
		super.onDetach();
		tvShowViewModel.setReady(false);
	}

	public void showTvShow(final String tvShowId){
		if(isAdded()){
			tvShowViewModel.loadTvShow(tvShowId);
		}
	}

	@Override protected int getFragmentLayout(){
		return R.layout.fragment_tv_show;
	}

	@Override public void onFanArtLoaded(final String fanArt){
		Picasso.with(getActivity()).load(fanArt).placeholder(R.color.main_color).into(iv_fan_art);
	}

	@Override public void onTvShowTitleLoaded(final String tvShowTitle){
		String tvShowHeaderTitle = getString(R.string.tv_show_title, tvShowTitle);
		header_tv_show_chapters.setText(tvShowHeaderTitle);
	}

	@Override public void onChaptersLoaded(List<ChapterViewModel> chapters){
		chapterAdapteeCollection.clear();
		chapterAdapteeCollection.addAll(chapters);
		adapter.notifyDataSetChanged();
	}

	@Override public void onVisibilityChanged(final boolean visible){
		int visibility = getVisibility(visible);
		iv_fan_art.setVisibility(visibility);
		lv_chapters.setVisibility(visibility);
	}

	@Override public void onLoadVisibilityChanged(final boolean visible){
		pb_loading.setVisibility(getVisibility(visible));
	}

	@Override public void onEmptyCaseVisibilityChanged(final boolean visible){
		v_empty_case.setVisibility(getVisibility(visible));
	}

	@Override public void onTvShowMessageNotFound(){
		ToastUtils.showError(getString(R.string.tv_show_not_found), getActivity());
	}

	@Override public void onConnectionErrorMessageNotFound(){
		ToastUtils.showError(getString(R.string.connection_error_message), getActivity());
	}

	@OnClick(R.id.iv_fan_art) void onFanArtClicked(){
		ActionCommand fanArtClickActionCommand = tvShowViewModel.getTvShowClickedCommand();
		fanArtClickActionCommand.execute();
	}

	private void initializeListView(){
		header_tv_show_chapters = (TextView) LayoutInflater.from(getActivity())
		                                                   .inflate(R.layout.header_tv_show_chapters, null);
		lv_chapters.addHeaderView(header_tv_show_chapters);
		adapter =
				(ChapterViewModelRendererAdapter) chapterRendererAdapterFactory.getChapterRendererAdapter(
						chapterAdapteeCollection);
		lv_chapters.setAdapter(adapter);
	}

	private void bindViewModel(){
		tvShowViewModel.setListener(this);
		tvShowViewModel.initialize();
	}

	private int getVisibility(final boolean visible){
		return visible ? View.VISIBLE : View.GONE;
	}
}
