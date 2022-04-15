package xyz.innky.graduationproject.web.mapper;

import xyz.innky.graduationproject.web.pojo.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.vo.ClassInfoVo;
/**
* @author xingyijin
* @description 针对表【class_info】的数据库操作Mapper
* @createDate 2022-04-14 21:03:58
* @Entity xyz.innky.graduationproject.web.pojo.ClassInfo
*/
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    Page<ClassInfoVo> selectPageVo(@Param("infoPage") Page<ClassInfoVo> infoPage, @Param("ew") Wrapper<ClassInfoVo> ew);

    ClassInfo selectByClassId(@Param("classId") Integer classId);

}




