package com.sxf.cps.customer;

import javax.transaction.Transactional;
import java.lang.annotation.*;

@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@Transactional(rollbackOn = {Exception.class})
public @interface DefaultTransaction {
}
