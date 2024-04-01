package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Task 1 field
  private ArrayList<Venue> venueList = new ArrayList<Venue>();
  // Task 2 field
  String currentDate;
  String availableDate;
  int bookingVenueNumber;
  String bookingVenueName = null;
  private ArrayList<Booking> bookingList = new ArrayList<Booking>();

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
      MessageCli.VENUE_ENTRY.getMessage(
          venueName, venueCode, capacityInput, hireFeeInput, availableDate);
      MessageCli.VENUE_ENTRY.printMessage(
          venueName, venueCode, capacityInput, hireFeeInput, availableDate);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    boolean successCreate = true;
    // if venue name is blank
    if (venueName == null || venueName.isEmpty() || venueName.trim().isEmpty()) {
      // venueName is blank
      successCreate = false;
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // if venue code is repeated
    for (Venue venue : venueList) {
      String code = venue.getCode();
      String name;
      // if we find an "equivalent book" (in terms of object reference equality), sell it
      if (code.equals(venueCode)) {
        successCreate = false;
        name = venue.getName();
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.getMessage(venueCode, name);
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, name);
        return;
      }
    }

    // venue capacity is whole integer, positive > 0
    try {
      // convert capacityInput to numbers
      int tempNumber = Integer.parseInt(capacityInput);
      // check if it is positive
      if (tempNumber <= 0) {
        successCreate = false;
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        return;
      }

    } catch (Exception e) {
      // if the capacityInput is not valid
      successCreate = false;
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    // venue hire fee is whole integer, positive > 0
    try {
      // convert capacityInput to numbers
      int tempNumber = Integer.parseInt(hireFeeInput);
      // check if it is positive
      if (tempNumber <= 0) {
        successCreate = false;
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }

    } catch (Exception e) {
      // if the capacityInput is not valid
      successCreate = false;
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // succesfully create venue if pass all of the above
    if (successCreate) {
      // add venue
      // venueName is trimmed if there are spaces at either side
      Venue newVenue = new Venue(venueName.trim(), venueCode, capacityInput, hireFeeInput);
      venueList.add(newVenue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.getMessage(venueName.trim(), venueCode);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName.trim(), venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    // assume dateInput is always valid
    currentDate = dateInput;
    MessageCli.DATE_SET.getMessage(dateInput);
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (currentDate != null) {
      // if current date is not empty
      MessageCli.CURRENT_DATE.getMessage(currentDate);
      MessageCli.CURRENT_DATE.printMessage(currentDate);
    } else {
      MessageCli.CURRENT_DATE.printMessage("not set");
    }
  }

  public void makeBooking(String[] options) {
    // options in order: venue code, request date, email, number of attendees
    String bookingCode = options[0];
    String bookingDate = options[1];
    String bookingEmail = options[2];
    String bookingCapacity = options[3];
    boolean successBook = true;

    // the system date must be set
    if (currentDate == null) {
      successBook = false;
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // there must be at least one venue in system
    if (venueList.size() == 0) {
      successBook = false;
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // venue code must exist
    for (int i = 0; i < venueList.size(); i++) {
      if (venueList.get(i).getCode().equals(bookingCode)) {
        // if code exists, exit the for loop
        // store the name of the venue
        bookingVenueName = venueList.get(i).getName();
        // get the location of venue in venueList
        bookingVenueNumber = i;

        // if the date for specific venue has already booked
        for (Booking bookNumber : bookingList) {
          if (bookNumber.getDate().equals(bookingDate)) {
            successBook = false;
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.getMessage(
                bookingVenueName, bookingDate);
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
                bookingVenueName, bookingDate);
            return;
          }
        }

        // boolean success remain true
        successBook = true;
        break;
      } else {
        // if code doesn't match or exist
        successBook = false;
      }
    }
    if (!successBook) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.getMessage(bookingCode);
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(bookingCode);
      return;
    }

    // venue must be available on specified date

    // the booking date must not be in the past
    // split the booking date
    String[] bookingDateParts = bookingDate.split("/");
    String bookDay = bookingDateParts[0]; // "DD"
    String bookMonth = bookingDateParts[1]; // "MM"
    String bookYear = bookingDateParts[2]; // "YYYY"
    // split the current date
    String[] currentDateParts = currentDate.split("/");
    String currentDay = currentDateParts[0]; // "DD"
    String currentMonth = currentDateParts[1]; // "MM"
    String currentYear = currentDateParts[2]; // "YYYY"
    // in the past means current year is greater than booking year
    if (Integer.parseInt(currentYear) > Integer.parseInt(bookYear)) {
      successBook = false;
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.getMessage(bookingDate, currentDate);
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, currentDate);
      return;
    } else if ((Integer.parseInt(currentYear) < Integer.parseInt(bookYear))) {
      // booking year is in the future, no action needed
      // same current year and booking year will proceed
    } else if ((Integer.parseInt(currentMonth) > Integer.parseInt(bookMonth))) {
      // booking month is in the past
      successBook = false;
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.getMessage(bookingDate, currentDate);
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, currentDate);
      return;
    } else if ((Integer.parseInt(currentMonth) < Integer.parseInt(bookMonth))) {
      // booking month is in the future, no action needed
      // same current month and booking month will proceed
    } else if ((Integer.parseInt(currentDay) > Integer.parseInt(bookDay))) {
      // booking date is in the past
      successBook = false;
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.getMessage(bookingDate, currentDate);
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(bookingDate, currentDate);
      return;
    }

    // pass all condition, create booking
    if (successBook) {
      String bookingReference = BookingReferenceGenerator.generateBookingReference();
      // create booking object new booking and store the following variables
      Booking newBooking =
          new Booking(
              bookingVenueName,
              bookingCode,
              bookingReference,
              bookingDate,
              bookingEmail,
              bookingCapacity);
      // add the object to the bookingList arrayList
      bookingList.add(newBooking);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.getMessage(
          bookingReference, bookingVenueName, bookingDate, bookingCapacity);
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingReference, bookingVenueName, bookingDate, bookingCapacity);
    }
  }

  public void printBookings(String venueCode) {
    // initialise bookingVenueNumber to -1
    bookingVenueNumber = -1;
    // find the index of first booking in bookingList
    for (int i = 0; i < venueList.size(); i++) {
      if (bookingList.get(i).getCode().equals(venueCode)) {

        // store the name of the venue
        bookingVenueName = venueList.get(i).getName();
        // get the location of venue in venueList
        bookingVenueNumber = i;
      }
    }
    // If there are no bookings for the venue yet, then it’s next available date is 01/01/2024
    // (today).
    if (bookingVenueNumber == -1) {
      availableDate = currentDate;
    }
    // If a booking already exists for 01/01/2024 (today), then the next available date is
    // 02/01/2024 (tomorrow).

    // If a booking already exists for today and tomorrow, then the next available date is
    // 03/01/2024.
    // If there is one booking 01/01/2024 (today), and the next one is 5 days away, then the next
    // available date is 02/01/2024 (tomorrow).
    // The next available date will also need updating when the system time changes. For example,
    // assume that the system date is 01/01/2024:

    // If the system date moves forward to 20/01/2024, and there are no bookings for the venue yet,
    // then it’s next available date is 20/01/2024 (the new today).

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
