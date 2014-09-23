package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.codepath.marcferna.imagesearcher.R;
import com.codepath.marcferna.imagesearcher.model.GoogleImage;
import com.squareup.picasso.Picasso;

public class ImageDisplayActivity extends Activity {

  private GoogleImage image;
  private ImageView ivGoogleImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_display);
    image = getIntent().getParcelableExtra("image");
    ivGoogleImage = (ImageView) findViewById(R.id.ivGoogleImage);
    WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
    Display display = windowManager.getDefaultDisplay();
    Point windowSize = new Point();
    display.getSize(windowSize);
    ivGoogleImage.getLayoutParams().height = windowSize.x;
    Picasso.with(this).load(image.url).into(ivGoogleImage);
  }
}