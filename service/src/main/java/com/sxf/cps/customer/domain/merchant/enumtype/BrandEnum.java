package com.sxf.cps.customer.domain.merchant.enumtype;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BrandEnum {
    MPOS("MPOS", "MPOS"),
    SPOS("SPOS", "SPOS"),
    EPOS("EPOS", "EPOS"),
    ;
    private String value;
    private String name;

}
