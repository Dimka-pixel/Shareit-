package Shareit.Booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private int bookerId;
    private int itemId;
    private String ItemName;
    private Status status;


}
