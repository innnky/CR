package xyz.innky.graduationproject.openstack.api;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginTokenApi {
    private final String baseUrl;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    final OkHttpClient client = new OkHttpClient();
    private String token;
    private String deviceUrl;
    private String imageUrl;

    public LoginTokenApi(String baseUrl) {
        this.baseUrl=baseUrl;
        String bodyJson = "{\n" +
                "    \"auth\": {\n" +
                "        \"identity\": {\n" +
                "            \"methods\": [\n" +
                "                \"password\"\n" +
                "            ],\n" +
                "            \"password\": {\n" +
                "                \"user\": {\n" +
                "                    \"domain\": {\n" +
                "                        \"name\": \"default\"\n" +
                "                    },\n" +
                "                    \"name\": \"admin\",\n" +
                "                    \"password\": \"4f9da04bf80d4969\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        \"scope\": {\n" +
                "            \"project\": {\n" +
                "                \"domain\": {\n" +
                "                    \"name\": \"default\"\n" +
                "                },\n" +
                "                \"name\": \"admin\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String url = "http://"+ this.baseUrl +":5000/v3/auth/tokens";
        ObjectMapper objectMapper = new ObjectMapper();
        String result = post(url, bodyJson);
        try {
            Map resultMap = objectMapper.readValue(result, HashMap.class);
            List list = (List) ((Map) resultMap.get("token")).get("catalog");
            this.deviceUrl = (String) ((Map)((List)((Map)list.get(3)).get("endpoints")).get(0)).get("url");
            this.imageUrl = (String) ((Map)((List)((Map)list.get(10)).get("endpoints")).get(0)).get("url");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    String post(String url, String json) {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            this.token = response.header("X-Subject-Token");
            System.out.println(this.token);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getToken() {
        return token;
    }

    public String getDeviceUrl() {
        return deviceUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
