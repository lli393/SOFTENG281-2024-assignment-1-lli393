package nz.ac.auckland.se281;

public class Floral extends Service {

  protected String floralTypeName;
  protected int floralCost;

  public Floral(String bookingReference, Types.FloralType floralType, int floralCost) {
    super(bookingReference);
    this.floralTypeName = floralType.getName();
    this.floralCost = floralCost;
  }
}
