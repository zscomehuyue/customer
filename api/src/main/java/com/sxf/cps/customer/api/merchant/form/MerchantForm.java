package com.sxf.cps.customer.api.merchant.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
@NoArgsConstructor
public class MerchantForm implements Serializable {
    private String merchantCode;
    private String name;
    private String mobile;
    private int page = 1;
    private int size = 10;
}
