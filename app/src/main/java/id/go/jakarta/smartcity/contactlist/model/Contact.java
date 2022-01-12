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

  public String getLongName() {
    return name.first + " " + name.last;
  }

  public String getShareContent() {
    return this.name.first + " " + this.name.last + ", " + this.email + ", " + this.cell;
  }

  public String getAddress() {
    return this.location.street.name + " " + this.location.street.number + ", " + this.location.city + ", " + this.location.state + ", " + this.location.postcode;
  }

  public String getPosition() {
    return this.location.coordinates.latitude + ", " + this.location.coordinates.longitude;
  }

  public String getMapLocationURI() {
    return "geo:0,0?q=" + this.location.coordinates.latitude + "," + this.location.coordinates.longitude + "(Treasure)";
  }
}
