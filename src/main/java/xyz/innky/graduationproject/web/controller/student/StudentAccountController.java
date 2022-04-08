package xyz.innky.graduationproject.web.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.UserAccountService;

@RestController
@RequestMapping("/student/account")
public class StudentAccountController {
    @Autowired
    private UserAccountService userAccountService;
}
