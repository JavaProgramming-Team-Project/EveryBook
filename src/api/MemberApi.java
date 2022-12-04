package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.LoginDto;
import entity.Member;
import ip.Host;
import login.LoginMember;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MemberApi {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final HttpRequestManager HTTP_REQUEST_MANAGER = new HttpRequestManager();

    /** ---------------------------------------------------------------------------------------------------
     * 회원가입
     */
    public static void signUp(Member member) {

        String endPoint = "/user/signup";
        String requestBody;

        try {
            requestBody = mapper.writeValueAsString(member);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HTTP_REQUEST_MANAGER.postRequest(endPoint, requestBody);
    }

    /** ---------------------------------------------------------------------------------------------------
     * 로그인
     */
    public static void login(LoginDto loginDto) {

        String endPoint = "/user/login/" + loginDto.getMemberId() + "?" + "password=" + loginDto.getPassword();
        String response;
        Member member = null;

        try {
            response = HTTP_REQUEST_MANAGER.getRequest(endPoint);
            member = mapper.readValue(response, Member.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.", "EveryBook", JOptionPane.ERROR_MESSAGE);
        }

        LoginMember.setLoginMember(member);
    }

    public static void updateMemberInfo() {
        Long updateMemberKey = LoginMember.getLoginMember().getMemberKey();

        String endPoint = "/user/" + updateMemberKey;
        String response;
        Member member;

        try {
            response = HTTP_REQUEST_MANAGER.getRequest(endPoint);
            member = mapper.readValue(response, Member.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        LoginMember.setLoginMember(member);
    }
}
