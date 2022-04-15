package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.DeviceService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/student/device")
public class StudentDeviceController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/all/{sceneId}")
    public Result getAllDevicesByScene(@PathVariable("sceneId") Integer sceneId) {
        return Result.ok(deviceService.getAllDevicesByScene(sceneId));
    }
}
