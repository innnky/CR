package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Scene;

import java.util.List;

@Data
public class SceneVo extends Scene {
    private List<Integer> deviceIds;
}
