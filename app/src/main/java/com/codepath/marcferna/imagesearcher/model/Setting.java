package com.codepath.marcferna.imagesearcher.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marc on 9/18/14.
 */
public class Setting implements Parcelable {

  public String size;
  public String color;
  public String type;
  public String site;

  public Setting() {
    this.size = "";
    this.color = "";
    this.type = "";
    this.site = "";
  }

  // Parcelling part
  public Setting(Parcel pc){
    this.size  = pc.readString();
    this.color = pc.readString();
    this.type  = pc.readString();
    this.site  = pc.readString();

  }

  @Override
  public int describeContents(){
    return 0;
  }

  @Override
  public void writeToParcel(Parcel pc, int flags) {
    pc.writeString(size);
    pc.writeString(color);
    pc.writeString(type);
    pc.writeString(site);
  }
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public Setting createFromParcel(Parcel in) {
      return new Setting(in);
    }

    public Setting[] newArray(int size) {
      return new Setting[size];
    }
  };
}
