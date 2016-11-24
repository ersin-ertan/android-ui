package com.nullcognition.svg;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*
  http://inloop.github.io/svg2android/
  To convert from zeplins svg

  what we have learned is the fill xml tags are mostly api 18+ and dont work with tinting
  so we tried applying a style with fill color, but that did not work either so we try
  Emma Guy themeing the fill color directly as the attribute of the fill color of the svg

  set the app:theme and the android:theme to my custome style but it didn't
 */

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (Build.VERSION.SDK_INT < 16) {
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN);
    } else {
      View decorView = getWindow().getDecorView();
      // Hide the status bar.
      int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
      decorView.setSystemUiVisibility(uiOptions);
      // Remember that you should never show the action bar if the
      // status bar is hidden, so hide that too if necessary.
      ActionBar actionBar = getActionBar();
      if (actionBar != null) {
        actionBar.hide();
      }
    }
    setContentView(R.layout.test);
  }

  @Override protected void onResume() {
    super.onResume();
    // test alternative way

    LinearLayout ll = (LinearLayout) findViewById(R.id.test);
    ImageView imageView = (ImageView) findViewById(R.id.cir);
    ImageView wax = (ImageView) findViewById(R.id.ic_waxxi);
    ImageView srcd = (ImageView) findViewById(R.id.srcd);

    // working after new layout attempt

    //imageView.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent),
    //    PorterDuff.Mode.SRC_IN);
    //DrawableCompat.setTint(imageView.getDrawable(), ContextCompat.getColor(this, R.color.colorAccent));
    //DrawableCompat.setTintMode(imageView.getDrawable(), PorterDuff.Mode.SRC_IN);

  }

  void emmaGuy() {

    // uses internal apis, may not be suitable

    //final ContextThemeWrapper wrapper = new ContextThemeWrapper(this, R.style.ColorStyle);
    //final Drawable
    //    drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.cir, wrapper.getTheme());
    //imageView.setImageDrawable(drawable);
    //
    //
    //final Resources.Theme theme = getResources().newTheme();
    //
    //theme.applyStyle(R.style.ColorStyle, false);
    //
    //final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.cir, theme);
    //imageView.setImageDrawable(drawable);
  }
}
