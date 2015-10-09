package com.nullcognition.effectiveandroidui.ui.renderer.tvshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nullcognition.effectiveandroidui.R;
import com.nullcognition.effectiveandroidui.domain.tvshow.TvShow;
import com.nullcognition.effectiveandroidui.ui.presenter.TvShowCatalogPresenter;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TvShowRenderer extends Renderer<TvShow>{

  private final Context                context;
  private final TvShowCatalogPresenter tvShowCatalogPresenter;

  @Bind(R.id.iv_thumbnail)       ImageView thumbnailImageView;
  @Bind(R.id.tv_title)           TextView  titleTextView;
  @Bind(R.id.tv_seasons_counter) TextView  seasonsCounterTextView;

  @Inject
  public TvShowRenderer(Context context, TvShowCatalogPresenter tvShowCatalogPresenter){
    this.context = context;
    this.tvShowCatalogPresenter = tvShowCatalogPresenter;
  }

  @Override protected void setUpView(View rootView){
    ButterKnife.bind(this, rootView);
  }

  @Override protected void hookListeners(View rootView){
    //Empty because we are using ButterKnife library
  }

  @Override protected View inflate(LayoutInflater inflater, ViewGroup parent){
    return inflater.inflate(R.layout.row_tv_show, parent, false);
  }

  @Override public void render(){
    TvShow tvShow = getContent();
    renderThumbnail(tvShow);
    renderTitle(tvShow);
    renderSeasonCounter(tvShow);
  }

  @OnClick(R.id.iv_thumbnail) void onThumbnailClicked() {
    tvShowCatalogPresenter.onTvShowThumbnailClicked(getContent());
  }

  @OnClick(R.id.v_row_container) void onBackgroundClicked() {
    tvShowCatalogPresenter.onTvShowClicked(getContent());
  }

  private TvShow renderThumbnail(TvShow tvShow) {
    Picasso.with(context).load(tvShow.getPoster()).into(thumbnailImageView);
    return tvShow;
  }

  private void renderTitle(TvShow tvShow) {
    titleTextView.setText(tvShow.getTitle().toUpperCase());
  }

  private void renderSeasonCounter(TvShow tvShow) {
    String seassons = context.getString(R.string.seasons_counter, tvShow.getNumberOfSeasons());
    seasonsCounterTextView.setText(seassons);
  }
}
