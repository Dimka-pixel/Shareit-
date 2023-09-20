package Shareit.Item;

import Shareit.Storage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class ItemDAO implements Storage<Item> {

    private int id;
    private final HashMap<Integer, Item> items = new HashMap<>();

    @Override
    public Item addObject(Item item) {
        id++;
        item.setId(id);
        items.put(id, item);
        return item;
    }

    @Override
    public Item getObjectById(int id) {
        return items.get(id);
    }

    @Override
    public HashMap<Integer, Item> getAllObjects() {
        return items;
    }

    @Override
    public void deleteObject(int id) {
        items.remove(id);
    }

}
