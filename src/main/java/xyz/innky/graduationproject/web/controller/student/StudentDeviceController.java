package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.StudentDeviceRelation;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.vo.Result;
import xyz.innky.graduationproject.web.vo.StudentDeviceReservation;

@RestController
@RequestMapping("/student/device")
public class StudentDeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/all/{sceneId}")
    public Result getAllDevicesByScene(@PathVariable("sceneId") Integer sceneId) {
        return Result.ok(deviceService.getAllDevicesByScene(sceneId));
    }

    @PostMapping("/{deviceId}")
    public Result getDeviceById(@PathVariable("deviceId") Integer deviceId, @RequestBody StudentDeviceReservation reservation) {
        return ResultUtil.returnResultByCondition(deviceService.addReservation(deviceId, reservation),"预约");
    }

    @GetMapping("/all")
    public Result getAllDevices() {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(deviceService.getAllDevicesByStudentId( studentId));
    }
}
