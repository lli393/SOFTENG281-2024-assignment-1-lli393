package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // field
  private ArrayList<Venue> venueList = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {
    int venueAmount = venueList.size();
    // print numbers of venue(s)
    if (venueAmount >= 10) {
      // it uses “are”, the quantity is a digit, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage(
          "are", String.valueOf(venueAmount), "s"); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage(
          "are", String.valueOf(venueAmount), "s"); // Using the printMessage() method

    } else if (venueAmount > 1 && venueAmount < 10) {
      // it uses “are”, the quantity is a word, “venues” is plural, and the sentence ends with a
      // colon (“:”)
      String venueWord = null;
      switch (venueAmount) {
        case 2:
          venueWord = "two";
          break;
        case 3:
          venueWord = "three";
          break;
        case 4:
          venueWord = "four";
          break;
        case 5:
          venueWord = "five";
          break;
        case 6:
          venueWord = "six";
          break;
        case 7:
          venueWord = "seven";
          break;
        case 8:
          venueWord = "eight";
          break;
        case 9:
          venueWord = "nine";
          break;
      }
      MessageCli.NUMBER_VENUES.getMessage("are", venueWord, "s"); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage(
          "are", venueWord, "s"); // Using the printMessage() method

    } else if (venueAmount == 1) {
      // it uses “is”, the quantity is a word, “venue” is singular, and the sentence ends with a
      // colon (“:”)
      MessageCli.NUMBER_VENUES.getMessage("is", "one", ""); // Using the getMessage() method
      MessageCli.NUMBER_VENUES.printMessage("is", "one", ""); // Using the printMessage() method

    } else if (venueAmount == 0) {
      // the quantity is “no”, “venues” is plural, and the sentence ends with a period (“.”)
      MessageCli.NO_VENUES.printMessage();
    }
    // loop for every successfully added venue(s)
    for (Venue venue : venueList) {
      String venueName = venue.getName();
      String venueCode = venue.getCode();
      String capacityInput = venue.getCapacity();
      String hireFeeInput = venue.getFee();
      MessageCli.VENUE_ENTRY.getMessage(venueName, venueCode, capacityInput, hireFeeInput, "");
      MessageCli.VENUE_ENTRY.printMessage(venueName, venueCode, capacityInput, hireFeeInput, "");
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    boolean success = true;
    // if venue name is blank
    if (venueName == null || venueName.isEmpty() || venueName.trim().isEmpty()) {
      // venueName is blank
      success = false;
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    }
    // if venue code is repeated
    for (Venue venue : venueList) {
      String code = venue.getCode();
      String name;
      // if we find an "equivalent book" (in terms of object reference equality), sell it
      if (code.equals(venueCode)) {
        success = false;
        name = venue.getName();
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.getMessage(venueCode, name);
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, name);
      }
    }
    // venue capacity is whole integer, positive > 0
    try {
      // convert capacityInput to numbers
      int tempNumber = Integer.parseInt(capacityInput);
      // check if it is positive
      if (tempNumber <= 0) {
        success = false;
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      }

    } catch (Exception e) {
      // if the capacityInput is not valid
      success = false;
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
    }

    // venue hire fee is whole integer, positive > 0
    try {
      // convert capacityInput to numbers
      int tempNumber = Integer.parseInt(hireFeeInput);
      // check if it is positive
      if (tempNumber <= 0) {
        success = false;
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      }

    } catch (Exception e) {
      // if the capacityInput is not valid
      success = false;
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
    }

    // succesfully create venue if pass all of the above
    if (success) {
      // add venue
      // venueName is trimmed if there are spaces at either side
      Venue newVenue = new Venue(venueName.trim(), venueCode, capacityInput, hireFeeInput);
      venueList.add(newVenue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.getMessage(venueName.trim(), venueCode);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName.trim(), venueCode);
    }
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
