package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import xyz.innky.graduationproject.web.pojo.UserAccount;

@Data
public class UserAccountStuVo extends UserAccount {

    private String studentName;
    private String studentNumber;
}
