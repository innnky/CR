package xyz.innky.graduationproject.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class SCVO {
    private Integer courseId;
    private List<Integer> classIds;
}
