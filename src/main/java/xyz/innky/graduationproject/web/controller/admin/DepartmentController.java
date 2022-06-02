package xyz.innky.graduationproject.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.innky.graduationproject.common.utils.ResultUtil;
import xyz.innky.graduationproject.web.pojo.Department;
import xyz.innky.graduationproject.web.service.DepartmentService;
import xyz.innky.graduationproject.web.vo.Result;

@RestController
@RequestMapping("/admin/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/")
    Result addDepartment(@RequestBody Department department){
        return ResultUtil.returnResultByConditionLambda(()->departmentService.addDeptartment(department),"添加部门");
    }

    @DeleteMapping("/{depId}")
    Result deleteDepartment(@PathVariable("depId") Integer depId){
        return ResultUtil.returnResultByConditionLambda(()->departmentService.removeById(depId), "删除部门");
    }

    @PutMapping("/")
    Result updateDepartment(@RequestBody Department department){
        return ResultUtil.returnResultByConditionLambda(()->departmentService.updateById(department),"修改部门");
    }

    @GetMapping("/")
    Result getAllDepartment(Integer page, Integer pageSize, Integer departmentId, String departmentName){
        return Result.ok(departmentService.getAllDepartment(page,pageSize, departmentId, departmentName));
    }

    @GetMapping("/list")
    Result getAllDepartment(){
        return Result.ok(departmentService.list());
    }

}
