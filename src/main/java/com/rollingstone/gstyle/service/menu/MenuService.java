package com.rollingstone.gstyle.service.menu;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.rollingstone.gstyle.dto.menu.ResponseMenuDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    @Value("${PRIVATE.KEY}")
    private String key;

    @Value("${EDUCATION.CODE}")
    private String education_code;

    @Value("${SCHOOL.CODE}")
    private String school_code;

    private ByteBuffer buffer;
    private String encode;
    private URI uri;

//    public String getMenuJSON(String date) {
//
//    }

    public ResponseMenuDTO getMenus(String date) {
        ResponseEntity<String> json = jsonResponse(date);
        return parsingMenu(json);
    }


    //교육청 API를 통해 특정 날짜 급식 JSON을 반환
    private ResponseEntity<String> jsonResponse(String date) {
        buffer = StandardCharsets.UTF_8.encode(key);
        encode = StandardCharsets.UTF_8.decode(buffer).toString();
        uri = UriComponentsBuilder
                .fromUriString("https://open.neis.go.kr")
                .path("/hub/mealServiceDietInfo")
                .queryParam("KEY", key)
                .queryParam("Type","json")
                .queryParam("pIndex", 1)
                .queryParam("pSize", 100)
                .queryParam("ATPT_OFCDC_SC_CODE", education_code)
                .queryParam("SD_SCHUL_CODE", school_code)
                .queryParam("MLSV_YMD", date)
                .encode()
                .build()
                .toUri();

        return getResponseEntity(uri);
    }

    private ResponseMenuDTO parsingMenu(ResponseEntity<String> result) {
            ResponseMenuDTO responseMenuDTO = new ResponseMenuDTO();
        try {
                JSONParser parser = new JSONParser();
                JSONObject object = (JSONObject) parser.parse(result.getBody());
                JSONArray menuItems = (JSONArray) object.get("mealServiceDietInfo");
                object = (JSONObject) menuItems.get(1);
                menuItems = (JSONArray) object.get("row");
                object = (JSONObject) menuItems.get(0);

                String lunchDate = (String) object.get("MLSV_YMD");
                String menus = (String) object.get("DDISH_NM");
                List<String> splitMenus = Arrays.asList(menus.split("<br/>"));
                responseMenuDTO.setLunchDate(lunchDate);
                responseMenuDTO.setMenus(splitMenus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMenuDTO;
    }


    private ResponseEntity<String> getResponseEntity(URI uri) {
        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result;
    }
}
