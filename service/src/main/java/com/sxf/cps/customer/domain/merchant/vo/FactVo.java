package com.sxf.cps.customer.domain.merchant.vo;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zscome
 * DateTime: 2020-03-29 22:21
 */
@Embeddable
@Data
@ToString
@Accessors(chain = true)
public class FactVo implements Serializable {
    private BrandEnum brandId;
    private String factId;
    private String factSn;
    private FactStatusEnum factStatus;
    private FactStateEnum factState;
    private BrandFlagEnum brandFlag;
    private Timestamp installDate;
    private Timestamp activeDate;

    @Basic
    @Column(name = "fact_id", length = 32)
    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    @Basic
    @Column(name = "fact_sn", length = 32)
    public String getFactSn() {
        return factSn;
    }

    public void setFactSn(String factSn) {
        this.factSn = factSn;
    }

    @Enumerated
    @Column(name = "fact_status")
    public FactStatusEnum getFactStatus() {
        return factStatus;
    }

    public void setFactStatus(FactStatusEnum factStatus) {
        this.factStatus = factStatus;
    }

    @Enumerated
    @Column(name = "fact_state")
    public FactStateEnum getFactState() {
        return factState;
    }

    public void setFactState(FactStateEnum factState) {
        this.factState = factState;
    }

    @Basic
    @Column(name = "brand_flag")
    public BrandFlagEnum getBrandFlag() {
        return brandFlag;
    }

    public void setBrandFlag(BrandFlagEnum brandFlag) {
        this.brandFlag = brandFlag;
    }

    @Basic
    @Column(name = "install_date")
    public Timestamp getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Timestamp installDate) {
        this.installDate = installDate;
    }

    @Basic
    @Column(name = "active_date")
    public Timestamp getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Timestamp activeDate) {
        this.activeDate = activeDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "brand_id", length = 10)
    public BrandEnum getBrandId() {
        return brandId;
    }

    public void setBrandId(BrandEnum brandId) {
        this.brandId = brandId;
    }

}
