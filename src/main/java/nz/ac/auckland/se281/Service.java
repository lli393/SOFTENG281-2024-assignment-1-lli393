package nz.ac.auckland.se281;

public abstract class Service extends Object {

  protected String bookingReference;
  protected Types type;

  public Service(String bookingReference) {
    this.bookingReference = bookingReference;
  }

  public abstract String getType();

  public abstract String getCost();

  public String getReference() {
    return bookingReference;
  }
}
