package nz.ac.auckland.se281;

public class Floral extends Service {

  protected String floralTypeName;
  protected String floralCost;

  public Floral(String bookingReference, Types.FloralType floralType, String floralCost) {
    super(bookingReference);
    this.floralTypeName = floralType.getName();
    this.floralCost = floralCost;
  }

  @Override
  public String getType() {
    return "Floral";
  }

  @Override
  public String getCost() {
    return floralCost;
  }

  public String getName() {
    return floralTypeName;
  }
}
