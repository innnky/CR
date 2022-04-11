package xyz.innky.graduationproject.common.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class CopyUtil {
    public static <E> E copy(Object e,Class<E> clazz){
        E e1 = null;
        try {
            e1 = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(e,e1);

        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        }
        return e1;
    }
}
