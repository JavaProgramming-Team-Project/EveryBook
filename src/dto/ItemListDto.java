package dto;

public class ItemListDto {
    private Long itemKey;
    private String itemName;
    private String itemAddress;
    private String itemCategory;
    private int itemPrice;
    private double avgRating;
    private String itemImage;

    public ItemListDto(Long itemKey, String itemName, String itemAddress, String itemCategory, int itemPrice, double avgRating, String itemImage) {
        this.itemKey = itemKey;
        this.itemName = itemName;
        this.itemAddress = itemAddress;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
        this.avgRating = avgRating;
        this.itemImage = itemImage;
    }

    public ItemListDto() {}

    public Long getItemKey() {
        return itemKey;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public String getItemImage() {
        return itemImage;
    }
}
