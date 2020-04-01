package com.sxf.cps.customer.api.merchant.form;

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
public class CreateMerchantForm implements Serializable {
    private String uuid;
    private String merchantCode;
    private String factId;
    private String factSn;
    private Timestamp registerDate;
    private String name;
    private String mobile;
    private String mobileCipher;
}
