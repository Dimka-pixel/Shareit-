package Shareit.Item;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    

    ItemDTO addItem(int id, ItemDTO itemDto);

    ItemDTO updateItem(int userId, int itemId, ItemDTO itemDto);

    ItemDTO getItemById(int id);

    List<ItemDTO> getAllItem(int id);

    List<ItemDTO> searchItems(String text);


}
