package xyz.innky.graduationproject.web.params;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Scene;

import java.util.List;

@Data
public class SceneParam extends Scene {
    List<Integer> deviceIds;
    List<String> roles;
}
