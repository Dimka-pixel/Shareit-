package Shareit.Booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public BookingDTO addBooking(BookingDTO bookingDTO, int userId) {
        bookingDTO.setBookerId(userId);
        Booking booking = BookingMapper.mapDtoToBooking(bookingDTO);
        booking.setStatus(Status.WAITING);
        return BookingMapper.mapBookingToDto(bookingRepository.save(booking));
    }

    @Override
    public
    BookingView updateBookingStatus(int bookingId, int userId, boolean approved) {
        Booking booking = bookingRepository.getReferenceById(bookingId);
        if(userId==booking.getBooker().getId()){
            if(approved) {
                booking.setStatus(Status.APPROVED);
            }else{
                booking.setStatus(Status.REJECTED);
            }
        }else{
            throw new BookingValidateException("You are not the owner this Item", HttpStatus.NOT_FOUND);
        }
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<BookingDTO> getAllBookingByUser(int userId, State state) {
        return null;
    }

    @Override
    public List<BookingDTO> getAllBookingByItem(int userId, int ItemId, State state) {
        return null;
    }
}
