package xyz.innky.graduationproject.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Student;
import xyz.innky.graduationproject.web.service.StudentService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/admin/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
     @PostMapping("/")
    public Result addStudent(@RequestBody Student student) {
        return ResultUtil.returnResultByCondition(studentService.addStudent(student),"添加学生");
    }

    @PutMapping("/")
    public Result updateStudent(@RequestBody Student student) {
        return ResultUtil.returnResultByCondition(studentService.updateById(student),"修改学生");
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Integer id) {
        return ResultUtil.returnResultByCondition(studentService.removeById(id),"删除学生");
    }

    @GetMapping("/")
    public Result getAllStudent(Integer page, Integer pageSize,
                                String  studentName,Integer studentId, String college, String grade,
                                String major, String className, String gender) {
        return Result.ok(studentService.getAllStudents(page,pageSize,studentName, studentId, college, grade, major, className, gender));
    }

    @GetMapping("/{id}/history")
    public  Result getHistoryClass(@PathVariable("id") Integer id){
         return Result.ok(studentService.getHistoryClass(id));
    }

}
