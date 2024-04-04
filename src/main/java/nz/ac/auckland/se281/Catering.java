package nz.ac.auckland.se281;

public class Catering extends Service {

  protected String cateringTypeName;
  protected String cateringCost;

  public Catering(String bookingReference, Types.CateringType cateringType, String cateringCost) {
    super(bookingReference);
    this.cateringTypeName = cateringType.getName();
    this.cateringCost = cateringCost;
  }

  @Override
  public String getType() {
    return "Catering";
  }

  public String getCost() {
    return cateringCost;
  }

  public String getName() {
    return cateringTypeName;
  }
}
