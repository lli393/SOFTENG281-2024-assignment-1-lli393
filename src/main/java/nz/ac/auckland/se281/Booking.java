package nz.ac.auckland.se281;

public class Booking {

  private String bookingVenueName;
  private String bookingCode;
  private String bookingReference;
  private String currentDate;
  private String bookingDate;
  private String bookingEmail;
  private String bookingCapacity;

  public Booking(
      String bookingVenueName,
      String bookingCode,
      String bookingReference,
      String currentDate,
      String bookingDate,
      String bookingEmail,
      String bookingCapacity) {
    this.bookingVenueName = bookingVenueName;
    this.bookingCode = bookingCode;
    this.bookingReference = bookingReference;
    this.currentDate = currentDate;
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingCapacity = bookingCapacity;
  }

  public String getDate() {
    return currentDate;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getCode() {
    return bookingCode;
  }

  public String getName() {
    return bookingVenueName;
  }

  public String getReference() {
    return bookingReference;
  }

  public String getCapacity() {
    return bookingCapacity;
  }

  public String getMail() {
    return bookingEmail;
  }
}
