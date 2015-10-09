package com.nullcognition.effectiveandroidui.ui.renderer.chapterviewmodel;

import com.nullcognition.effectiveandroidui.ui.viewmodel.ChapterViewModel;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;

public class ChapterViewModelRendererBuilder extends RendererBuilder<ChapterViewModel>{

  public ChapterViewModelRendererBuilder(Collection<Renderer<ChapterViewModel>> prototypes) {
    super(prototypes);
  }

  @Override protected Class getPrototypeClass(ChapterViewModel content) {
    return ChapterViewModelRenderer.class;
  }
}
