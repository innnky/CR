package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.common.utils.api.DeviceApi;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/common/device")
public class CommonDeviceController {

    @Autowired
    private DeviceApi deviceApi;

    @Autowired
    private DeviceService deviceService;

    @PutMapping("/")
    public Result updateDevice(@RequestBody Device device) {
        return ResultUtil.returnResultByConditionLambda(()->deviceService.updateById(device), "修改设备");
    }
    @PostMapping("/")
    public Result addDevice(@RequestBody Device device) {
        device.setStatus(1);

        return ResultUtil.returnResultByConditionLambda(()->deviceService.addDevice(device), "添加设备");
    }
    @DeleteMapping("/{id}")
    public Result deleteDevice(@PathVariable("id") String id) {

        return ResultUtil.returnResultByConditionLambda(()->deviceService.removeDevice(id), "删除设备");
    }

    @GetMapping("/")
    public Result getAllDeviceByConditionAndPage(String page, String pageSize,
                                                 String deviceName, String imageType, String status,
                                                 Integer deviceId) {
        return Result.ok(deviceService.getAllDeviceByConditionAndPage(page, pageSize, deviceName, imageType, status, deviceId));

    }

    @PostMapping("{uuid}/start")
    public Result startDevice(@PathVariable("uuid") String uuid) {
        deviceApi.startServer(uuid);
        deviceService.startDevice(uuid);
        return Result.ok("启动设备");
    }
    @PostMapping("{uuid}/stop")
    public Result stopDevice(@PathVariable("uuid") String uuid) {
        deviceApi.stopServer(uuid);
        deviceService.stopDevice(uuid);
        return Result.ok("停止设备");
    }
    @GetMapping("{uuid}/vnc")
    public Result getVnc(@PathVariable("uuid") String uuid) {
        return new Result(200,"",deviceApi.getVncAddress(uuid));
    }
    @GetMapping("/unused")
    public Result getUnusedDevice() {
        return Result.ok(deviceService.getUnusedDevice());
    }
}