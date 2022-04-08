package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.innky.graduationproject.web.service.UserAccountService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserAccountService userService;

    @GetMapping("/menu")
    public Result getMenu(){
        return Result.ok(userService.getMenu());
    }
}
