package nz.ac.auckland;

import nz.ac.auckland.se281.Service;
import nz.ac.auckland.se281.Types;

public class Catering extends Service {

  protected String cateringTypeName;
  protected int cateringCost;

  public Catering(String bookingReference, Types.CateringType cateringType, int cateringCost) {
    super(bookingReference);
    this.cateringTypeName = cateringType.getName();
    this.cateringCost = cateringCost;
  }
}
