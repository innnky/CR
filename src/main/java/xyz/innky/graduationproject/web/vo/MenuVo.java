package xyz.innky.graduationproject.web.vo;

import lombok.Data;
import xyz.innky.graduationproject.web.pojo.Menu;

import java.util.List;

@Data
public class MenuVo extends Menu {
    private List<MenuVo> children;

    @Override
    public String toString() {
        return "MenuVo{" +super.toString()+
                ",children=" + children +
                '}';
    }
}
