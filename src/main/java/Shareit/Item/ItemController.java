package Shareit.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    public static final String HEADER_NAME = "X-Sharer-User-Id";
    @Autowired
    private ItemService itemService;
    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO createItem(@RequestHeader(value = HEADER_NAME) int userId, @Validated @RequestBody ItemDTO itemDto) {
        logger.info("request POST /items");
        return itemService.addItem(userId, itemDto);
    }

    @GetMapping("/{ItemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO getItemById(@PathVariable int ItemId) {
        logger.info("request GET /items/{ItemId} ItemId = {}", ItemId);
        return itemService.getItemById(ItemId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTO> getAllItemsByOwnerId(@RequestHeader(value = HEADER_NAME) int userId) {
        logger.info("request GET /items");
        return itemService.getAllItem(userId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTO> searchItems(@RequestParam String text) {
        logger.info("request GET /items/search");
        return itemService.searchItems(text);
    }

    @PatchMapping("/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO updateItem(@RequestHeader(value = HEADER_NAME) int userId,
                              @PathVariable int itemId, @RequestBody ItemDTO itemDto) {
        logger.info("request PATCH /items/{itemId} itemId = {}", itemId);
        return itemService.updateItem(userId, itemId, itemDto);
    }
}

