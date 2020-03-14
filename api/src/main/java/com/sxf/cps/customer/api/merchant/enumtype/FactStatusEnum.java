package com.sxf.cps.customer.api.merchant.enumtype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FactStatusEnum {

    //激活状态0已登记2未激活1已激活

    REGISTED(0, "登记"),
    ACTIVED(1, "已激活"),
    NOT_ACTIVED(2, "未激活"),
    ;
    private Integer value;
    private String name;
}
