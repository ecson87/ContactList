package id.go.jakarta.smartcity.contactlist.model;

public class Name {
  public String title;
  public String first;
  public String last;

  public Name(String first, String last) {
    this.first = first;
    this.last = last;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getLast() {
    return last;
  }

  public void setLast(String last) {
    this.last = last;
  }
}
