package xyz.innky.graduationproject.openstack.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DeviceApi {
    @Autowired
    private LoginTokenApi api;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String postJson(String url, String json){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body).addHeader("X-Auth-Token", api.getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    String deleteRequest(String url){
        Request request = new Request.Builder()
                .url(url)
                .delete().addHeader("X-Auth-Token", api.getToken())
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createDevice(String imageUuid, String deviceName){
        String bodyJson = "{\n" +
                "    \"server\" : {\n" +
                "        \"name\" : \""+deviceName+"\",\n" +
                "        \"imageRef\" : \""+imageUuid+"\",\n" +
                "        \"flavorRef\" : \"1\",\n" +
                "        \"availability_zone\": \"nova\",\n" +
                "        \"OS-DCF:diskConfig\": \"AUTO\",\n" +
                "        \"metadata\" : {\n" +
                "            \"My Server Name\" : \"Apache1\"\n" +
                "        },\n" +
                "        \"networks\" : [{\n" +
                "            \"uuid\" : \"cb83453c-47d7-4903-8566-1e35b953121d\"\n" +
                "        }],\n"+
                "        \"security_groups\": [\n" +
                "            {\n" +
                "                \"name\": \"default\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String res = postJson(api.getDeviceUrl() + "/servers", bodyJson);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map resultMap = objectMapper.readValue(res, HashMap.class);
            Object o = ((Map) resultMap.get("server")).get("id");
            return (String) o;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public void deleteDevice(String uuid){
        deleteRequest(api.getDeviceUrl()+"/servers/"+uuid);
    }

    public String getVncAddress(String uuid){
        String body = "{\n" +
                "    \"os-getVNCConsole\": {\n" +
                "        \"type\": \"novnc\"\n" +
                "    }\n" +
                "}";
        String res = postJson(api.getDeviceUrl() + "/servers/" + uuid + "/action", body);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map resultMap = objectMapper.readValue(res, HashMap.class);
            Object o = ((Map) resultMap.get("console")).get("url");
            return (String) o;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void startServer(String uuid){
        String body = "{\n" +
                "    \"os-start\" : null\n" +
                "}";
        ///servers/{server_id}/action
        postJson(api.getDeviceUrl() + "/servers/" + uuid + "/action", body);
    }

    public void stopServer(String uuid){
        String body = "{\n" +
                "    \"os-stop\" : null\n" +
                "}";
        ///servers/{server_id}/action
        postJson(api.getDeviceUrl() + "/servers/" + uuid + "/action", body);
    }


}
