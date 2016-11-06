package com.nullcognition.fastlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainLayout layout = LayoutCache.getInstance()
        .getLayout(this,
            LayoutCache.Activity_Main_Layout); // loads but crashes on stop/ or is it destroy
    //new ActivityMainLayout(this); // working, but doesn't cache

    setContentView(layout);
  }

  @Override protected void onDestroy() {
    // cache should be cleared here
    super.onDestroy();
  }
}
