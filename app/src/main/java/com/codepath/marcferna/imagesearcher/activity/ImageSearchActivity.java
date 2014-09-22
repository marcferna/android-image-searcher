package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.marcferna.imagesearcher.GoogleImageClient;
import com.codepath.marcferna.imagesearcher.R;
import com.codepath.marcferna.imagesearcher.model.GoogleImage;

import org.apache.http.Header;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class ImageSearchActivity extends Activity {

  private EditText etSearch;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_search);
  }


  public void onClickSearch(View searchButton) {
    etSearch = (EditText) findViewById(R.id.etSearch);
    String searchValue = etSearch.getText().toString();
    GoogleImageClient client = new GoogleImageClient(searchValue);

    try {
      Class[] parameterTypes = new Class[1];
      parameterTypes[0] = ArrayList.class;
      Method method = ImageSearchActivity.class.getMethod("populateImageGrid", parameterTypes);
      client.search(this, method);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void populateImageGrid(ArrayList<GoogleImage> images) {
    // populate the adapter for the grid view
    Log.i("DEBUG", "hello");
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
