package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

public class Contact {
  @SerializedName("gender")
  public String gender;
  @SerializedName("name")
  public Name name;
  @SerializedName("location")
  public Location location;
  @SerializedName("email")
  public String email;
  @SerializedName("dob")
  public Dob dob;
  @SerializedName("phone")
  public String phone;
  @SerializedName("cell")
  public String cell;
  @SerializedName("id")
  public Id id;
  @SerializedName("picture")
  public Picture picture;
  @SerializedName("nat")
  public String nat;
}
