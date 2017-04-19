package com.nullcognition.litho;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Text;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);

    ComponentContext cc = new ComponentContext(this);

    LithoView lithoView =
        LithoView.create(this, Text.create(cc).text("TEXT").textSizeDip(24).build());

    setContentView(lithoView);
  }
}
