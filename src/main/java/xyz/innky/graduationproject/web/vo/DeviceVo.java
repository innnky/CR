package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Device;
import xyz.innky.graduationproject.web.pojo.Image;

@Data
public class DeviceVo extends Device {

    private Image image;

}
