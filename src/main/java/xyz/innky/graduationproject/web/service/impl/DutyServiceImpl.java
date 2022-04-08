package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.innky.graduationproject.web.pojo.Duty;
import xyz.innky.graduationproject.web.service.DutyService;
import xyz.innky.graduationproject.web.mapper.DutyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【duty】的数据库操作Service实现
* @createDate 2022-04-07 08:56:20
*/
@Service
public class DutyServiceImpl extends ServiceImpl<DutyMapper, Duty>
    implements DutyService{

    @Override
    public List<Duty> searchAllByStudentId(Integer studentId) {
        return getBaseMapper().searchAllByStudentId(studentId);
    }
}




