package entity;

public class Review {
    private Long reviewKey;

    private Long memberKey;
    private Long itemKey;
    private int reviewStar;
    private String reviewBody;
    private String reviewDate;

    public Review(Long reviewKey, Long memberKey, Long itemKey, int reviewStar, String reviewBody, String reviewDate) {
        this.reviewKey = reviewKey;
        this.memberKey = memberKey;
        this.itemKey = itemKey;
        this.reviewStar = reviewStar;
        this.reviewBody = reviewBody;
        this.reviewDate = reviewDate;
    }

    public Review(){}

    public Long getReviewKey() {
        return reviewKey;
    }

    public void setReviewKey(Long reviewKey) {
        this.reviewKey = reviewKey;
    }

    public Long getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(Long memberKey) {
        this.memberKey = memberKey;
    }

    public Long getItemKey() {
        return itemKey;
    }

    public void setItemKey(Long itemKey) {
        this.itemKey = itemKey;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(int reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
