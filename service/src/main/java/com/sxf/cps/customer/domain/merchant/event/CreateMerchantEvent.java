package com.sxf.cps.customer.domain.merchant.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateMerchantEvent {
    private String uuid;
    private String merchantCode;
    private String factId;
    private String factSn;
    private Timestamp registerDate;
    private String name;
    private String mobile;
    private String mobileCipher;

}
