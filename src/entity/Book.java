package entity;

public class Book {
    private Long bookKey;

    private Long memberKey;
    private Long itemKey;
    private String bookDate; //예약 시점 일자
    private String itemDate; //상품 예약일
    private int bookPrice;

    public Book(Long bookKey, Long memberKey, Long itemKey, String bookDate, String itemDate, int bookPrice) {
        this.bookKey = bookKey;
        this.memberKey = memberKey;
        this.itemKey = itemKey;
        this.bookDate = bookDate;
        this.itemDate = itemDate;
        this.bookPrice = bookPrice;
    }

    public Book(){
    }

    public Long getBookKey() {
        return bookKey;
    }

    public Long getMemberKey() {
        return memberKey;
    }

    public Long getItemKey() {
        return itemKey;
    }

    public String getBookDate() {
        return bookDate;
    }

    public String getItemDate() {
        return itemDate;
    }

    public int getBookPrice() {
        return bookPrice;
    }
}
