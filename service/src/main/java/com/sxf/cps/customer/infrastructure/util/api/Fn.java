package com.sxf.cps.customer.infrastructure.util.api;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author: zscome
 * DateTime: 2020-01-08 17:15
 */
public interface Fn<T, R> extends Function<T, R>, Serializable {
}
