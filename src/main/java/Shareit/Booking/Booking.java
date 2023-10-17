package Shareit.Booking;

import Shareit.Item.Item;
import Shareit.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
@Builder
public class Booking {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotNull
    private LocalDateTime start;
    @Column
    @NotNull
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "booker_id")
    @NotNull
    private User booker;
    @ManyToOne
    @JoinColumn(name = "item_id")
    @NotNull
    private Item item;
    @Enumerated(EnumType.STRING)
    private Status status;


}
