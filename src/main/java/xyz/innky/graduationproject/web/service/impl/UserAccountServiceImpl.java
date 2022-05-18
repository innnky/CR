package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import xyz.innky.graduationproject.common.utils.AccountUtil;
import xyz.innky.graduationproject.web.pojo.*;
import xyz.innky.graduationproject.web.service.*;
import xyz.innky.graduationproject.web.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.MenuVo;
import xyz.innky.graduationproject.web.vo.UserAccountDetail;
import xyz.innky.graduationproject.web.vo.UserAccountStuVo;
import xyz.innky.graduationproject.web.vo.UserAccountTeacherVo;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xingyijin
* @description 针对表【user_account(用户账号表)】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount>
    implements UserAccountService{

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    RoleMenuRelationService roleMenuRelationService;
    @Autowired
    MenuService menuService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<UserAccount> userAccounts = getBaseMapper().selectAllByAccount(s);
        if (userAccounts.size() == 1) {
            UserAccountDetail userAccountDetail = new UserAccountDetail();
            userAccountDetail.setUserAccount(userAccounts.get(0));
            return userAccountDetail;
        }
        return null;
    }

    @Override
    public Page<UserAccount> getAdminAccounts(Integer page, Integer pageSize, String account) {
        Page<UserAccount> userAccountPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!ObjectUtils.isEmpty(account), UserAccount::getAccount, account);
        queryWrapper.eq(UserAccount::getRoleId, 3);
        return getBaseMapper().selectPage(userAccountPage, queryWrapper);
    }

    @Override
    public Page<UserAccountStuVo> getStudentAccounts(Integer page, Integer pageSize, String studentName, Integer studentId, String account) {
        Page<UserAccountStuVo> userAccountPage = new Page<>(page, pageSize);
        List<Student> students = studentService.getAllStudentsByName(studentName);
        //将students每一个元素的学生id放入List中
        if (students.size() ==0) {
            return userAccountPage;
        }
        List<Integer> studentIds = students.stream().map(Student::getStudentId).collect(java.util.stream.Collectors.toList());
        LambdaQueryWrapper<UserAccountStuVo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(!ObjectUtils.isEmpty(studentIds), UserAccountStuVo::getStudentId, studentIds);
        queryWrapper.like(!ObjectUtils.isEmpty(account), UserAccountStuVo::getAccount, account);
        queryWrapper.eq(!ObjectUtils.isEmpty(studentId), UserAccountStuVo::getStudentId, studentId);
        Page<UserAccountStuVo> userAccountStuVoPage = getBaseMapper().selectStuPageVo(userAccountPage, queryWrapper);
        for (UserAccountStuVo record : userAccountStuVoPage.getRecords()) {
            Student student = studentService.getById(record.getStudentId());
            record.setStudentName(student.getStudentName());
            record.setStudentNumber(student.getStudentNumber());
        }
        return userAccountStuVoPage;
    }

    @Override
    public Page<UserAccountTeacherVo> getTeacherAccounts(Integer page, Integer pageSize, String teacherName, Integer teacherId, String account) {
        Page<UserAccountTeacherVo> userAccountPage = new Page<>(page, pageSize);
        List<Teacher> teachers = teacherService.getAllTeachersByName(teacherName);
        //将teachers每一个元素的教师id放入List中
        if (teachers.size() ==0) {
            return userAccountPage;
        }
        List<Integer> teacherIds = teachers.stream().map(Teacher::getTeacherId).collect(java.util.stream.Collectors.toList());
        LambdaQueryWrapper<UserAccountTeacherVo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(!ObjectUtils.isEmpty(teacherIds), UserAccountTeacherVo::getTeacherId, teacherIds);
        queryWrapper.like(!ObjectUtils.isEmpty(account), UserAccountTeacherVo::getAccount, account);
        queryWrapper.eq(!ObjectUtils.isEmpty(teacherId), UserAccountTeacherVo::getTeacherId, teacherId);
        Page<UserAccountTeacherVo> userAccountTeacherVoPage = getBaseMapper().selectTeacherPageVo(userAccountPage, queryWrapper);
        for (UserAccountTeacherVo record : userAccountTeacherVoPage.getRecords()) {
            Teacher teacher = teacherService.getById(record.getTeacherId());
            record.setTeacherName(teacher.getTeacherName());
            record.setTeacherNumber(teacher.getTeacherNumber());
        }
        return userAccountTeacherVoPage;

    }

    @Override
    public List<MenuVo> getMenu() {
        UserAccount userAccount = getBaseMapper().selectById(AccountUtil.getUserId());
        List<RoleMenuRelation> listByRoleId = roleMenuRelationService.getListByRoleId(userAccount.getRoleId());
        List<Integer> menuIds = listByRoleId.stream().map(RoleMenuRelation::getMenuId).collect(Collectors.toList());
        return menuService.getMenusByMenuIds(menuIds);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        UserAccountDetail userAccountDetail = AccountUtil.getUserAccountDetail();
        if(passwordEncoder.matches(oldPassword,userAccountDetail.getPassword())){
            UserAccount userAccount = userAccountDetail.getUserAccount();
            userAccount.setPassword(passwordEncoder.encode(newPassword));
            return this.updateById(userAccount);
        }
        return false;
    }

    @Override
    public boolean registerStudent(String username, String password, String studentNumber, String studentName) {
        Student student = studentService.getByStudentNumberAndStudentName(studentNumber, studentName);
        if (student != null) {
            UserAccount userAccount = new UserAccount();
            userAccount.setAccount(username);
            userAccount.setPassword(passwordEncoder.encode(password));
            userAccount.setRoleId(2);
            userAccount.setStudentId(student.getStudentId());
            return getBaseMapper().insert(userAccount) > 0;
        }
        return false;
    }

    @Override
    public boolean registerTeacher(String username, String password, String teacherNumber, String teacherName) {
        Teacher teacher = teacherService.getByTeacherNumberAndTeacherName(teacherNumber, teacherName);
        if (teacher != null) {
            UserAccount userAccount = new UserAccount();
            userAccount.setAccount(username);
            userAccount.setPassword(passwordEncoder.encode(password));
            userAccount.setRoleId(1);
            userAccount.setTeacherId(teacher.getTeacherId());
            return getBaseMapper().insert(userAccount) > 0;

        }
        return false;
    }

    @Override
    public UserAccount getAccountByStudentId(Integer studentId) {
        return getBaseMapper().getOneByStudentId(studentId);
    }
}




