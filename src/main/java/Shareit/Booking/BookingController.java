package Shareit.Booking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static Shareit.Item.ItemController.HEADER_NAME;

@RestController
@RequestMapping("/bookings")
@Slf4j
@RequiredArgsConstructor
public class BookingController {

    private final BookingServiceImpl bookingService;

    @PostMapping
    BookingDTO addBooking(@Validated @RequestBody BookingDTO bookingDTO, @RequestHeader(value = HEADER_NAME) int userId){
        log.info("request POST/bookings");
    return bookingService.addBooking(bookingDTO,userId);
    }

    @PatchMapping("/{bookingId}")
    BookingView updateBookingStatus(@RequestHeader(value = HEADER_NAME) int userId, @PathVariable int bookingId,
                                   @RequestParam boolean approved){
        log.info("request PATCH");
    return bookingService.updateBookingStatus(bookingId, userId, approved);
    }



}
