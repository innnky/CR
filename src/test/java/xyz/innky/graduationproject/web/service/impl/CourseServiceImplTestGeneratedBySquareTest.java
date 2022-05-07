package xyz.innky.graduationproject.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import xyz.innky.graduationproject.web.pojo.*;
import xyz.innky.graduationproject.web.service.*;
import xyz.innky.graduationproject.web.vo.CourseVo;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CourseServiceImplTestGeneratedBySquareTest {

    private CourseServiceImpl courseServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        courseServiceImplUnderTest = new CourseServiceImpl();
        courseServiceImplUnderTest.studentService = mock(StudentService.class);
        courseServiceImplUnderTest.courseService = mock(CourseService.class);
        courseServiceImplUnderTest.courseTimeService = mock(CourseTimeService.class);
        courseServiceImplUnderTest.sCourseClassRelationService = mock(SCourseClassRelationService.class);
        courseServiceImplUnderTest.studentExerciseRelationService = mock(StudentExerciseRelationService.class);
        courseServiceImplUnderTest.sCourseService = mock(SCourseService.class);
        courseServiceImplUnderTest.exerciseService = mock(ExerciseService.class);
        courseServiceImplUnderTest.materialService = mock(MaterialService.class);
    }

    @Test
    void testGetAllCoursesByStuid() {
        // Setup
        final Course course = new Course();
        course.setCourseId(0);
        course.setCourseDescription("courseDescription");
        course.setCourseName("courseName");
        course.setCourseImg("courseImg");
        final List<Course> expectedResult = Arrays.asList(course);

        // Run the test
        final List<Course> result = courseServiceImplUnderTest.getAllCoursesByStuid(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllCourse() {
        // Setup
        // Run the test
        final Page<Course> result = courseServiceImplUnderTest.getAllCourse(0, 0, "courseId", "courseName");

        // Verify the results
    }

    @Test
    void testGetCourses() {
        // Setup
        final CourseVo courseVo = new CourseVo();
        courseVo.setCourseId(0);
        courseVo.setCourseDescription("courseDescription");
        courseVo.setCourseName("courseName");
        courseVo.setCourseImg("courseImg");
        courseVo.setSCourseId(0);
        final List<CourseVo> expectedResult = Arrays.asList(courseVo);

        // Configure StudentService.getById(...).
        final Student student = new Student();
        student.setStudentId(0);
        student.setStudentName("studentName");
        student.setClassId(0);
        student.setAdmissiondate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        student.setGender("gender");
        student.setContact("contact");
        student.setStudentNumber("studentNumber");
        when(courseServiceImplUnderTest.studentService.getById(0)).thenReturn(student);

        // Configure SCourseClassRelationService.getByClassId(...).
        final SCourseClassRelation sCourseClassRelation = new SCourseClassRelation();
        sCourseClassRelation.setId(0);
        sCourseClassRelation.setSCourseId(0);
        sCourseClassRelation.setClassId(0);
        final List<SCourseClassRelation> sCourseClassRelations = Arrays.asList(sCourseClassRelation);
        when(courseServiceImplUnderTest.sCourseClassRelationService.getByClassId(0)).thenReturn(sCourseClassRelations);

        // Configure SCourseService.getCourseBySid(...).
        final Course course = new Course();
        course.setCourseId(0);
        course.setCourseDescription("courseDescription");
        course.setCourseName("courseName");
        course.setCourseImg("courseImg");
        when(courseServiceImplUnderTest.sCourseService.getCourseBySid(0)).thenReturn(course);

        // Run the test
        final List<CourseVo> result = courseServiceImplUnderTest.getCourses(0, "courseName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourses_SCourseClassRelationServiceReturnsNoItems() {
        // Setup
        // Configure StudentService.getById(...).
        final Student student = new Student();
        student.setStudentId(0);
        student.setStudentName("studentName");
        student.setClassId(0);
        student.setAdmissiondate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        student.setGender("gender");
        student.setContact("contact");
        student.setStudentNumber("studentNumber");
        when(courseServiceImplUnderTest.studentService.getById(0)).thenReturn(student);

        when(courseServiceImplUnderTest.sCourseClassRelationService.getByClassId(0))
                .thenReturn(Collections.emptyList());

        // Configure SCourseService.getCourseBySid(...).
        final Course course = new Course();
        course.setCourseId(0);
        course.setCourseDescription("courseDescription");
        course.setCourseName("courseName");
        course.setCourseImg("courseImg");
        when(courseServiceImplUnderTest.sCourseService.getCourseBySid(0)).thenReturn(course);

        // Run the test
        final List<CourseVo> result = courseServiceImplUnderTest.getCourses(0, "courseName");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetCourseTime() {
        // Setup
        final CourseTime courseTime = new CourseTime();
        courseTime.setTimeId(0);
        courseTime.setSCourseId(0);
        courseTime.setWeekday(0);
        courseTime.setWeek(1);
        courseTime.setClassSequence(0);
        final List<CourseTime> expectedResult = Arrays.asList(courseTime);

        // Configure CourseTimeService.getBySCid(...).
        final CourseTime courseTime1 = new CourseTime();
        courseTime1.setTimeId(0);
        courseTime1.setSCourseId(0);
        courseTime1.setWeekday(0);
        courseTime1.setWeek(1);
        courseTime1.setClassSequence(0);
        final List<CourseTime> courseTimes = Arrays.asList(courseTime1);
        when(courseServiceImplUnderTest.courseTimeService.getBySCid(0)).thenReturn(courseTimes);

        // Run the test
        final List<CourseTime> result = courseServiceImplUnderTest.getCourseTime(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourseTime_CourseTimeServiceReturnsNoItems() {
        // Setup
        when(courseServiceImplUnderTest.courseTimeService.getBySCid(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<CourseTime> result = courseServiceImplUnderTest.getCourseTime(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetCourseExercise() {
        // Setup
        final Exercise exercise = new Exercise();
        exercise.setExerciseId(0);
        exercise.setContent("content");
        exercise.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setEndTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setSCourseId(0);
        exercise.setAttachmentPath("attachmentPath");
        exercise.setSceneId(0);
        final List<Exercise> expectedResult = Arrays.asList(exercise);

        // Configure ExerciseService.getExerciseBySCid(...).
        final Exercise exercise1 = new Exercise();
        exercise1.setExerciseId(0);
        exercise1.setContent("content");
        exercise1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise1.setEndTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise1.setSCourseId(0);
        exercise1.setAttachmentPath("attachmentPath");
        exercise1.setSceneId(0);
        final List<Exercise> exercises = Arrays.asList(exercise1);
        when(courseServiceImplUnderTest.exerciseService.getExerciseBySCid(0)).thenReturn(exercises);

        // Run the test
        final List<Exercise> result = courseServiceImplUnderTest.getCourseExercise(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourseExercise_ExerciseServiceReturnsNoItems() {
        // Setup
        when(courseServiceImplUnderTest.exerciseService.getExerciseBySCid(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Exercise> result = courseServiceImplUnderTest.getCourseExercise(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetCourseMaterial() {
        // Setup
        final Material material = new Material();
        material.setMaterialId(0);
        material.setMaterialName("materialName");
        material.setSCourseId(0);
        material.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        material.setMaterialPath("materialPath");
        final List<Material> expectedResult = Arrays.asList(material);

        // Configure MaterialService.getMaterialBySCid(...).
        final Material material1 = new Material();
        material1.setMaterialId(0);
        material1.setMaterialName("materialName");
        material1.setSCourseId(0);
        material1.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        material1.setMaterialPath("materialPath");
        final List<Material> materials = Arrays.asList(material1);
        when(courseServiceImplUnderTest.materialService.getMaterialBySCid(0)).thenReturn(materials);

        // Run the test
        final List<Material> result = courseServiceImplUnderTest.getCourseMaterial(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourseMaterial_MaterialServiceReturnsNoItems() {
        // Setup
        when(courseServiceImplUnderTest.materialService.getMaterialBySCid(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Material> result = courseServiceImplUnderTest.getCourseMaterial(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTeacherCourses() {
        // Setup
        final CourseVo courseVo = new CourseVo();
        courseVo.setCourseId(0);
        courseVo.setCourseDescription("courseDescription");
        courseVo.setCourseName("courseName");
        courseVo.setCourseImg("courseImg");
        courseVo.setSCourseId(0);
        final List<CourseVo> expectedResult = Arrays.asList(courseVo);

        // Run the test
        final List<CourseVo> result = courseServiceImplUnderTest.getTeacherCourses(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAddCourseMaterial() {
        // Setup
        final MultipartFile material = null;
        when(courseServiceImplUnderTest.materialService.save(new Material())).thenReturn(false);

        // Run the test
        final boolean result = courseServiceImplUnderTest.addCourseMaterial(0, material);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testAddCourseExercise() {
        // Setup
        final Exercise exercise = new Exercise();
        exercise.setExerciseId(0);
        exercise.setContent("content");
        exercise.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setEndTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setSCourseId(0);
        exercise.setAttachmentPath("attachmentPath");
        exercise.setSceneId(0);

        when(courseServiceImplUnderTest.exerciseService.saveExercise(new Exercise())).thenReturn(false);

        // Configure SCourseClassRelationService.getCourseClass(...).
        final ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(0);
        classInfo.setClassName("className");
        classInfo.setGrade("grade");
        classInfo.setMajor("major");
        classInfo.setCollege("college");
        classInfo.setHeadTeacherId(0);
        classInfo.setClassNumber("classNumber");
        final List<ClassInfo> classInfos = Arrays.asList(classInfo);
        when(courseServiceImplUnderTest.sCourseClassRelationService.getCourseClass(0)).thenReturn(classInfos);

        // Configure StudentService.getStudentsByClass(...).
        final Student student = new Student();
        student.setStudentId(0);
        student.setStudentName("studentName");
        student.setClassId(0);
        student.setAdmissiondate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        student.setGender("gender");
        student.setContact("contact");
        student.setStudentNumber("studentNumber");
        final List<Student> students = Arrays.asList(student);
//        when(courseServiceImplUnderTest.studentService.getStudentsByClass(0)).thenReturn(students);

        when(courseServiceImplUnderTest.studentExerciseRelationService.saveBatch(
                Arrays.asList(new StudentExerciseRelation()))).thenReturn(false);

        // Run the test
        final boolean result = courseServiceImplUnderTest.addCourseExercise(exercise);

        // Verify the results
        assertThat(result).isTrue();
        verify(courseServiceImplUnderTest.studentExerciseRelationService).saveBatch(
                Arrays.asList(new StudentExerciseRelation()));
    }

    @Test
    void testAddCourseExercise_SCourseClassRelationServiceReturnsNoItems() {
        // Setup
        final Exercise exercise = new Exercise();
        exercise.setExerciseId(0);
        exercise.setContent("content");
        exercise.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setEndTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setSCourseId(0);
        exercise.setAttachmentPath("attachmentPath");
        exercise.setSceneId(0);

        when(courseServiceImplUnderTest.exerciseService.saveExercise(new Exercise())).thenReturn(false);
        when(courseServiceImplUnderTest.sCourseClassRelationService.getCourseClass(0))
                .thenReturn(Collections.emptyList());

        // Configure StudentService.getStudentsByClass(...).
        final Student student = new Student();
        student.setStudentId(0);
        student.setStudentName("studentName");
        student.setClassId(0);
        student.setAdmissiondate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        student.setGender("gender");
        student.setContact("contact");
        student.setStudentNumber("studentNumber");
        final List<Student> students = Arrays.asList(student);
//        when(courseServiceImplUnderTest.studentService.getStudentsByClass(0)).thenReturn(students);

        when(courseServiceImplUnderTest.studentExerciseRelationService.saveBatch(
                Arrays.asList(new StudentExerciseRelation()))).thenReturn(false);

        // Run the test
        final boolean result = courseServiceImplUnderTest.addCourseExercise(exercise);

        // Verify the results
        assertThat(result).isTrue();
        verify(courseServiceImplUnderTest.studentExerciseRelationService).saveBatch(
                Arrays.asList(new StudentExerciseRelation()));
    }

    @Test
    void testAddCourseExercise_StudentServiceReturnsNoItems() {
        // Setup
        final Exercise exercise = new Exercise();
        exercise.setExerciseId(0);
        exercise.setContent("content");
        exercise.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setEndTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        exercise.setSCourseId(0);
        exercise.setAttachmentPath("attachmentPath");
        exercise.setSceneId(0);

        when(courseServiceImplUnderTest.exerciseService.saveExercise(new Exercise())).thenReturn(false);

        // Configure SCourseClassRelationService.getCourseClass(...).
        final ClassInfo classInfo = new ClassInfo();
        classInfo.setClassId(0);
        classInfo.setClassName("className");
        classInfo.setGrade("grade");
        classInfo.setMajor("major");
        classInfo.setCollege("college");
        classInfo.setHeadTeacherId(0);
        classInfo.setClassNumber("classNumber");
        final List<ClassInfo> classInfos = Arrays.asList(classInfo);
        when(courseServiceImplUnderTest.sCourseClassRelationService.getCourseClass(0)).thenReturn(classInfos);

        when(courseServiceImplUnderTest.studentService.getStudentsByClass(0)).thenReturn(Collections.emptyList());
        when(courseServiceImplUnderTest.studentExerciseRelationService.saveBatch(
                Arrays.asList(new StudentExerciseRelation()))).thenReturn(false);

        // Run the test
        final boolean result = courseServiceImplUnderTest.addCourseExercise(exercise);

        // Verify the results
        assertThat(result).isTrue();
        verify(courseServiceImplUnderTest.studentExerciseRelationService).saveBatch(
                Arrays.asList(new StudentExerciseRelation()));
    }
}
