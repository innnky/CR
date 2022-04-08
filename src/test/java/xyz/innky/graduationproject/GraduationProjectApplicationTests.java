package xyz.innky.graduationproject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xyz.innky.graduationproject.web.mapper.MenuMapper;
import xyz.innky.graduationproject.web.mapper.UserAccountMapper;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;
import xyz.innky.graduationproject.web.vo.MenuVo;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class GraduationProjectApplicationTests {

    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    CourseService courseService;
    @Autowired
    MenuMapper menuMapper;


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

    @Test
    void testMenuMapper(){
        List<MenuVo> listByMenuIds = menuMapper.getListByMenuIds(Arrays.asList(15,16,17,18,19));
        System.out.println(listByMenuIds);
        System.out.println(111);
    }

}
