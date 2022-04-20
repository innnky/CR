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
    public Result getDeviceById(@PathVariable("deviceId") Integer deviceId, @RequestBody StudentDeviceRelation reservation) {
        reservation.setDeviceId(deviceId);
        reservation.setStudentId(AccountUtil.getStudentId());
        return ResultUtil.returnResultByCondition(deviceService.addReservation(reservation),"预约");
    }

    @GetMapping("/all")
    public Result getAllDevices() {
        Integer studentId = AccountUtil.getStudentId();
        return Result.ok(deviceService.getAllDevicesByStudentId( studentId));
    }

    @GetMapping("/{deviceId}/vnc")
    public Result getVncUrl(@PathVariable("deviceId") Integer deviceId) {
        return Result.ok(deviceService.getVncUrl(deviceId));
    }

    @DeleteMapping("/{deviceId}")
    public Result deleteReservation(@PathVariable("deviceId") Integer deviceId) {

        return ResultUtil.returnResultByCondition(deviceService.deleteReservation(deviceId, AccountUtil.getStudentId()),"取消");
    }

    @GetMapping("{deviceId}/reservation")
    public Result getReservation(@PathVariable("deviceId") Integer deviceId) {
        return Result.ok(deviceService.getReservation(deviceId, AccountUtil.getStudentId()));
    }
}
