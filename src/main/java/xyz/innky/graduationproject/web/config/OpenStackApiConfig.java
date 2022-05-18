package xyz.innky.graduationproject.web.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.innky.graduationproject.openstack.api.LoginTokenApi;

@Configuration
public class OpenStackApiConfig {

    @Value("${openstack.baseurl}")
    String serverBaseUrl;

    @Bean
    public LoginTokenApi loginTokenApi(){
        try {
            return new LoginTokenApi(serverBaseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginTokenApi();
    }

}
