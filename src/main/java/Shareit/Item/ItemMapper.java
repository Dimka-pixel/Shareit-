package Shareit.Item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    //commit

    public static ItemDTO mapItemToDto(Item item) {
        ItemDTO itemDto = new ItemDTO();

        if (item != null) {
            itemDto.setId(item.getId());
            itemDto.setName(item.getName());
            itemDto.setDescription(item.getDescription());
            itemDto.setAvailable(item.isAvailable());
        }
        return itemDto;
    }

    public static Item mapDtoToItem(ItemDTO itemDto) {
        Item item = new Item();
        if (itemDto != null) {
            item.setName(itemDto.getName());
            item.setDescription(itemDto.getDescription());
            item.setAvailable(itemDto.getAvailable());
        }
        return item;
    }
}
