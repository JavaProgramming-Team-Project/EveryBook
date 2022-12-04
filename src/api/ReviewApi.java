package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ReviewListDto;
import entity.Review;
import ip.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ReviewApi {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final HttpRequestManager HTTP_REQUEST_MANAGER = new HttpRequestManager();

    /** ---------------------------------------------------------------------------------------------------
     * 리뷰 작성
     * @param review review 객체
     */
    public static void write(entity.Review review) {
        String endPoint = "/review";
        String requestBody;
        try {
            requestBody = mapper.writeValueAsString(review);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HTTP_REQUEST_MANAGER.postRequest(endPoint, requestBody);
    }

    /** ---------------------------------------------------------------------------------------------------
     * item별 review 리스트
     * @param itemKey review 리스트를 불러올 item의 itemKey
     * @return List<Review> 형식
     */
    public static List<ReviewListDto> reviewList(Long itemKey) {
        List<ReviewListDto> list;
        String endPoint = "/review/item/"+itemKey;
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            list = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /** ---------------------------------------------------------------------------------------------------
     * review 삭제
     * @param reviewKey 삭제할 reivew의 reviewKey
     */
    public static void deleteReview(Long reviewKey) {
        String endPoint = "/review/" + reviewKey;

        HTTP_REQUEST_MANAGER.deleteRequest(endPoint);
    }
}
