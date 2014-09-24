package com.codepath.marcferna.imagesearcher.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.marcferna.imagesearcher.EndlessScrollListener;
import com.codepath.marcferna.imagesearcher.GoogleImageClient;
import com.codepath.marcferna.imagesearcher.GoogleImagesAdapter;
import com.codepath.marcferna.imagesearcher.R;
import com.codepath.marcferna.imagesearcher.model.GoogleImage;
import com.codepath.marcferna.imagesearcher.model.Setting;

import org.apache.http.Header;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class ImageSearchActivity extends Activity {

  private GoogleImagesAdapter imagesAdapter;
  private EditText etSearch;
  private GridView gvImages;
  private Setting settings = new Setting();
  private static int SETTINGS_ACTIVITY_CODE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_search);
    setupViews();
  }

  private void setupViews() {
    setupSearchView();
    setupGridView();
  }

  private void setupSearchView() {
    etSearch = (EditText) findViewById(R.id.etSearch);
  }

  private void setupGridView() {
    gvImages = (GridView)findViewById(R.id.gvImages);
    imagesAdapter = new GoogleImagesAdapter(this, new ArrayList<GoogleImage>());
    gvImages.setAdapter(imagesAdapter);
    imagesAdapter.notifyDataSetChanged();
    gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent imageDisplayIntent = new Intent(ImageSearchActivity.this, ImageDisplayActivity.class);
        GoogleImage image = imagesAdapter.getItem(position);

        imageDisplayIntent.putExtra("image", image);
        startActivity(imageDisplayIntent);
      }
    });
    gvImages.setOnScrollListener(new EndlessScrollListener() {
      @Override
      public void onLoadMore(int page, int totalItemsCount) {
        GoogleImageClient client = new GoogleImageClient(etSearch.getText().toString(), settings);
        client.offset = page;
        fetchImages(client);
      }
    });
  }


  public void onClickSearch(View searchButton) {
    imagesAdapter.clear();
    String searchValue = etSearch.getText().toString();
    GoogleImageClient client = new GoogleImageClient(searchValue, settings);
    fetchImages(client);
  }

  public void populateImageGrid(ArrayList<GoogleImage> images) {
    // populate the adapter for the grid view
    imagesAdapter.addAll(images);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_image_search, menu);
    return true;
  }

  public void onClickSettings(MenuItem mi) {
    Intent settingsIntent = new Intent(this, SettingsActivity.class);
    settingsIntent.putExtra("settings", settings);
    startActivityForResult(settingsIntent, SETTINGS_ACTIVITY_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // REQUEST_CODE is defined above
    if (resultCode == RESULT_OK && requestCode == SETTINGS_ACTIVITY_CODE) {
      settings = data.getExtras().getParcelable("settings");
    }
  }

  private void fetchImages(GoogleImageClient client){
    try {
      Class[] parameterTypes = new Class[1];
      parameterTypes[0] = ArrayList.class;
      Method method = ImageSearchActivity.class.getMethod("populateImageGrid", parameterTypes);
      client.search(this, method);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
