package id.go.jakarta.smartcity.contactlist.model;

public class Contact {
  public String gender;
  public Name name;
  public Location location;
  public String email;
  public Dob dob;
  public String phone;
  public String cell;
  public Id id;
  public Picture picture;
  public String nat;

  public Contact(Name name, Picture picture) {
    this.name = name;
    this.picture = picture;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public Picture getPicture() {
    return picture;
  }

  public void setPicture(Picture picture) {
    this.picture = picture;
  }
}
