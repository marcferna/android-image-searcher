package com.codepath.marcferna.imagesearcher;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.codepath.marcferna.imagesearcher.model.GoogleImage;

import java.util.List;

/**
 * Created by marc on 9/21/14.
 */
public class GoogleImagesAdapter extends ArrayAdapter<GoogleImage> {

  public GoogleImagesAdapter(Context context, List<GoogleImage> images) {
    super(context, android.R.layout.simple_list_item_1, images);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    return super.getView(position, convertView, parent);
  }
}
