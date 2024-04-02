package nz.ac.auckland.se281;

public class Booking {

  private String bookingVenueName;
  private String bookingCode;
  private String bookingReference;
  private String bookingDate;
  private String bookingEmail;
  private String bookingCapacity;

  public Booking(
      String bookingVenueName,
      String bookingCode,
      String bookingReference,
      String bookingDate,
      String bookingEmail,
      String bookingCapacity) {
    this.bookingVenueName = bookingVenueName;
    this.bookingCode = bookingCode;
    this.bookingReference = bookingReference;
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingCapacity = bookingCapacity;
  }

  public String getDate() {
    return bookingDate;
  }

  public String getCode() {
    return bookingCode;
  }

  public String getName() {
    return bookingVenueName;
  }
}
