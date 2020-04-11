package com.sxf.cps.customer.api.merchant.form;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class CreateMerchantBrandForm implements Serializable{
    private String id;

    private String factId;

    private String factSn;

    private BrandFlagEnum brandFlag;

    private String name;

    private String mobile;

    private String mobileCipher;

    private BrandEnum brandId;

    private String userId;

    private FactStatusEnum factStatus;

    private FactStateEnum factState;

    private String rateId;
}
