package com.sxf.cps.customer.domain.merchant.enumtype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BrandFlagEnum {
    DEFAULT_VALUE(1, "默认"),
    NOT_DEFAULT_VALUE(0, "非默认"),
    ;
    private Integer value;
    private String name;
}
