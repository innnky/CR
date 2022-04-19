package xyz.innky.graduationproject;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import xyz.innky.graduationproject.openstack.api.ImageApi;
import xyz.innky.graduationproject.web.mapper.MenuMapper;
import xyz.innky.graduationproject.web.mapper.UserAccountMapper;
import xyz.innky.graduationproject.web.pojo.ClassInfo;
import xyz.innky.graduationproject.web.pojo.Scene;
import xyz.innky.graduationproject.web.pojo.Teacher;
import xyz.innky.graduationproject.web.service.CourseService;
import xyz.innky.graduationproject.web.service.SCourseClassRelationService;
import xyz.innky.graduationproject.web.service.SCourseService;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;
import xyz.innky.graduationproject.web.vo.MenuVo;
import xyz.innky.graduationproject.web.vo.SCourseVo;
import xyz.innky.graduationproject.web.vo.TeacherVo;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@SpringBootTest
class GraduationProjectApplicationTests {

    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    CourseService courseService;
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    SCourseClassRelationService sCourseClassRelationService;

    @Autowired
    SCourseService sCourseService;

    @Autowired
    ImageApi imageApi;

    @Test
    void contextLoads() {

    }

    @Autowired

    PasswordEncoder bCryptPasswordEncoder;
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

    @Test
    void test11(){
        Teacher teacher = new Teacher();
        TeacherVo teacherVo = new TeacherVo();
        teacher.setTeacherId(1);
        teacher.setTeacherName("张三");
        teacher.setDepartmentId(1);
        List<Teacher> teachers = Arrays.asList(teacher, teacher, teacher);
        List<TeacherVo> teacherVos;
//        teacherVos = teachers.stream().map(TeacherVo::new).collect(Collectors.toList());
        System.out.println(teacherVo);

    }
    @Test
    void tesssst(){
//        Stream<Scene> stream = Stream.of(
//                new Scene(1,"aa"),
//                new Scene(2,"bb"),
//                new Scene(3,"cc")
//        );
//        Integer reduce = stream.map(Scene::getSceneId).reduce(0,Integer::sum);
//        Stream.of(1,2,3,4,5).filter(i -> i > 3).count();
//        int times = 100;
//        long start = System.currentTimeMillis();
//        LongStream.rangeClosed(0, times).parallel().reduce(0,(x,y) -> {
//            System.out.println(x);
//            return x+y;
//        });
//
//        long end = System.currentTimeMillis();
//
//        System.out.println(end - start);
        Optional<Scene> op = Optional.ofNullable(new Scene(1,"aa"));
//        op.map(Scene::getSceneName).map()
    }

    @Test
    void testsCourseClassRelationService(){
//        sCourseClassRelationService.addSCourseClassRelation(1, Arrays.asList("1","2"));
    }
    @Test
    void testsCourseClassRelationService2(){
        SCourseVo sCourse = new SCourseVo();
        sCourse.setCourseId(11);
        sCourse.setTeacherId(2);
        sCourse.setTerm("2020年春");
//        sCourse.setClassInfos(Arrays.asList(1,2,3).stream().map(i->new ClassInfo(i, null,null,null,null,null)).collect(Collectors.toList()));
        sCourseService.addSCourse(sCourse);
    }

    @Test
    void testImageApi(){
        imageApi.createImage(new File("/Users/xingyijin/IdeaProjects/graduationProject/src/main/resources/static/cirros-0.3.0-x86_64-disk.img"), "javatest");
    }

    @Test
    void teeeasdads(){
        bCryptPasswordEncoder.matches("123456","");
    }


}
