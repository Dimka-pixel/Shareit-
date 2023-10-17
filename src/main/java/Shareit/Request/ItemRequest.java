package Shareit.Request;

import Shareit.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Requests")
public class ItemRequest {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "requestor_id")
    private User user;

}

