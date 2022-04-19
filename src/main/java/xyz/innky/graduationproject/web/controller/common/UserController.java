package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
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

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody String oldPassword, @RequestBody String newPassword){
        return ResultUtil.returnResultByCondition(userService.changePassword(oldPassword, newPassword),"修改密码");
    }
}
