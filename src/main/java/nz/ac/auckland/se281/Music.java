package nz.ac.auckland.se281;

public class Music extends Service {

  // All music services cost $500, and there is only one music service available.
  protected String musicCost = "500";

  public Music(String bookingReference) {
    super(bookingReference);
  }

  @Override
  public String getType() {
    return "Music";
  }

  public String getCost() {
    return musicCost;
  }
}
