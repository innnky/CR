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
    public Result changePassword(@RequestBody Param param){
        System.out.println("asdasdasdasdasd"+param.toString());
        return ResultUtil.returnResultByCondition(userService.changePassword(param.getOldPassword(), param.getNewPassword()),"修改密码");
    }
    static class Param{
        private String oldPassword;
        private String newPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        @Override
        public String toString() {
            return "Param{" +
                    "oldPassword='" + oldPassword + '\'' +
                    ", newPassword='" + newPassword + '\'' +
                    '}';
        }
    }
}
