package Shareit.Item;

public class ItemMapper {


    public static ItemDTO mapItemToDto(Item item) {
        ItemDTO itemDto = null;

        if (item != null) {
            itemDto = ItemDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .description(item.getDescription())
                    .available(item.isAvailable())
                    .build();
        }
        return itemDto;
    }

    public static Item mapDtoToItem(ItemDTO itemDto) {
        Item item = new Item();
        if (itemDto != null) {
            item = Item.builder()
                    .name(itemDto.getName())
                    .description(itemDto.getDescription())
                    .available(itemDto.getAvailable())
                    .build();
        }
        return item;
    }
}
