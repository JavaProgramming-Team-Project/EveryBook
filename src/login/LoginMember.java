package login;

import entity.Member;

public class LoginMember {

    private static Member member;

    private static LoginMember loginMember = new LoginMember();

    private LoginMember() {
        member = null;
    }

    public static void setLoginMember(Member member) {
        LoginMember.member = member;
    }

    public static Member getLoginMember() {
        return member;
    }
}
