package com.sxf.cps.customer.infrastructure.util.api;

import org.springframework.data.domain.ExampleMatcher;

/**
 * FIXME 完美解决：ExampleMatcher扩展问题；
 */
public class ExampleMatchers {

    private final static ExampleMatchers instance = new ExampleMatchers();

    public static ExampleMatchers builder() {
        return instance;
    }

    private ExampleMatchers() {
    }

    public <T> ExampleMatchers withMatcher(Fn<T, Object> fn, ExampleMatcher.GenericPropertyMatcher genericPropertyMatcher) {
        ExampleMatcher matcher = (build() == null ? ExampleMatcher.matching() : build()).withMatcher(FiledHelper.field(fn), genericPropertyMatcher);
        ExampleMatchers helper = new ExampleMatchers() {
            public ExampleMatcher build() {
                return matcher;
            }
        };
        return helper;
    }

    public ExampleMatcher build() {
        return null;
    }


}
