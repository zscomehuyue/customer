package com.sxf.cps.customer.api.merchant.dto;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class MerchantDto implements Serializable {
    private String uuid;
    private String merchantCode;
    private String name;
    private String mobile;
    private String mobileCipher;
    private Integer vipType;
    private Timestamp vipStartDate;
    private Timestamp vipEndDate;
    private Timestamp registerDate;
    private Timestamp activeDate;
    private Timestamp callBackTime;
    private BrandEnum brandId;
    private String userId;
    private Timestamp created;
    private Timestamp modified;

}
