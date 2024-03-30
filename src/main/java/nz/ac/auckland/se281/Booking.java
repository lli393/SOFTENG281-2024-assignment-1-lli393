package nz.ac.auckland.se281;

public class Booking {
  private String bookingVenue;
  private String bookingCode;
  private String bookingDate;
  private String bookingEmail;
  private String bookingCapacity;

  public Booking(
      String bookingVenue,
      String bookingCode,
      String bookingDate,
      String bookingEmail,
      String bookingCapacity) {
    this.bookingVenue = bookingVenue;
    this.bookingCode = bookingCode;
    this.bookingDate = bookingDate;
    this.bookingEmail = bookingEmail;
    this.bookingCapacity = bookingCapacity;
  }
}
