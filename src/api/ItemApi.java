package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ItemListDto;
import entity.Item;
import ip.Host;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ItemApi {

    private final static String HOST = Host.getHost();
    private static ObjectMapper mapper = new ObjectMapper();

    /** ---------------------------------------------------------------------------------------------------
     * 카테고리 별 상품 리스트
     * @param itemCategory String형 카테고리
     * @return 해당 카테고리의 상품 DTO 리스트
     */
    public static List<ItemListDto> itemListByCategory(String itemCategory) {
        List<ItemListDto> list;
        try {
            String hostUrl = HOST + "/item/category/"+ URLEncoder.encode(itemCategory, StandardCharsets.UTF_8);
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            list = mapper.readValue(br.readLine(), new TypeReference<>() {});
            br.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /** ---------------------------------------------------------------------------------------------------
     * 검색어가 이름에 포함된 item 리스트 검색
     * @param itemName String 형 itemName
     * @return 검색어가 이름에 포함된 Item 객체들의 리스트
     */
    public static List<ItemListDto> findItemByName(String itemName) {
        List<ItemListDto> list;

        try {
            String hostUrl = HOST + "/item/name/"+URLEncoder.encode(itemName, StandardCharsets.UTF_8);
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            list = mapper.readValue(br.readLine(), new TypeReference<>() {});
            br.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /** ---------------------------------------------------------------------------------------------------
     * primaryKey로 item 검색
     * @param itemKey Long형 item key
     * @return itemKey값이 primaryKey 값인 item
     */
    public static Item findItemByKey(Long itemKey) {
        Item item;

        try {
            String hostUrl = HOST + "/item/" + itemKey;
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            item = mapper.readValue(br.readLine(), new TypeReference<>() {});
            br.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    /** ---------------------------------------------------------------------------------------------------
     * 전체 item 리스트
     * @return 모든 item 리스트 반환
     */
    public List<ItemListDto> itemList() {
        List<ItemListDto> list;

        try {
            String hostUrl = HOST + "item/list";
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            list = mapper.readValue(br.readLine(), new TypeReference<>() {});
            br.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
