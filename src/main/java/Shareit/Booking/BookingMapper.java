package Shareit.Booking;

import Shareit.Item.ItemRepository;
import Shareit.User.User;
import Shareit.User.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingMapper {

    private static UserRepository userRepository;

    private static ItemRepository itemRepository;


    public static BookingDTO mapBookingToDto(Booking booking){
        BookingDTO bookingDTO = null;
        if(booking!=null){
            bookingDTO = BookingDTO.builder()
                    .id(booking.getId())
                    .start(booking.getStart())
                    .end(booking.getEnd())
                    .bookerId(booking.getBooker().getId())
                    .itemId(booking.getItem().getId())
                    .ItemName(booking.getItem().getName())
                    .status(booking.getStatus())
                    .build();
        }

        return bookingDTO;
    }

    public static Booking mapDtoToBooking(BookingDTO bookingDTO){
        Booking booking = null;
        if(bookingDTO!=null){
            booking = Booking.builder()
                    .id(bookingDTO.getId())
                    .start(bookingDTO.getStart())
                    .end(booking.getEnd())
                    .booker(userRepository.getReferenceById(bookingDTO.getBookerId()))
                    .item(itemRepository.getReferenceById(bookingDTO.getBookerId()))
                    .status(bookingDTO.getStatus())
                    .build();
        }
        return booking;
    }
}
