package xyz.innky.graduationproject.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import xyz.innky.graduationproject.web.vo.UserAccountDetail;

public class AccountUtil {
    public static Integer getUserId() {
        UserAccountDetail principal = (UserAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserAccount().getUid();
    }
    public static Integer getStudentId() {
        UserAccountDetail principal = (UserAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserAccount().getStudentId();
    }
    public static Integer getTeacherId() {
        UserAccountDetail principal = (UserAccountDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserAccount().getTeacherId();
    }

}
