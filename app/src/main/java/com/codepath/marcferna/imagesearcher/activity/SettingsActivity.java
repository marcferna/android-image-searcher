package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.marcferna.imagesearcher.R;


public class SettingsActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    // Get the settings from persitance
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_settings, menu);
    return true;
  }

  public void onClickSave(MenuItem mi) {
    // Save settings in persistance
    finish();
  }
}
