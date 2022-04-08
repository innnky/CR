package xyz.innky.graduationproject.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    Integer code;
    String message;
    Object data;
    public static Result ok(String msg){
        return new Result(200, msg, null);
    }
    public static Result err(String msg){
        return new Result(500, msg, null);
    }
    public static Result ok( Object data){
        return new Result(200, "", data);
    }
}
