package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PointDto;

public class PointApi {

    private static ObjectMapper mapper = new ObjectMapper();
    private static final HttpRequestManager HTTP_REQUEST_MANAGER = new HttpRequestManager();

    /** --------------------------------------------------------------------------------------------------
     * 포인트 충전
     */
    public static void addPoint(PointDto pointDto) {
        String endPoint = "/point/add";
        String requestBody;

        try {
            requestBody = mapper.writeValueAsString(pointDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HTTP_REQUEST_MANAGER.putRequest(endPoint, requestBody);

        MemberApi.updateMemberInfo();
    }

    /** --------------------------------------------------------------------------------------------------
     * 포인트 사용
     */
    /*public static void usePoint(PointDto pointDto) {
        String endPoint = "/point/use";
        String requestBody;

        try {
            requestBody = mapper.writeValueAsString(pointDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HTTP_REQUEST_MANAGER.putRequest(endPoint, requestBody);

        MemberApi.updateMemberInfo();
    }*/
}
