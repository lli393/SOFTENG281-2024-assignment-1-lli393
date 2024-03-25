package nz.ac.auckland.se281;

public class Venue {

  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;
  private int venueID;
  private int nextID = 0;

  public Venue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacityInput = capacityInput;
    this.hireFeeInput = hireFeeInput;
    this.venueID = nextID;
    nextID++;
  }

  public int getID() {
    return venueID;
  }

  public String getName() {
    return venueName;
  }

  public String getCode() {
    return venueCode;
  }

  public String getCapacity() {
    return capacityInput;
  }

  public String getFee() {
    return hireFeeInput;
  }
}
