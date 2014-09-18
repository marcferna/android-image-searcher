package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.marcferna.imagesearcher.R;


public class ImageSearchActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_search);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_image_search, menu);
    return true;
  }

  public void onClickSettings(MenuItem mi) {
    Intent settingsIntent = new Intent(this, SettingsActivity.class);
    startActivity(settingsIntent);
  }
}
