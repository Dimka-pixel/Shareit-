package Shareit.Item;

import Shareit.User.User;
import Shareit.User.UserDTO;
import Shareit.User.UserMapper;
import Shareit.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public ItemDTO addItem(int id, ItemDTO itemDto) {
        Item item = ItemMapper.mapDtoToItem(itemDto);
        User user = UserMapper.mapDtoToUser(userService.getUserById(id));
        item.setOwner(user);
        itemRepository.save(item);
        log.info("return {}", item);
        return ItemMapper.mapItemToDto(item);
    }

    @Override
    public ItemDTO updateItem(int userId, int itemId, ItemDTO itemDto) {
        User user = UserMapper.mapDtoToUser(userService.getUserById(userId));
        Item item = itemRepository.getReferenceById(itemId);
        if (user.equals(item.getOwner())) {
            if (itemDto.getName() != null) {
                if (!itemDto.getName().isBlank()) {
                    item.setName(itemDto.getName());
                } else {
                    log.warn("The field \"name\" is blank");
                    throw new ItemValidateException("The field \"name\" should not be empty", HttpStatus.BAD_REQUEST);
                }
            }
            if (itemDto.getDescription() != null) {
                if (!itemDto.getDescription().isBlank()) {
                    item.setDescription(itemDto.getDescription());
                } else {
                    log.warn("The field \"description\" is blank");
                    throw new ItemValidateException("The field \"description\" should not be empty", HttpStatus.BAD_REQUEST);
                }
            }
            if (itemDto.getAvailable() != null) {
                item.set_available(itemDto.getAvailable());
            }
            log.info("return {}", ItemMapper.mapItemToDto(item));
            return ItemMapper.mapItemToDto(item);
        } else {
            log.warn("This user is not owner");
            throw new ItemValidateException("This user is not owner", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ItemDTO getItemById(int id) {
        Item item = itemRepository.getReferenceById(id);
        ItemDTO itemDTO = ItemMapper.mapItemToDto(item);
        log.info("return {}", itemDTO);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAllItem(int id) {
        List<ItemDTO> items = new ArrayList<>();
        List<Item> allItems = itemRepository.findAll();
        User user = UserMapper.mapDtoToUser(userService.getUserById(id));
        if (userService.getUserById(id) != null) {
            for (Item item :  allItems) {
                if (! allItems.isEmpty() && user.equals(item.getOwner())) {
                    items.add(ItemMapper.mapItemToDto(item));
                }
            }
        }
        log.info("return {}", items);
        return items;
    }

    @Override
    public List<ItemDTO> searchItems(String text) {
        List<ItemDTO> items = new ArrayList<>();
        List<Item> allItems = itemRepository.findAll();
        if (!text.isBlank()) {
            for (Item item :  allItems) {
                if (!text.isEmpty() && ! allItems.isEmpty() && item.getDescription().toLowerCase().
                        contains(text.toLowerCase()) && item.is_available()) {
                    items.add(ItemMapper.mapItemToDto(item));
                }
            }
        } else {
            log.warn("The description is empty");
            throw new ItemValidateException("The description should not be empty", HttpStatus.BAD_REQUEST);
        }
        log.info("return {}", items);
        return items;
    }

}
