package Shareit.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;


    public ItemController(@Autowired ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/items")
    public ItemDTO createItem(@RequestHeader(value = "X-Sharer-User-Id") int userId, @Validated @RequestBody ItemDTO itemDto) {
        return itemService.addItem(userId, itemDto);

    }

    @GetMapping("/items/{ItemId}")
    public ItemDTO getItemById(@PathVariable int ItemId) {
        return itemService.getItemById(ItemId);
    }

    @GetMapping("/items")
    public List<ItemDTO> getAllItemsByOwnerId(@RequestHeader(value = "X-Sharer-User-Id") int userId) {
        return itemService.getAllItem(userId);
    }

    @GetMapping("/items/search")
    public List<ItemDTO> searchItems(@RequestParam String text) {
        return itemService.searchItems(text);
    }

    @PatchMapping("/items/{itemId}")
    public ItemDTO updateItem(@RequestHeader(value = "X-Sharer-User-Id") int userId, @PathVariable int itemId, @RequestBody ItemDTO itemDto) {
        return itemService.updateItem(userId, itemId, itemDto);
    }
}
