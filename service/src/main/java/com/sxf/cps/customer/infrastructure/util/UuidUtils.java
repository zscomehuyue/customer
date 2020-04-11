package com.sxf.cps.customer.infrastructure.util;

import java.util.UUID;

public class UuidUtils {

    public static String creatUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
