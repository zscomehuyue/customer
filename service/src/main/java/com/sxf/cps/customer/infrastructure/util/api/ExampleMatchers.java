package com.sxf.cps.customer.infrastructure.util.api;

import org.springframework.data.domain.ExampleMatcher;

public interface ExampleMatchers {


    static <T> ExampleMatcher withMatcher(Fn<T, Object> fn, ExampleMatcher.GenericPropertyMatcher genericPropertyMatcher) {
        return ExampleMatcher.matching().withMatcher(FiledHelper.field(fn), genericPropertyMatcher);
    }


    static <T> ExampleMatcher withMatcher(Fn<T, Object> fn, ExampleMatcher.GenericPropertyMatcher genericPropertyMatcher, ExampleMatcher matcher) {
        return matcher.withMatcher(FiledHelper.field(fn), genericPropertyMatcher);
    }


}
