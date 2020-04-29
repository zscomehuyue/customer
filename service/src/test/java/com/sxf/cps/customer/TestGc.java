package com.sxf.cps.customer;

import com.sxf.cps.customer.domain.merchant.entity.MerchantBrandEntity;
import com.sxf.cps.customer.infrastructure.util.BeanValueUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: zscome
 * DateTime: 2020-04-20 20:48
 */
public class TestGc {

    public static void main(String[] args) {
        AtomicLong index = new AtomicLong();
        for (int i = 1; i < 90000; i++) {
            List ls = new LinkedList();
            for (int j = 0; j < 10000; j++) {
                MerchantBrandEntity bean = BeanValueUtils.initValue(MerchantBrandEntity.class, i * j);
                ls.add(bean);
//                ls.remove(bean);
                bean = null;
                System.err.println(index.incrementAndGet());
//                System.gc();
            }
            ls.clear();
            ls = null;
            System.gc();
        }
    }
}
