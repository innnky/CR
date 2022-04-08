package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Image;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/common/device")
public class CommonDeviceController {

    @Autowired
    private DeviceService deviceService;

    @PutMapping("/")
    public Result updateDevice(@RequestBody Device device) {
        return ResultUtil.returnResultByCondition(deviceService.updateById(device), "修改设备");
    }
    @PostMapping("/")
    public Result addDevice(@RequestBody Device device) {
        return ResultUtil.returnResultByCondition(deviceService.save(device), "添加设备");
    }
    @DeleteMapping("/{id}")
    public Result deleteDevice(@PathVariable("id") String id) {
        return ResultUtil.returnResultByCondition(deviceService.removeById(id), "删除设备");
    }

    @GetMapping("/")
    public Result getAllDeviceByConditionAndPage(String page, String pageSize,
                                                 String deviceName, String imageType, String status,
                                                 Integer deviceId) {
        return Result.ok(deviceService.getAllDeviceByConditionAndPage(page, pageSize, deviceName, imageType, status, deviceId));

    }

}