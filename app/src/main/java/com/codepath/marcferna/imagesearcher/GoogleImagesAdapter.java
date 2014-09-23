package com.codepath.marcferna.imagesearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.marcferna.imagesearcher.model.GoogleImage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by marc on 9/21/14.
 */
public class GoogleImagesAdapter extends ArrayAdapter<GoogleImage> {

  public final static class ViewHolderGoogleImage {
    public ImageView googleImage;
    public TextView imageTitle;

    public ViewHolderGoogleImage(View itemView) {
      googleImage = (ImageView) itemView.findViewById(R.id.ivGoogleImage);
      imageTitle = (TextView) itemView.findViewById(R.id.tvImageTitle);
    }
  }

  public GoogleImagesAdapter(Context context, List<GoogleImage> images) {
    super(context, android.R.layout.simple_list_item_1, images);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolderGoogleImage viewHolder;

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);

      viewHolder = new ViewHolderGoogleImage(convertView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolderGoogleImage) convertView.getTag();
    }

    GoogleImage image = getItem(position);
    if(image != null) {
      viewHolder.googleImage.setImageResource(0);
      viewHolder.googleImage.setAdjustViewBounds(true);
      Picasso.with(viewHolder.googleImage.getContext()).load(image.thumbUrl).fit().centerInside().into(viewHolder.googleImage);
      viewHolder.imageTitle.setText(image.title);
    }

    return convertView;
  }
}
