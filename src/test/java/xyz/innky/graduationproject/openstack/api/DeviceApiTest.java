package xyz.innky.graduationproject.openstack.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.innky.graduationproject.common.utils.api.DeviceApi;

@SpringBootTest
class DeviceApiTest {

    @Autowired
    DeviceApi deviceApi;

    @Test
    void deleteRequest() {
        deviceApi.createDevice("c610fcfe-a418-40d3-9c10-7a69f48ac6b7","测试api");
    }

    @Test
    void postJson() {
    }

    @Test
    void testDeleteRequest() {
    }

    @Test
    void createDevice() {
    }

    @Test
    void deleteDevice() {
        deviceApi.deleteDevice("e9bf5ff8-885a-484f-8d69-a47da92ef28d");
    }

    @Test
    void getVncAddress() {
        System.out.println(deviceApi.getVncAddress("f0cde80c-18e2-432f-8aa7-2d8b1b08b037"));
    }

    @Test
    void startServer() {
        deviceApi.startServer("2d71dfb3-1dfc-4721-a9a8-81ce64756f10");
    }
    //stopServer
    @Test
    void stopServer() {
        deviceApi.stopServer("f0cde80c-18e2-432f-8aa7-2d8b1b08b037");
    }

    @Test
    void createRouter() {
        deviceApi.createRouter("asdasd");
    }
    @Test
    void deleteRouter() {
        deviceApi.deleteRouter("c7897e15-edbe-48ec-87d6-52e8e1375f0a");
    }
}