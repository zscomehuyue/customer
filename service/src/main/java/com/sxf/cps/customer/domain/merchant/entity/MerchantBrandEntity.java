package com.sxf.cps.customer.domain.merchant.entity;


import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "merchant_brand", schema = "xlm")
public class MerchantBrandEntity {
    private String uuid;

    //FIXME 没有自动生成merchantId，这个自己的不需要吗？
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
    private RateCheckInEntity rateCheckInEntity;
    private List<RateCheckInLogEntity> rateCheckInLogEntityList;


    @OneToOne
    @JoinColumn(name = "uuid", referencedColumnName = "merchant_brand_id")
    public RateCheckInEntity getRateCheckInEntity() {
        return rateCheckInEntity;
    }

    public void setRateCheckInEntity(RateCheckInEntity rateCheckInEntity) {
        this.rateCheckInEntity = rateCheckInEntity;
    }

    @OneToMany
    @JoinColumn(name = "merchant_brand_id", referencedColumnName = "uuid")
    public List<RateCheckInLogEntity> getRateCheckInLogEntityList() {
        return rateCheckInLogEntityList;
    }

    public void setRateCheckInLogEntityList(List<RateCheckInLogEntity> rateCheckInLogEntityList) {
        this.rateCheckInLogEntityList = rateCheckInLogEntityList;
    }

    @Id
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "merchant_code")
    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "brand_id")
    public BrandEnum getBrandId() {
        return brandId;
    }

    public void setBrandId(BrandEnum brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "fact_id")
    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    @Basic
    @Column(name = "fact_sn")
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile_cipher")
    public String getMobileCipher() {
        return mobileCipher;
    }

    public void setMobileCipher(String mobileCipher) {
        this.mobileCipher = mobileCipher;
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

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "modified")
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Basic
    @Column(name = "merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantBrandEntity that = (MerchantBrandEntity) o;
        return brandFlag == that.brandFlag &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(merchantCode, that.merchantCode) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(factId, that.factId) &&
                Objects.equals(factSn, that.factSn) &&
                Objects.equals(factStatus, that.factStatus) &&
                Objects.equals(factState, that.factState) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(mobileCipher, that.mobileCipher) &&
                Objects.equals(installDate, that.installDate) &&
                Objects.equals(activeDate, that.activeDate) &&
                Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, merchantCode, brandId, userId, factId, factSn, factStatus, factState, brandFlag, name, mobile, mobileCipher, installDate, activeDate, created, modified);
    }
}
