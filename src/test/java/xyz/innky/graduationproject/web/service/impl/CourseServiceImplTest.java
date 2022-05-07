package xyz.innky.graduationproject.web.service.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import xyz.innky.graduationproject.web.pojo.Course;
import xyz.innky.graduationproject.web.pojo.Exercise;

class CourseServiceImplTest {
    /**
     * Method under test: {@link CourseServiceImpl#getAllCourse(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCourse() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getAllCourse(CourseServiceImpl.java:102)
        //   In order to prevent getAllCourse(Integer, Integer, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAllCourse(Integer, Integer, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getAllCourse(1, 3, "42", "Course Name");
    }

    /**
     * Method under test: {@link CourseServiceImpl#getAllCourse(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCourse2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getAllCourse(CourseServiceImpl.java:102)
        //   In order to prevent getAllCourse(Integer, Integer, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAllCourse(Integer, Integer, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getAllCourse(1, 3, "", "Course Name");
    }

    /**
     * Method under test: {@link CourseServiceImpl#getAllCourse(Integer, Integer, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCourse3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getAllCourse(CourseServiceImpl.java:102)
        //   In order to prevent getAllCourse(Integer, Integer, String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAllCourse(Integer, Integer, String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getAllCourse(1, 3, "42", "");
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourses(Integer, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCourses() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getCourses(CourseServiceImpl.java:108)
        //   In order to prevent getCourses(Integer, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCourses(Integer, String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getCourses(123, "Course Name");
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseTime(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCourseTime() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getCourseTime(CourseServiceImpl.java:130)
        //   In order to prevent getCourseTime(Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCourseTime(Integer).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getCourseTime(1);
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseExercise(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCourseExercise() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getCourseExercise(CourseServiceImpl.java:135)
        //   In order to prevent getCourseExercise(Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCourseExercise(Integer).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getCourseExercise(1);
    }

    /**
     * Method under test: {@link CourseServiceImpl#getCourseMaterial(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCourseMaterial() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getCourseMaterial(CourseServiceImpl.java:140)
        //   In order to prevent getCourseMaterial(Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getCourseMaterial(Integer).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getCourseMaterial(1);
    }

    /**
     * Method under test: {@link CourseServiceImpl#getTeacherCourses(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetTeacherCourses() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.getTeacherCourses(CourseServiceImpl.java:145)
        //   In order to prevent getTeacherCourses(Integer)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getTeacherCourses(Integer).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).getTeacherCourses(123);
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseMaterial(Integer, org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCourseMaterial() throws UnsupportedEncodingException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: F009 Internal error.
        //   java.lang.IllegalArgumentException: SpringReflectiveBackend: No bean found for field declaration: FieldDeclaration{declaringClass=com.diffblue.tir.utils.TestClassForwardDeclaration, type=xyz.innky.graduationproject.web.service.impl.CourseServiceImpl, name='courseServiceImpl', annotations=[Annotation{kind=AUTOWIRED, params={}}]}
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access files outside the temporary directory (file 'nullmaterial', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        courseServiceImpl.addCourseMaterial(1, new MockMultipartFile("Name", "AAAAAAAA".getBytes("UTF-8")));
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseMaterial(Integer, org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCourseMaterial2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: F009 Internal error.
        //   java.lang.IllegalArgumentException: SpringReflectiveBackend: No bean found for field declaration: FieldDeclaration{declaringClass=com.diffblue.tir.utils.TestClassForwardDeclaration, type=xyz.innky.graduationproject.web.service.impl.CourseServiceImpl, name='courseServiceImpl', annotations=[Annotation{kind=AUTOWIRED, params={}}]}
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.addCourseMaterial(CourseServiceImpl.java:150)
        //   In order to prevent addCourseMaterial(Integer, MultipartFile)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCourseMaterial(Integer, MultipartFile).
        //   See https://diff.blue/R013 to resolve this issue.

        (new CourseServiceImpl()).addCourseMaterial(1, null);
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseMaterial(Integer, org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCourseMaterial3() throws IOException, IllegalStateException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: F009 Internal error.
        //   java.lang.IllegalArgumentException: SpringReflectiveBackend: No bean found for field declaration: FieldDeclaration{declaringClass=com.diffblue.tir.utils.TestClassForwardDeclaration, type=xyz.innky.graduationproject.web.service.impl.CourseServiceImpl, name='courseServiceImpl', annotations=[Annotation{kind=AUTOWIRED, params={}}]}
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.addCourseMaterial(CourseServiceImpl.java:159)
        //   In order to prevent addCourseMaterial(Integer, MultipartFile)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCourseMaterial(Integer, MultipartFile).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        MockMultipartFile mockMultipartFile = mock(MockMultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("foo.txt");
        doNothing().when(mockMultipartFile).transferTo((java.io.File) any());
        courseServiceImpl.addCourseMaterial(1, mockMultipartFile);
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseMaterial(Integer, org.springframework.web.multipart.MultipartFile)}
     */
    @Test
    void testAddCourseMaterial4() throws IOException, IllegalStateException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: F009 Internal error.
        //   java.lang.IllegalArgumentException: SpringReflectiveBackend: No bean found for field declaration: FieldDeclaration{declaringClass=com.diffblue.tir.utils.TestClassForwardDeclaration, type=xyz.innky.graduationproject.web.service.impl.CourseServiceImpl, name='courseServiceImpl', annotations=[Annotation{kind=AUTOWIRED, params={}}]}
        //   Please contact Diffblue through the appropriate support channel
        //   (https://www.diffblue.com/support) providing details about this error.

        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        MockMultipartFile mockMultipartFile = mock(MockMultipartFile.class);
        when(mockMultipartFile.getOriginalFilename()).thenReturn("foo.txt");
        doThrow(new IOException("An error occurred")).when(mockMultipartFile).transferTo((java.io.File) any());
        assertFalse(courseServiceImpl.addCourseMaterial(1, mockMultipartFile));
        verify(mockMultipartFile).getOriginalFilename();
        verify(mockMultipartFile).transferTo((java.io.File) any());
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseExercise(Exercise)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCourseExercise() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.addCourseExercise(CourseServiceImpl.java:169)
        //   In order to prevent addCourseExercise(Exercise)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCourseExercise(Exercise).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();

        Exercise exercise = new Exercise();
        exercise.setAttachmentPath("Attachment Path");
        exercise.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        exercise.setEndTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        exercise.setExerciseId(123);
        exercise.setSCourseId(123);
        exercise.setSceneId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        exercise.setStartTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        courseServiceImpl.addCourseExercise(exercise);
    }

    /**
     * Method under test: {@link CourseServiceImpl#addCourseExercise(Exercise)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCourseExercise2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at xyz.innky.graduationproject.web.service.impl.CourseServiceImpl.addCourseExercise(CourseServiceImpl.java:169)
        //   In order to prevent addCourseExercise(Exercise)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCourseExercise(Exercise).
        //   See https://diff.blue/R013 to resolve this issue.

        CourseServiceImpl courseServiceImpl = new CourseServiceImpl();
        Exercise exercise = mock(Exercise.class);
        doNothing().when(exercise).setAttachmentPath((String) any());
        doNothing().when(exercise).setContent((String) any());
        doNothing().when(exercise).setEndTime((Date) any());
        doNothing().when(exercise).setExerciseId((Integer) any());
        doNothing().when(exercise).setSCourseId((Integer) any());
        doNothing().when(exercise).setSceneId((Integer) any());
        doNothing().when(exercise).setStartTime((Date) any());
        exercise.setAttachmentPath("Attachment Path");
        exercise.setContent("Not all who wander are lost");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        exercise.setEndTime(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        exercise.setExerciseId(123);
        exercise.setSCourseId(123);
        exercise.setSceneId(123);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        exercise.setStartTime(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        courseServiceImpl.addCourseExercise(exercise);
    }

    /**
     * Method under test: default or parameterless constructor of {@link CourseServiceImpl}
     */
    @Test
    void testConstructor() {
        CourseServiceImpl actualCourseServiceImpl = new CourseServiceImpl();
        assertNull(actualCourseServiceImpl.getBaseMapper());
        assertNull(actualCourseServiceImpl.studentService);
        assertNull(actualCourseServiceImpl.studentExerciseRelationService);
        assertNull(actualCourseServiceImpl.sCourseService);
        assertNull(actualCourseServiceImpl.sCourseClassRelationService);
        assertNull(actualCourseServiceImpl.materialService);
        assertNull(actualCourseServiceImpl.exerciseService);
        assertNull(actualCourseServiceImpl.courseTimeService);
        assertNull(actualCourseServiceImpl.courseService);
        Class<Course> expectedEntityClass = Course.class;
        assertSame(expectedEntityClass, actualCourseServiceImpl.getEntityClass());
    }
}

