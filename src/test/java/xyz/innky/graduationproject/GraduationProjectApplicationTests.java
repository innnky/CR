package xyz.innky.graduationproject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.innky.graduationproject.web.mapper.UserAccountMapper;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;

@SpringBootTest
class GraduationProjectApplicationTests {

    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    CourseService courseService;

    @Test
    void contextLoads() {

    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    void test(){
        System.out.println(bCryptPasswordEncoder.encode("123456"));
    }

    @Test
    void testCourseService(){
        System.out.println(courseService.getAllCoursesByStuid(1));
    }

    @Test
    void testWrapper(){
        QueryWrapper<ClassInfoVo> infoVoQueryWrapper = new QueryWrapper<>();
        infoVoQueryWrapper.eq("class_id", 1).or().eq("class_id", 2);
        System.out.println(infoVoQueryWrapper.getSqlSegment());
    }

}
