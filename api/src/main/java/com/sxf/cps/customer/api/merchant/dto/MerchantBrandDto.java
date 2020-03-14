package com.sxf.cps.customer.api.merchant.dto;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class MerchantBrandDto {
    private String merchantCode;
    private BrandEnum brandId;
    private String merchantId;
    private String userId;
    private String factId;
    private String factSn;
    private FactStatusEnum factStatus;
    private FactStateEnum factState;
    private BrandFlagEnum brandFlag;
    private String name;
    private String mobile;
    private String mobileCipher;
    private Timestamp installDate;
    private Timestamp activeDate;
    private Timestamp created;
    private Timestamp modified;
}
