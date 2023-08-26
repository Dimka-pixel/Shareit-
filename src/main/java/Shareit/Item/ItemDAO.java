package Shareit.Item;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ItemDAO {

    private int id;
    private HashMap<Integer, Item> items = new HashMap<>();

    public Item addItem(Item item) {
        id++;
        item.setId(id);
        items.put(id, item);
        return item;
    }

    public Item getItemByID(int id) {
        return items.get(id);
    }

    public HashMap<Integer, Item> getAllItems() {
        return items;
    }

}
