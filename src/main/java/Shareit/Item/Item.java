package Shareit.Item;

import Shareit.Request.ItemRequest;
import Shareit.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Item {

    private int id;
    private String name;
    private String description;
    private User owner;
    private boolean available;
    private ItemRequest request;
    //

}
