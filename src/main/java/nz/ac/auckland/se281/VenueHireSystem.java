package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.Catering;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Task 1 field
  private ArrayList<Venue> venueList = new ArrayList<Venue>();
  // Task 2 field
  private String currentDate;
  private String availableDate;
  private String oldBookingCapacity;
  private int bookingVenueIndex;
  private String bookingVenueName = null;
  private String bookingVenueCapacity;
  private ArrayList<Booking> bookingList = new ArrayList<Booking>();
  private String printVenueName;
  private String bookingReference;
  private String bookingDate;

  // Task 3 field
  private ArrayList<Service> serviceList = new ArrayList<Service>();

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
      return;
    }

    // loop for every successfully added venue(s)
    for (Venue venue : venueList) {
      String venueName = venue.getName();
      String venueCode = venue.getCode();
      String capacityInput = venue.getCapacity();
      String hireFeeInput = venue.getFee();

      // If there are no bookings for the venue yet, then it’s next available date is 01/01/2024
      // (today).
      // If a booking already exists for today and tomorrow, then the next available date is
      // 03/01/2024.
      // If there is one booking 01/01/2024 (today), and the next one is 5 days away, then the next
      // available date is 02/01/2024 (tomorrow).
      // The next available date will also need updating when the system time changes. For example,
      // assume that the system date is 01/01/2024:
      // If the system date moves forward to 20/01/2024, and there are no bookings for the venue
      // yet, then it’s next available date is 20/01/2024 (the new today).

      // set available date to current date
      availableDate = currentDate;
      // if current date is not set
      if (currentDate == null) {
        availableDate = "00/00/0000";
      }

      // get the available day
      String[] availableDateParts = availableDate.split("/");
      int availableDay = Integer.parseInt(availableDateParts[0]); // "DD"
      String availableMonth = availableDateParts[1]; // "MM"
      String availableYear = availableDateParts[2]; // "YYYY"
      boolean available;

      // from today till end of the month
      for (int day = availableDay; day < 32; day++) {
        // assume it the day is not booked
        available = true;
        // check for available day in the booking dates
        for (Booking book : bookingList) {
          // if its the same code
          if (book.getCode().equals(venueCode)) {
            // get the booking day
            String bookingDate = book.getDate();
            String[] bookingDateParts = bookingDate.split("/");
            int bookingDay = Integer.parseInt(bookingDateParts[0]); // "DD"

            // if the day is booked
            if (bookingDay == day) {
              // break immediately if found
              available = false;
              break;
            }
          }
        }
        // if the day is available/not booked
        if (available) {
          // update available date
          availableDate = Integer.toString(day) + "/" + availableMonth + "/" + availableYear;
          if (day < 10) {
            availableDate = "0" + availableDate;
          }
          break;
        }
      }

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
    for (int index = 0; index < venueList.size(); index++) {
      if (venueList.get(index).getCode().equals(bookingCode)) {
        // if code exists, exit the for loop
        // store the name of the venue
        bookingVenueName = venueList.get(index).getName();
        // get the location of venue in venueList
        bookingVenueIndex = index;
        // get the venue capacity for booking
        bookingVenueCapacity = venueList.get(index).getCapacity();

        // if the date for specific venue has already booked
        for (Booking bookNumber : bookingList) {
          if (bookNumber.getName().equals(bookingVenueName)
              && bookNumber.getDate().equals(bookingDate)) {
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

    // if number of attendees is less than 25% or more than 100% of venue capacity
    if (Integer.parseInt(bookingCapacity) < 0.25 * Integer.parseInt(bookingVenueCapacity)) {
      // store previous number of attendee
      oldBookingCapacity = bookingCapacity;
      // set number of attendees to 25% of venue capacity
      bookingCapacity = Integer.toString((int) (0.25 * Integer.parseInt(bookingVenueCapacity)));
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.getMessage(
          oldBookingCapacity, bookingCapacity, bookingVenueCapacity);
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          oldBookingCapacity, bookingCapacity, bookingVenueCapacity);

    } else if (Integer.parseInt(bookingCapacity) > Integer.parseInt(bookingVenueCapacity)) {
      // store previous number of attendee
      oldBookingCapacity = bookingCapacity;
      // set number of attendees to venue capacity
      bookingCapacity = bookingVenueCapacity;
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.getMessage(
          oldBookingCapacity, bookingCapacity, bookingVenueCapacity);
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          oldBookingCapacity, bookingCapacity, bookingVenueCapacity);
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
    // get printing values
    printVenueName = null;
    boolean bookingExist = false;

    for (Venue venues : venueList) {
      if (venues.getCode().equals(venueCode)) {
        printVenueName = venues.getName();
      }
    }
    // if venue doesn't exist or no venue in the system
    if (printVenueName == null || venueList.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.getMessage(venueCode);
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }
    // print booking header
    MessageCli.PRINT_BOOKINGS_HEADER.getMessage(printVenueName);
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(printVenueName);
    // loop the bookings
    for (Booking bookings : bookingList) {
      // if it is the venue
      if (venueCode.equals(bookings.getCode())) {
        // booking exists
        bookingExist = true;
        bookingReference = bookings.getReference();
        bookingDate = bookings.getDate();
        MessageCli.PRINT_BOOKINGS_ENTRY.getMessage(bookingReference, bookingDate);
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(bookingReference, bookingDate);
      }
    }
    // if no booking
    if (!bookingExist) {
      MessageCli.PRINT_BOOKINGS_NONE.getMessage(printVenueName);
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(printVenueName);
      return;
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    for (Booking bookings : bookingList) {
      // if bookingReference can be found
      if (bookings.getReference().equals(bookingReference)) {
        // get the number of attendees
        int bookingCapacity = Integer.parseInt(bookings.getCapacity());
        // get cost for the service per person
        int cateringCost = cateringType.getCostPerPerson();
        // get the total cost for catering
        cateringCost = cateringCost * bookingCapacity;

        // add a service
        Catering newCatering = new Catering(bookingReference, cateringType, cateringCost);
        serviceList.add(newCatering);
        // print
        MessageCli.ADD_SERVICE_SUCCESSFUL.getMessage(
            "Catering (" + cateringType.getName() + ")", bookingReference);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Catering (" + cateringType.getName() + ")", bookingReference);
        return;
      }
    }
    // if bookingReference is not found
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.getMessage("Catering", bookingReference);
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void addServiceMusic(String bookingReference) {
    for (Booking bookings : bookingList) {
      // if bookingReference can be found
      if (bookings.getReference().equals(bookingReference)) {
        // add a service
        Music newMusic = new Music(bookingReference);
        serviceList.add(newMusic);
        // print
        MessageCli.ADD_SERVICE_SUCCESSFUL.getMessage("Music", bookingReference);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
        return;
      }
    }
    // if bookingReference is not found
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.getMessage("Music", bookingReference);
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    for (Booking bookings : bookingList) {
      // if bookingReference can be found
      if (bookings.getReference().equals(bookingReference)) {
        // get cost for the service
        int floralCost = floralType.getCost();
        // add a service
        Floral newCatering = new Floral(bookingReference, floralType, floralCost);
        serviceList.add(newCatering);
        // print
        MessageCli.ADD_SERVICE_SUCCESSFUL.getMessage(
            "Floral (" + floralType.getName() + ")", bookingReference);
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
            "Floral (" + floralType.getName() + ")", bookingReference);
        return;
      }
    }
    // if bookingReference is not found
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.getMessage("Catering", bookingReference);
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
