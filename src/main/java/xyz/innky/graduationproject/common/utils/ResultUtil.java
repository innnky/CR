package xyz.innky.graduationproject.common.utils;

import xyz.innky.graduationproject.web.vo.Result;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ResultUtil {
    public static Result returnResultByCondition(boolean condition, String msgPrefix){
        if (condition){
            return Result.ok(msgPrefix+"成功");
        }

        else {
            return Result.err(msgPrefix+"失败");
        }
    }
    public static Result returnResultByConditionLambda(Supplier<Boolean> supplier, String msgPrefix){
        try {
            if (supplier.get()){
                return Result.ok(msgPrefix+"成功");
            }

            else {
                return Result.err(msgPrefix+"失败");
            }
        }
        catch (Exception e){
            return Result.err(msgPrefix+"失败");
        }

    }
}
