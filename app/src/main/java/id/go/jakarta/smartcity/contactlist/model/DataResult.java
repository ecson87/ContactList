package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResult {
  @SerializedName("results")
  public ArrayList<Contact> results;
  @SerializedName("info")
  public Info info;
}
