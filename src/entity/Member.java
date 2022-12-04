package entity;

public class Member {
    private Long memberKey;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private int memberAge;
    private int memberPoint;

    public Member(Long memberKey, String memberId, String memberPassword, String memberName, String memberPhone
            , int memberAge, int memberPoint) {
        this.memberKey = memberKey;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberAge = memberAge;
        this.memberPoint = memberPoint;
    }

    public Member(){}

    public Long getMemberKey() {
        return memberKey;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public int getMemberAge() {
        return memberAge;
    }

    public int getMemberPoint() {
        return memberPoint;
    }

    public void setMemberKey(Long memberKey) {
        this.memberKey = memberKey;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public void setMemberAge(int memberAge) {
        this.memberAge = memberAge;
    }
}
