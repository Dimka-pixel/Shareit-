package Shareit.Booking;

import Shareit.Item.ItemView;
import Shareit.User.UserView;

import java.time.LocalDateTime;

public interface BookingView {
     int getId();
    LocalDateTime getStart();
    LocalDateTime getEnd();
    UserView getUser();
    ItemView getItem();
    Status getStatus();
}
