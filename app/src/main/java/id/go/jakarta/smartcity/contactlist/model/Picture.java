package id.go.jakarta.smartcity.contactlist.model;

public class Picture {
  public String large;
  public String medium;
  public String thumbnail;

  public Picture(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getLarge() {
    return large;
  }

  public void setLarge(String large) {
    this.large = large;
  }

  public String getMedium() {
    return medium;
  }

  public void setMedium(String medium) {
    this.medium = medium;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }
}
