package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.common.utils.MailUtil;
import xyz.innky.graduationproject.web.pojo.*;
//import xyz.innky.graduationproject.web.pojo.TeacherCourseClassRelation;
import xyz.innky.graduationproject.web.service.*;
import xyz.innky.graduationproject.web.mapper.CourseMapper;
import org.springframework.stereotype.Service;
import xyz.innky.graduationproject.web.vo.CourseVo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author xingyijin
* @description 针对表【course】的数据库操作Service实现
* @createDate 2022-04-06 15:13:05
*/
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseTimeService courseTimeService;
    @Autowired
    SCourseClassRelationService sCourseClassRelationService;
    @Autowired
    StudentExerciseRelationService studentExerciseRelationService;
    @Autowired
    SCourseService sCourseService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    MaterialService materialService;
    @Autowired
    UserAccountService userAccountService;
//    static.path
    @Value("${static.path}")
    private String staticPath;
//    server.address
    @Value("${server.address}")
    private String serverAddress;
//    server.port
    @Value("${server.port}")
    private String serverPort;




    @Override
    public List<Course> getAllCoursesByStuid(Integer stuid) {

//        List<Course> res = new ArrayList<>();
//        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
//        studentQueryWrapper.eq("student_id", stuid);
//        Student student = studentMapper.selectOne(studentQueryWrapper);
//        if (student!= null){
////            QueryWrapper<TeacherCourseClassRelation> queryWrapper = new QueryWrapper<>();
////            queryWrapper.eq("class_id", student.getClassId());
////            List<TeacherCourseClassRelation> relations = teacherCourseClassRelationMapper.selectList(queryWrapper);
////            for (TeacherCourseClassRelation relation : relations) {
////                QueryWrapper<Course> wrapper = new QueryWrapper<>();
////                wrapper.eq("course_id", relation.getCourseId());
////                Course course = courseMapper.selectOne(wrapper);
////                res.add(course);
////            }
            return null;
//        }
//        return res;
    }

    @Override
    public Page<Course> getAllCourse(Integer page, Integer pageSize, String courseId, String courseName) {
        Page<Course> coursePage = new Page<>(page, pageSize);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(courseId), "course_id", courseId);
        queryWrapper.like(!ObjectUtils.isEmpty(courseName), "course_name", courseName);
        getBaseMapper().selectPage(coursePage, queryWrapper);
        return coursePage;
    }

    @Override
    public List<CourseVo> getCourses(Integer studentId, String courseName) {
        Student byId = studentService.getById(studentId);
        List<SCourseClassRelation> byClassId = sCourseClassRelationService.getByClassId(byId.getClassId());
        List<CourseVo> collect = byClassId.stream().map((r) -> {
            Course courseBySid = sCourseService.getCourseBySid(r.getSCourseId());
            CourseVo courseVo = new CourseVo();
            courseVo.setCourseId(courseBySid.getCourseId());
            courseVo.setCourseName(courseBySid.getCourseName());
            courseVo.setCourseImg(courseBySid.getCourseImg());
            courseVo.setCourseDescription(courseBySid.getCourseDescription());
            courseVo.setSCourseId(r.getSCourseId());
            return courseVo;
        }).filter((course -> {
            if (courseName == null) {
                return true;
            }
            return course.getCourseName().contains(courseName);
        })).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<CourseTime> getCourseTime(Integer scid) {
        return courseTimeService.getBySCid(scid);
    }

    @Override
    public List<Exercise> getCourseExercise(Integer scid) {
        return exerciseService.getExerciseBySCid(scid);
    }

    @Override
    public List<Material> getCourseMaterial(Integer scid) {
        return materialService.getMaterialBySCid(scid);
    }

    @Override
    public List<CourseVo> getTeacherCourses(Integer teacherId) {
        return getBaseMapper().getAllByTeacherId(teacherId);
    }

    @Override
    public boolean addCourseMaterial(Integer scid, MultipartFile material) {
        String fileName = material.getOriginalFilename();
        String filePath = staticPath + "material/" + fileName;
        try {
            material.transferTo(new File(filePath));
            Material material1 = new Material();
            material1.setMaterialName(fileName);
            material1.setMaterialPath("http://"+ serverAddress +":"+ serverPort + "/material/" + fileName);
            material1.setUpdateTime(new Date(System.currentTimeMillis()));
            material1.setSCourseId(scid);
            return materialService.save(material1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addCourseExercise(Exercise exercise) {
        boolean save = exerciseService.saveExercise(exercise);
        if (save) {
            //通过scid查到所有的班级->学生
            List<Integer> classIds = sCourseClassRelationService.getCourseClass(exercise.getSCourseId()).stream().map(ClassInfo::getClassId).collect(Collectors.toList());
//            studentService.getStudentsByClass()
            List<Student> students = new ArrayList<>();
            classIds.forEach(classId -> {
                students.addAll(studentService.getStudentsByClass(classId));
            });
            //将学生关系添加进student_exercise_relation表
            Stream<StudentExerciseRelation> studentExerciseRelationStream = students.stream().map(student -> {
                UserAccount account = userAccountService.getAccountByStudentId(student.getStudentId());
                if (account != null) {
                    MailUtil.sendMail(account.getAccount(), "您有一个新的练习题", "您有一个新的练习题，请登录系统查看");
                }
                StudentExerciseRelation studentExerciseRelation = new StudentExerciseRelation();
                studentExerciseRelation.setExerciseId(exercise.getExerciseId());
                studentExerciseRelation.setStudentId(student.getStudentId());
                return studentExerciseRelation;
            });
            studentExerciseRelationService.saveBatch(studentExerciseRelationStream.collect(Collectors.toList()));
            return true;
        }
        return false;
    }

}




