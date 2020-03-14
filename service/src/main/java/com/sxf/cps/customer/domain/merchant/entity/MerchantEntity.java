package com.sxf.cps.customer.domain.merchant.entity;

import com.sxf.cps.customer.domain.merchant.enumtype.BrandEnum;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@Table(name = "merchant", schema = "xlm")
public class MerchantEntity {
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


    @OneToMany
    @JoinColumn(name = "merchant_id", referencedColumnName = "uuid")
    private List<MerchantBrandEntity> merchantBrandEntityList;

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
    @Column(name = "vip_type")
    public Integer getVipType() {
        return vipType;
    }

    public void setVipType(Integer vipType) {
        this.vipType = vipType;
    }

    @Basic
    @Column(name = "vip_start_date")
    public Timestamp getVipStartDate() {
        return vipStartDate;
    }

    public void setVipStartDate(Timestamp vipStartDate) {
        this.vipStartDate = vipStartDate;
    }

    @Basic
    @Column(name = "vip_end_date")
    public Timestamp getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(Timestamp vipEndDate) {
        this.vipEndDate = vipEndDate;
    }

    @Basic
    @Column(name = "register_date")
    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
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
    @Column(name = "call_back_time")
    public Timestamp getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Timestamp callBackTime) {
        this.callBackTime = callBackTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantEntity that = (MerchantEntity) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(merchantCode, that.merchantCode) &&
                Objects.equals(name, that.name) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(mobileCipher, that.mobileCipher) &&
                Objects.equals(vipType, that.vipType) &&
                Objects.equals(vipStartDate, that.vipStartDate) &&
                Objects.equals(vipEndDate, that.vipEndDate) &&
                Objects.equals(registerDate, that.registerDate) &&
                Objects.equals(activeDate, that.activeDate) &&
                Objects.equals(callBackTime, that.callBackTime) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, merchantCode, name, mobile, mobileCipher, vipType, vipStartDate, vipEndDate, registerDate, activeDate, callBackTime, brandId, userId, created, modified);
    }
}
