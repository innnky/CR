package xyz.innky.graduationproject.web.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import xyz.innky.graduationproject.web.pojo.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.UserAccountStuVo;
import xyz.innky.graduationproject.web.vo.UserAccountTeacherVo;

/**
* @author xingyijin
* @description 针对表【user_account(用户账号表)】的数据库操作Mapper
* @createDate 2022-04-06 15:13:05
* @Entity xyz.innky.graduationproject.web.pojo.UserAccount
*/
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    List<UserAccount> selectAllByAccount(@Param("account") String account);

    Page<UserAccountStuVo> selectStuPageVo(@Param("userAccountPage") Page<UserAccountStuVo> userAccountPage, @Param("ew") Wrapper<UserAccountStuVo> ew);

    Page<UserAccountTeacherVo> selectTeacherPageVo(@Param("userAccountPage") Page<UserAccountTeacherVo> userAccountPage, @Param("ew") Wrapper<UserAccountTeacherVo> ew);

    UserAccount getOneByStudentId(@Param("studentId") Integer studentId);
}




