package xyz.innky.graduationproject.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.innky.graduationproject.web.pojo.Menu;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.pojo.UserAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.innky.graduationproject.web.vo.MenuVo;
import xyz.innky.graduationproject.web.vo.UserAccountStuVo;
import xyz.innky.graduationproject.web.vo.UserAccountTeacherVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【user_account(用户账号表)】的数据库操作Service
* @createDate 2022-04-06 15:13:05
*/
public interface UserAccountService extends IService<UserAccount>, UserDetailsService {

    Page<UserAccount> getAdminAccounts(Integer page, Integer pageSize, String account);

    Page<UserAccountStuVo> getStudentAccounts(Integer page, Integer pageSize, String studentName, Integer studentId, String account);

    Page<UserAccountTeacherVo> getTeacherAccounts(Integer page, Integer pageSize, String teacherName, Integer teacherId, String account);

    List<MenuVo> getMenu();

    boolean changePassword(String oldPassword, String newPassword);
}
