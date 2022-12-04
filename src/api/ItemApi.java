package api;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private static ObjectMapper mapper = new ObjectMapper();
    private static final HttpRequestManager HTTP_REQUEST_MANAGER = new HttpRequestManager();

    /** ---------------------------------------------------------------------------------------------------
     * 카테고리 별 상품 리스트
     * @param itemCategory String형 카테고리
     * @return 해당 카테고리의 상품 DTO 리스트
     */
    public static List<ItemListDto> itemListByCategory(String itemCategory) {
        List<ItemListDto> list;
        String endPoint = "/item/category/"+ URLEncoder.encode(itemCategory, StandardCharsets.UTF_8);
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            list = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
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
        String endPoint = "/item/name/"+URLEncoder.encode(itemName, StandardCharsets.UTF_8);
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            list = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
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
        String endPoint = "/item/" + itemKey;
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            item = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

    /** ---------------------------------------------------------------------------------------------------
     * 전체 item 리스트
     * @return 모든 item 리스트 반환
     */
    public static List<ItemListDto> itemList() {
        List<ItemListDto> list;
        String endPoint = "/item/list";
        String response = HTTP_REQUEST_MANAGER.getRequest(endPoint);

        try {
            list = mapper.readValue(response, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
