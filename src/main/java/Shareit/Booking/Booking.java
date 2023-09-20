package Shareit.Booking;

import Shareit.Item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;

}
