package Shareit.Item;

import Shareit.User.UserMapper;
import Shareit.User.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private ItemDAO itemDao;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public ItemDTO addItem(int id, ItemDTO itemDto) {
        Item item = ItemMapper.mapDtoToItem(itemDto);
        item.setOwner(UserMapper.mapDtoToUser(userService.getUserById(id)));
        itemDao.addObject(item);
        logger.info("return " + item);
        return ItemMapper.mapItemToDto(item);
    }

    @Override
    public ItemDTO updateItem(int userId, int itemId, ItemDTO itemDto) {
        Item item = Optional.ofNullable(itemDao.getObjectById(itemId)).orElseThrow();
        if (userId == item.getOwner().getId()) {
            if (itemDto.getName() != null) {
                if (!itemDto.getName().isBlank()) {
                    item.setName(itemDto.getName());
                } else {
                    logger.warn("The field \"name\" is blank");
                    throw new ItemValidateException("The field \"name\" should not be empty", HttpStatus.BAD_REQUEST);
                }
            }
            if (itemDto.getDescription() != null) {
                if (!itemDto.getDescription().isBlank()) {
                    item.setDescription(itemDto.getDescription());
                } else {
                    logger.warn("The field \"description\" is blank");
                    throw new ItemValidateException("The field \"description\" should not be empty", HttpStatus.BAD_REQUEST);
                }
            }
            if (itemDto.getAvailable() != null) {
                item.setAvailable(itemDto.getAvailable());
            }
            logger.info("return " + ItemMapper.mapItemToDto(item));
            return ItemMapper.mapItemToDto(item);
        } else {
            logger.warn("This user is not owner");
            throw new ItemValidateException("This user is not owner", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ItemDTO getItemById(int id) {
        Item item = Optional.ofNullable(itemDao.getObjectById(id)).orElseThrow();
        ItemDTO itemDTO = ItemMapper.mapItemToDto(item);
        logger.info("return " + itemDTO);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAllItem(int id) {
        List<ItemDTO> items = new ArrayList<>();
        if (userService.getUserById(id) != null) {
            for (Item item : itemDao.getAllObjects().values()) {
                if (!itemDao.getAllObjects().isEmpty() && item.getOwner().getId() == id) {
                    items.add(ItemMapper.mapItemToDto(item));
                }
            }
        }
        logger.info("return " + items);
        return items;
    }

    @Override
    public List<ItemDTO> searchItems(String text) {
        List<ItemDTO> items = new ArrayList<>();
        if (!text.isBlank()) {
            for (Item item : itemDao.getAllObjects().values()) {
                if (!text.isEmpty() && !itemDao.getAllObjects().isEmpty() && item.getDescription().toLowerCase().
                        contains(text.toLowerCase()) && item.isAvailable()) {
                    items.add(ItemMapper.mapItemToDto(item));
                }
            }

        } else {
            logger.warn("The description is empty");
            throw new ItemValidateException("The description should not be empty", HttpStatus.BAD_REQUEST);
        }
        logger.info("return " + items);
        return items;
    }

}
