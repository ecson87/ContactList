package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

public class Picture {
  @SerializedName("large")
  public String large;
  @SerializedName("medium")
  public String medium;
  @SerializedName("thumbnail")
  public String thumbnail;
}
