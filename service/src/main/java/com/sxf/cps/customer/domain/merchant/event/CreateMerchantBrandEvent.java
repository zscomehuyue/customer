package com.sxf.cps.customer.domain.merchant.event;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantBrandEvent implements Serializable {
    private String userId;
    private String factId;
    private String factSn;
    private String rateId;
    private BrandEnum brandId;
    private FactStatusEnum factStatus;
    private FactStateEnum factState;
    private BrandFlagEnum brandFlag;
    private String name;
    private String mobile;
    private String mobileCipher;

}
