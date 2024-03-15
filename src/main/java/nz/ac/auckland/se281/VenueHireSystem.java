package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // field
  int venueID = 0;
  String venueName;
  String venueCode;
  String capacityInput;
  String hireFeeInput;

  public VenueHireSystem() {}

  public void printVenues() {
    // print numbers of venue(s)
    if (venueID >= 10) {
      // it uses “are”, the quantity is a digit, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage(
          "are", String.valueOf(venueID), "s"); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage(
          "are", String.valueOf(venueID), "s"); // Using the printMessage() method

    } else if (venueID > 1) {
      // it uses “are”, the quantity is a word, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      String venueWord = null;
      switch (venueID) {
        case 2:
          venueWord = "two";
        case 3:
          venueWord = "three";
        case 4:
          venueWord = "four";
        case 5:
          venueWord = "five";
        case 6:
          venueWord = "six";
        case 7:
          venueWord = "seven";
        case 8:
          venueWord = "eight";
        case 9:
          venueWord = "nine";
      }
      MessageCli.NUMBER_VENUES.getMessage("are", venueWord, "s"); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage(
          "are", venueWord, "s"); // Using the printMessage() method

    } else if (venueID == 1) {
      // it uses “is”, the quantity is a word, “venue” is singular, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage("is", "one", ""); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage("is", "one", ""); // Using the printMessage() method

    } else if (venueID == 0) {
      // the quantity is “no”, “venues” is plural, and the sentence ends with a period (“.”)
      MessageCli.NO_VENUES.printMessage();
    }
    // loop for every successfully added venue(s)
    for (int i = 0; i < venueID; i++) {
      MessageCli.VENUE_ENTRY.getMessage(venueName, venueCode, capacityInput, hireFeeInput, "");
      MessageCli.VENUE_ENTRY.printMessage(venueName, venueCode, capacityInput, hireFeeInput, "");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // if venue name is blank

    if (venueName != null && !venueName.isEmpty() && !venueName.trim().isEmpty()) {
      // venueName is not empty
      this.venueID = venueID++;
      this.venueName = venueName;
      this.venueCode = venueCode;
      this.capacityInput = capacityInput;
      this.hireFeeInput = hireFeeInput;
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    } else {
      // venueName is blank
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    }

    // if venue code is repeated
    // venue capacity and hiring fee is whole integer, positive > 0
    // succesfully create venue if pass all of the above

  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
