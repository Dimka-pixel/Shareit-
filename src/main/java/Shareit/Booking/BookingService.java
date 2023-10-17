package Shareit.Booking;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface BookingService {

    BookingDTO addBooking(BookingDTO bookingDTO, int userId);

    BookingView updateBookingStatus(int bookingId, int userId, boolean approved);

    List<BookingDTO> getAllBookingByUser(int userId, State state);

    List<BookingDTO> getAllBookingByItem(int userId, int ItemId, State state);


}
