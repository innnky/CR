package xyz.innky.graduationproject.web.service;

import xyz.innky.graduationproject.web.pojo.Duty;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【duty】的数据库操作Service
* @createDate 2022-04-12 19:40:39
*/
public interface DutyService extends IService<Duty> {

    List<Duty> searchAllByStudentId(Integer studentId);
}
