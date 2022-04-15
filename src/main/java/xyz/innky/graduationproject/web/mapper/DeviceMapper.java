package xyz.innky.graduationproject.web.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import xyz.innky.graduationproject.web.pojo.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.innky.graduationproject.web.vo.DeviceVo;

import java.util.List;

/**
* @author xingyijin
* @description 针对表【device】的数据库操作Mapper
* @createDate 2022-04-14 17:36:12
* @Entity xyz.innky.graduationproject.web.pojo.Device
*/
public interface DeviceMapper extends BaseMapper<Device> {
    Page<DeviceVo> selectPageVo(@Param("deviceVoPage") Page<DeviceVo> deviceVoPage, @Param("ew") LambdaQueryWrapper<Device> ew);

    int updateStatusByUuid(@Param("status") Integer status, @Param("uuid") String uuid);

    List<Device> getUnusedDevice();
}




