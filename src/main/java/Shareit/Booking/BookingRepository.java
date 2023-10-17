package Shareit.Booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    BookingView findById(int id);
}
