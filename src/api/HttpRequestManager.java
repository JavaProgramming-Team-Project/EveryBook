package api;

import entity.Member;
import ip.Host;
import login.LoginMember;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class HttpRequestManager {

    private final String HOST = Host.getHost();

    /** --------------------------------------------------------------------------------------------------------
     * GET Request Method
     * @param endPoint End Point : String
     * @return response : String
     */
    public String getRequest(String endPoint) throws IllegalArgumentException {
        String hostUrl = HOST + endPoint;

        return getResponse(hostUrl, "GET", null);
    }

    /** --------------------------------------------------------------------------------------------------------
     * POST Request Method
     * @param endPoint End Point : String
     * @param requestBody Request Body : String
     * @return response : String
     */
    public String postRequest(String endPoint, String requestBody) {
        String hostUrl = HOST + endPoint;

        return getResponse(hostUrl, "POST", requestBody);
    }

    /** --------------------------------------------------------------------------------------------------------
     * PUT Request Method
     * @param endPoint End Point : String
     * @return response : String
     */
    /*public String putRequest(String endPoint) {
        String hostUrl = HOST + endPoint;

        return getResponse(hostUrl, "PUT", );
    }*/

    /** --------------------------------------------------------------------------------------------------------
     * DELETE Request Method
     * @param endPoint End Point : String
     * @return response : String
     */
    public String deleteRequest(String endPoint) {
        String hostUrl = HOST + endPoint;

        return getResponse(hostUrl, "DELETE", null);
    }

    /** --------------------------------------------------------------------------------------------------------
     * Get Response
     * @param hostUrl URL : String
     * @param requestMethod Request Method : String
     * @param requestBody Request Body : String
     * @return response : String
     */
    public String getResponse(String hostUrl, String requestMethod, String requestBody) {
        String response;

        try {
            HttpURLConnection conn;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(requestMethod);
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            if (requestMethod.equals("POST")) {
                conn.setDoOutput(true); //POST

                OutputStream os = conn.getOutputStream();
                os.write(requestBody.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            response = br.readLine();
            System.out.println("응답 메시지 : " + response);

            br.close();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
