package com.sxf.cps.customer.domain.merchant.enumtype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FactStateEnum {
//    默认0装机1解绑2

    DEFAULT_VALUE(0, "默认"),
    INSTALL(1, "装机"),
    UNBIND(2, "解绑"),
    ;
    private Integer value;
    private String name;

}
