package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Device;

import java.util.Date;

@Data
public class StudentDeviceReservation {
    private Date date;
    private Integer sequence;
    private Device device;
    private Date startTime;
}
