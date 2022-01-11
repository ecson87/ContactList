package id.go.jakarta.smartcity.contactlist.model;

import com.google.gson.annotations.SerializedName;

public class Location {
  @SerializedName("street")
  public Street street;
  @SerializedName("city")
  public String city;
  @SerializedName("state")
  public String state;
  @SerializedName("country")
  public String country;
  @SerializedName("postcode")
  public int postcode;
  @SerializedName("coordinates")
  public Coordinates coordinates;
  @SerializedName("timezone")
  public Timezone timezone;
}
