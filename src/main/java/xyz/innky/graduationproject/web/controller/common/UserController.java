package xyz.innky.graduationproject.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.params.RegistrationParam;
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
        return ResultUtil.returnResultByConditionLambda(()->userService.changePassword(param.getOldPassword(), param.getNewPassword()),"修改密码");
    }

    @PostMapping("/registerStudent")
    public Result registerStudent(@RequestBody RegistrationParam param){
//        return ResultUtil.returnResultByConditionLambda(()->userService.registerStudent(username, password, studentNumber, studentName),"注册");
        return ResultUtil.returnResultByConditionLambda(()->userService.registerStudent(param.getUsername(), param.getPassword(), param.getNumber(), param.getName()),"注册");
    }
    @PostMapping("/registerTeacher")
    public Result registerTeacher(@RequestBody RegistrationParam param){
        return ResultUtil.returnResultByConditionLambda(()->userService.registerTeacher(param.getUsername(), param.getPassword(), param.getNumber(), param.getName()),"注册");
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
