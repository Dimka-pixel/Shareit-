package Shareit.Item;

import Shareit.User.UserMapper;
import Shareit.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDao;

    private final UserServiceImpl userService;

    public ItemServiceImpl(@Autowired ItemDAO itemDao, @Autowired UserServiceImpl userService) {
        this.itemDao = itemDao;
        this.userService = userService;
    }

    @Override
    public ItemDTO addItem(int id, ItemDTO itemDto) {
        Item item = ItemMapper.mapDtoToItem(itemDto);
        item.setOwner(UserMapper.mapDtoToUser(userService.getUserById(id)));
        itemDao.addItem(item);
        return ItemMapper.mapItemToDto(item);
    }

    @Override
    public ItemDTO updateItem(int userId, int itemId, ItemDTO itemDto) {
        Item item = itemDao.getItemByID(itemId);
        if (item != null) {
            if (userId == item.getOwner().getId()) {
                if (itemDto.getName() != null) {
                    item.setName(itemDto.getName());
                }
                if (itemDto.getDescription() != null) {
                    item.setDescription(itemDto.getDescription());
                }
                if (itemDto.getAvailable() != null) {
                    item.setAvailable(itemDto.getAvailable());
                }
                return ItemMapper.mapItemToDto(item);
            } else {
                throw new ItemValidateException("This user is not owner", HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ItemValidateException("Item with id " + " not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ItemDTO getItemById(int id) {
        Item item = itemDao.getItemByID(id);
        ItemDTO itemDTO;
        if (item != null) {
            itemDTO = ItemMapper.mapItemToDto(item);
            return itemDTO;
        } else {
            throw new ItemValidateException("This Item not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<ItemDTO> getAllItem(int id) {
        List<ItemDTO> items = new ArrayList<>();
        if (userService.getUserById(id) != null) {
            for (Item item : itemDao.getAllItems().values()) {
                if (!itemDao.getAllItems().isEmpty() && item.getOwner().getId() == id) {
                    items.add(ItemMapper.mapItemToDto(item));
                }
            }
        }
        return items;
    }

    @Override
    public List<ItemDTO> searchItems(String text) {
        List<ItemDTO> items = new ArrayList<>();
        for (Item item : itemDao.getAllItems().values()) {
            if (!text.isEmpty() && !itemDao.getAllItems().isEmpty() && item.getDescription().toLowerCase().
                    contains(text.toLowerCase()) && item.isAvailable()) {
                items.add(ItemMapper.mapItemToDto(item));
            }
        }
        return items;
    }

}
