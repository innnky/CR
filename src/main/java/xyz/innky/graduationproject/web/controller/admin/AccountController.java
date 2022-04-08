package xyz.innky.graduationproject.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.UserAccount;
import xyz.innky.graduationproject.web.service.UserAccountService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/")
    public Result addAccount(UserAccount userAccount) {
        return ResultUtil.returnResultByCondition(userAccountService.save(userAccount), "添加账户");
    }
    @PutMapping("/")
    public Result updateAccount(UserAccount userAccount) {
        return ResultUtil.returnResultByCondition(userAccountService.updateById(userAccount), "更新账户");
    }
    @DeleteMapping("/{id}")
    public Result deleteAccount(@PathVariable("id") Long id) {
        return ResultUtil.returnResultByCondition(userAccountService.removeById(id), "删除账户");
    }
    @GetMapping("/student/")
    public Result getStudentAccounts(Integer page, Integer pageSize,
                               String studentName, Integer studentId, String account) {
        return Result.ok(userAccountService.getStudentAccounts(page, pageSize, studentName, studentId, account));
    }
    @GetMapping("/teacher/")
    public Result getTeacherAccounts(Integer page, Integer pageSize,
                               String teacherName, Integer teacherId, String account) {
            return Result.ok(userAccountService.getTeacherAccounts(page, pageSize, teacherName, teacherId, account)
            );
    }
    @GetMapping("/admin/")
    public Result getAccounts(Integer page, Integer pageSize,
                               String account) {
        return Result.ok(userAccountService.getAdminAccounts(page, pageSize,account));
    }
}
