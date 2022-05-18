package xyz.innky.graduationproject.common.utils.api;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImageApi {
    @Autowired
    LoginTokenApi api;
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
    String putFile(String url, File file){
        RequestBody requestBody = RequestBody.create(file,MediaType.parse("application/octet-stream"));
        Request request = new Request.Builder()
                .url(url)
                .put(requestBody).addHeader("X-Auth-Token", api.getToken())
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

    public String createImage(File file, String name){
        String id = UUID.randomUUID().toString();
        String bodyJson = "{\n" +
                "    \"container_format\": \"bare\",\n" +
                "    \"disk_format\": \"qcow2\",\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"id\": \""+ id +"\"\n" +
                "}";
        String s = postJson(api.getImageUrl() + "/v2/images", bodyJson);
        System.out.println(s);
        putFile(api.getImageUrl()+"/v2/images/"+id+"/file",file);
        file.delete();
        return id;
    }

    public void deleteImage(String uuid){
        deleteRequest(api.getImageUrl()+"/v2/images/"+uuid);
    }


}
