package com.codepath.marcferna.imagesearcher.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marc on 9/18/14.
 */
public class Setting implements Parcelable {

  public String filter;

  public Setting() {

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {

  }
}
