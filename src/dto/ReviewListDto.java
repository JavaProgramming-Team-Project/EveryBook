package dto;

public class ReviewListDto {
    private Long reviewKey;
    private String reviewBody;
    private int reviewStar;
    private String reviewDate;
    private String memberName;
    private Long memberKey;

    public ReviewListDto(Long reviewKey, String reviewBody, int reviewStar, String reviewDate, String memberName, Long memberKey) {
        this.reviewKey = reviewKey;
        this.reviewBody = reviewBody;
        this.reviewStar = reviewStar;
        this.reviewDate = reviewDate;
        this.memberName = memberName;
        this.memberKey = memberKey;
    }

    public ReviewListDto() {}

    public Long getReviewKey() {
        return reviewKey;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public int getReviewStar() {
        return reviewStar;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getMemberName() {
        return memberName;
    }

    public Long getMemberKey() {
        return memberKey;
    }
}
