package com.codepath.marcferna.imagesearcher.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marc on 9/21/14.
 */
public class GoogleImage implements Parcelable {
  public int width;
  public int height;
  public String googleId;
  public String url;
  public String thumbUrl;
  public String title;

  public GoogleImage(){
    // nothing
  }

  // Parcelling part
  public GoogleImage(Parcel pc){
    this.width    = pc.readInt();
    this.height   = pc.readInt();
    this.googleId = pc.readString();
    this.url      = pc.readString();
    this.thumbUrl = pc.readString();
    this.title    = pc.readString();
  }

  @Override
  public int describeContents(){
    return 0;
  }

  @Override
  public void writeToParcel(Parcel pc, int flags) {
    pc.writeInt(width);
    pc.writeInt(height);
    pc.writeString(googleId);
    pc.writeString(url);
    pc.writeString(thumbUrl);
    pc.writeString(title);
  }
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public GoogleImage createFromParcel(Parcel in) {
      return new GoogleImage(in);
    }

    public GoogleImage[] newArray(int size) {
      return new GoogleImage[size];
    }
  };

}
