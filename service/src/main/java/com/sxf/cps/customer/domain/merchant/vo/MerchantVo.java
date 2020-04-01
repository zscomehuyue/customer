package com.sxf.cps.customer.domain.merchant.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zscome
 * DateTime: 2020-03-29 22:09
 */
@Embeddable
@Data
@ToString
@Accessors(chain = true)
public class MerchantVo implements Serializable {
    private Long merchantId;
    private String merchantCode;
    private String name;
    private String mobile;
    private String mobileCipher;

    @Basic
    @Column(name = "merchant_code", length = 32)
    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    @Basic
    @Column(name = "name", length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mobile", length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile_cipher", length = 64)
    public String getMobileCipher() {
        return mobileCipher;
    }

    public void setMobileCipher(String mobileCipher) {
        this.mobileCipher = mobileCipher;
    }

    @Basic
    @Column(name = "merchant_id")
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }


}
