package dto;

public class LoginDto {

    private String memberId;
    private String password;

    public LoginDto(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getPassword() {
        return password;
    }
}
