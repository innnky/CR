package xyz.innky.graduationproject.web.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.ClassInfo;

/**
 * 
 * @TableName class_info
 */
@Data
public class ClassInfoVo extends ClassInfo {

    private String teacherName;

    private Integer studentCount;


}