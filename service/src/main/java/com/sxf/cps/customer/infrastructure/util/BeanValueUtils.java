package com.sxf.cps.customer.infrastructure.util;

import org.axonframework.common.ReflectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zscome
 * DateTime: 2020-04-01 14:04
 */
public class BeanValueUtils {


    public static <T> T initValue(Class<T> clazz, int value) {
        try {
            T obj = clazz.newInstance();
            Arrays.stream(clazz.getDeclaredFields()).forEach(f -> {
                if (Long.class.getTypeName().equals(f.getGenericType().getTypeName())) {
                    ReflectionUtils.setFieldValue(f, obj, Long.valueOf(value));
                } else if (Integer.class.getTypeName().equals(f.getGenericType().getTypeName())) {
                    ReflectionUtils.setFieldValue(f, obj, Integer.valueOf(value));
                } else if (Timestamp.class.getTypeName().equals(f.getGenericType().getTypeName())) {
                    ReflectionUtils.setFieldValue(f, obj, Timestamp.valueOf(LocalDateTime.now()));
                } else if (String.class.getTypeName().equals(f.getGenericType().getTypeName())) {
                    ReflectionUtils.setFieldValue(f, obj, f.getName()+"-"+value);
                } else if (!f.getGenericType().getClass().isPrimitive() &&
                        !List.class.isAssignableFrom(f.getType()) &&
                        !f.getType().isEnum()) {
                    ReflectionUtils.setFieldValue(f, obj, initValue(f.getType(), value));
                }
            });
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
