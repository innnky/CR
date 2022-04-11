package xyz.innky.graduationproject.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.SpecificCourseService;

@RestController
@RequestMapping("/admin/scourse")
public class SCourseController {
    @Autowired
    private SpecificCourseService specificCourseService;




}
