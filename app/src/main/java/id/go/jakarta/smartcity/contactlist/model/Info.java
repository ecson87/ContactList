package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

public class Info {
  @SerializedName("seed")
  public String seed;
  @SerializedName("results")
  public int results;
  @SerializedName("page")
  public int page;
  @SerializedName("version")
  public String version;
}
