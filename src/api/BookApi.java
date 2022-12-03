package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Book;
import ip.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BookApi {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final HttpRequestManager HTTP_REQUEST_MANAGER = new HttpRequestManager();

    /** ---------------------------------------------------------------------------------------------------
     * 예약 추가
     * @param book 예약 정보 book
     */
    public static void booking(Book book) {
        String endPoint = "/book";
        String requestBody;
        String response;
        try {
            requestBody = mapper.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HTTP_REQUEST_MANAGER.postRequest(endPoint, requestBody);
    }

    /** ---------------------------------------------------------------------------------------------------
     * 회원의 예약 리스트
     * @param memberKey 예약 리스트를 조회할 회원의 memberKey
     * @return memberId를 가진 회원의 예약 리스트
     */
    public static List<Book> bookList(Long memberKey) {
        List<Book> list = new ArrayList<>();

        String endPoint = "/book/list/"+ URLEncoder.encode(String.valueOf(memberKey), StandardCharsets.UTF_8);
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            list = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /** ---------------------------------------------------------------------------------------------------
     * 예약 취소
     * @param bookKey 취소할 예약의 primary key
     */
    public static void bookCancel(Long bookKey) {
        String endPoint = "/book/"+ URLEncoder.encode(String.valueOf(bookKey), StandardCharsets.UTF_8);

        HTTP_REQUEST_MANAGER.deleteRequest(endPoint);
    }
}
