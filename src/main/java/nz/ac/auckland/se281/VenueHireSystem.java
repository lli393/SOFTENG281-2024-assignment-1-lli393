package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // field
  int venueAmount = 0;

  public VenueHireSystem() {}

  public void printVenues() {
    if (venueAmount >= 10) {
      // it uses “are”, the quantity is a digit, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage(
          "are", String.valueOf(venueAmount), "s"); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage(
          "are", String.valueOf(venueAmount), "s"); // Using the printMessage() method
      MessageCli.VENUE_ENTRY.printMessage();

    } else if (venueAmount > 1) {
      // it uses “are”, the quantity is a word, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      String venueWord = null;
      switch (venueAmount) {
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
      MessageCli.VENUE_ENTRY.printMessage();
    } else if (venueAmount == 1) {
      // it uses “is”, the quantity is a word, “venue” is singular, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage("is", "one", ""); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage("is", "one", ""); // Using the printMessage() method
      MessageCli.VENUE_ENTRY.printMessage();
    } else if (venueAmount == 0) {
      // the quantity is “no”, “venues” is plural, and the sentence ends with a period (“.”)
      MessageCli.NO_VENUES.printMessage();
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
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
