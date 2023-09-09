package Shareit.Booking;

import Shareit.Item.Item;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {

    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Item item;

}
