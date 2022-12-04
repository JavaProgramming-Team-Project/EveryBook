package dto;

public class PointDto {
    private Long memberKey;
    private int point;

    public PointDto(Long memberKey, int point) {
        this.memberKey = memberKey;
        this.point = point;
    }

    public PointDto() {}
}
