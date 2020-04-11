package com.sxf.cps.customer.infrastructure.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author: zscome
 * DateTime: 2019-03-27 20:38
 * <p>
 * Example:
 * <pre> {@code
 *  public void handleChannelEvent(ChannelEvent event) {
 *         String info = info(() -> "=handleChannelEvent=>eventType=%s , channel=%s , eventId=%s ", event.getEventType().getValue(), event.getBody(), event.getId());
 *         try {
 *             //业务逻辑
 *         } catch (Exception e) {
 *            error(() -> "%s ,error:%s", info, e);
 *         }
 *        info(() -> "<%s .", info);
 *     }
 *
 * }</pre>
 */
@SuppressWarnings("Duplicates")
public class LogUtils {

    private final static Logger logger = LoggerFactory.getLogger(LogUtils.class);
    private final static String FORMAT_RESULT_ERROR = "LogUtils Format Error : Please check your msg format!";
    private final static String TO_STRING_METHOD_NAME = "toString";
    private final static String RESULT_NULL = "null";
    private final static String LOG_SUFFIX_END = "=>";
    private final static String LOG_SUFFIX_START = "=";

    public static String format(String msg, Object... args) {
        try {
            return String.format(msg, Arrays.stream(args).map(o -> {
                if (null == o) {
                    return RESULT_NULL;
                }
                if (Throwable.class.isAssignableFrom(o.getClass())) {
                    return ExceptionUtils.getStackTrace((Throwable) o);
                } else if (String.class.isAssignableFrom(o.getClass())) {
                    return (String) o;
                } else {
                    if (null != findMethod(o.getClass(), TO_STRING_METHOD_NAME)) {
                        return o.toString();
                    } else {
                        return ToStringBuilder.reflectionToString(o);
                    }
                }
            }).toArray());
        } catch (Exception e) {
            error(() -> getFullStackTrace(e));
        }
        return FORMAT_RESULT_ERROR;
    }

    public static String getFullStackTrace(Throwable ex) {
        return ExceptionUtils.getStackTrace(ex);
    }

    public static String info(Supplier<String> supplier, Object... formatObj) {
        Logger logger = getBizLog(LogUtils.class);
        if (logger.isInfoEnabled()) {
            String msg = format(supplier.get(), formatObj);
            logger.info(msg);
            return msg;
        }
        return RESULT_NULL;
    }

    public static String debug(Supplier<String> supplier, Object... formatObj) {
        Logger logger = getBizLog(LogUtils.class);
        if (logger.isDebugEnabled()) {
            String msg = format(supplier.get(), formatObj);
            logger.debug(msg);
            return msg;
        }
        return RESULT_NULL;
    }

    public static String warn(Supplier<String> supplier, Object... formatObj) {
        Logger logger = getBizLog(LogUtils.class);
        if (logger.isWarnEnabled()) {
            String msg = format(supplier.get(), formatObj);
            logger.warn(msg);
            return msg;
        }
        return RESULT_NULL;
    }

    /**
     * @see LogUtils#error(Supplier, Object...)
     */
    public static String error(Logger logger, Supplier<String> supplier, Object... formatObj) {
        if (logger.isErrorEnabled()) {
            String msg = format(supplier.get(), formatObj);
            logger.error(msg);
            return msg;
        }
        return RESULT_NULL;
    }

    public static String error(Supplier<String> supplier, Object... formatObj) {
        StackTraceElement stack = getStackTraceElement(LogUtils.class);
        if (formatObj.length > 0 && RESULT_NULL.equals(formatObj[0]) && null != stack) {
            formatObj[0] = LOG_SUFFIX_START + stack.getMethodName() + LOG_SUFFIX_END;
        }
        return error(getBizLog(stack), supplier, formatObj);
    }

    public static void tryCatch(exec fun) {
        StackTraceElement stack = getStackTraceElement(LogUtils.class);
        try {
            fun.exec();
        } catch (Exception e) {
            if (null != stack) {
                Logger logger = LoggerFactory.getLogger(stack.getClassName());
                if (logger.isErrorEnabled()) {
                    logger.error("=" + stack.getMethodName() + "=>errors:", e);
                }
            } else {
                if (logger.isErrorEnabled()) {
                    logger.error("=tryCatch=>errors:", e);
                }
            }
        }
    }

    public static void tryCatch(Supplier fun) {
        StackTraceElement stack = getStackTraceElement(LogUtils.class);
        try {
            fun.get();
        } catch (Exception e) {
            if (null != stack) {
                Logger logger = LoggerFactory.getLogger(stack.getClassName());
                if (logger.isErrorEnabled()) {
                    logger.error("=" + stack.getMethodName() + "=>errors:", e);
                }
            } else {
                if (logger.isErrorEnabled()) {
                    logger.error("=tryCatch=>errors:", e);
                }
            }
        }
    }

    private static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {
        Method[] methods = (clazz.isInterface() ? clazz.getMethods() : clazz.getDeclaredMethods());
        for (Method method : methods) {
            if (name.equals(method.getName()) &&
                    (paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
                return method;
            }
        }
        return null;
    }

    public static Logger getBizLog(Class clazz) {
        StackTraceElement stack = getStackTraceElement(clazz);
        if (null != stack) {
            return LoggerFactory.getLogger(stack.getClassName());
        }
        return logger;
    }

    private static Logger getBizLog(StackTraceElement stack) {
        if (null != stack) {
            return LoggerFactory.getLogger(stack.getClassName());
        }
        return logger;
    }

    private static StackTraceElement getStackTraceElement(Class clazz) {
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i].getClassName().equals(clazz.getName()) && i + 3 < stacks.length) {
                return stacks[i + 3];
            }
        }
        return null;
    }

    @FunctionalInterface
    public interface exec {
        void exec();
    }

}
