package xyz.innky.graduationproject.web.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.UserAccountService;

@RestController
@RequestMapping("/teacher/account")
public class TeacherAccountController {
    @Autowired
    private UserAccountService userAccountService;
}
