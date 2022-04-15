package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.UserAccount;

@Data
public class UserAccountTeacherVo extends UserAccount {

    private String teacherName;
    private String teacherNumber;

}
