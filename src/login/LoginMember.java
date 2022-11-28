package login;

import entity.Member;

public class LoginMember {

    private static Member member;

    private Long memberKey;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private int memberAge;

    private static LoginMember loginMember = new LoginMember();

    private LoginMember() {
        member = null;
        System.out.println("loginmember ");
    }

    public static void setLoginMember(Member member) {
        LoginMember.member = member;
    }

    public static Member getLoginMember() {
        return member;
    }
}
