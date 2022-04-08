package xyz.innky.graduationproject.common.utils;

import xyz.innky.graduationproject.web.vo.Result;

public class ResultUtil {
    public static Result returnResultByCondition(boolean condition, String msgPrefix){
        if (condition){
            return Result.ok(msgPrefix+"成功");
        }

        else {
            return Result.err(msgPrefix+"失败");
        }
    }
}
