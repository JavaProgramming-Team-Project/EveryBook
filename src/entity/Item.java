package entity;

public class Item {
    private Long itemKey;

    private String itemName;
    private String itemBody;
    private int itemPrice;
    private String itemAddress;
    private String itemCategory;
    private String itemPhone;
    private String itemImage;
    private double avgRating;
    private String itemImage;

    public Item(Long itemKey, String itemName, String itemBody, int itemPrice, String itemAddress, String itemCategory, String itemPhone, String itemImage, double avgRating, String itemImage1) {
        this.itemKey = itemKey;
        this.itemName = itemName;
        this.itemBody = itemBody;
        this.itemPrice = itemPrice;
        this.itemAddress = itemAddress;
        this.itemCategory = itemCategory;
        this.itemPhone = itemPhone;
        this.itemImage = itemImage;
        this.avgRating = avgRating;
        this.itemImage = itemImage1;
    }

    public Item(){}

    public Long getItemKey() {
        return itemKey;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemBody() {
        return itemBody;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public String getItemAddress() {
        return itemAddress;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemPhone() {
        return itemPhone;
    }

    public String getItemImage() {
        return itemImage;
    }

    public double getAvgRating() {
        return avgRating;

    }
}