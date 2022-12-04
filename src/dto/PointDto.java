package dto;

public class PointDto {
    private Long memberKey;
    private int point; //추가, 또는 삭감 될 포인트 (모두 양수로 입력)

    public PointDto(Long memberKey, int point) {
        this.memberKey = memberKey;
        this.point = point;
    }

    public PointDto() {}
}
