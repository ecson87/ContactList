package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Dob {
  @SerializedName("date")
  public String date;
  @SerializedName("age")
  public int age;
}
