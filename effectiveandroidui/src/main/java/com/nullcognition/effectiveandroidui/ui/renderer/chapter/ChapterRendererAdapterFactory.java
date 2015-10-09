package com.nullcognition.effectiveandroidui.ui.renderer.chapter;

import android.view.LayoutInflater;

import com.nullcognition.effectiveandroidui.di.activity.ActivityScope;
import com.nullcognition.effectiveandroidui.domain.tvshow.Chapter;
import com.pedrogomez.renderers.RendererAdapter;

import javax.inject.Inject;

public class ChapterRendererAdapterFactory {

  private final ChapterRendererBuilder rendererBuilder;
  private final LayoutInflater         layoutInflater;

  @Inject public ChapterRendererAdapterFactory(ChapterRendererBuilder rendererBuilder,
                                       LayoutInflater layoutInflater){
    this.rendererBuilder = rendererBuilder;
    this.layoutInflater = layoutInflater;
  }

  public RendererAdapter<Chapter> getChapterRendererAdapter(
          final ChapterAdapteeCollection chapterCollection){
    return new ChapterRendererAdapter(layoutInflater, rendererBuilder, chapterCollection);
  }
}
