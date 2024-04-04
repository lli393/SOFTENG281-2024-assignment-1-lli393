package nz.ac.auckland;

import nz.ac.auckland.se281.Service;
import nz.ac.auckland.se281.Types;

public class Catering extends Service {

  private static final Types Types = null;
  protected Types.CateringType cateringType;

  public Catering(String bookingReference, Types.CateringType cateringType) {
    super(bookingReference, Types);
    this.cateringType = cateringType;
  }
}
