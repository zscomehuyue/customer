package com.sxf.cps.customer.domain.merchant.entity;


import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import com.sxf.cps.customer.domain.merchant.vo.FactVo;
import com.sxf.cps.customer.domain.merchant.vo.MerchantVo;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "merchant_brand")
public class MerchantBrandEntity {

    private Long uuid;

    //FIXME 没有自动生成merchantId，这个自己的不需要吗？
    private MerchantVo merchantVo;
    private BrandEnum brandId;
    private String userId;
    private FactVo factVo;
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
    @GeneratedValue
    @Column(name = "uuid")
    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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
//    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    @Embedded
    public MerchantVo getMerchantVo() {
        return merchantVo;
    }

    public void setMerchantVo(MerchantVo merchantVo) {
        this.merchantVo = merchantVo;
    }

    @Embedded
    public FactVo getFactVo() {
        return factVo;
    }

    public void setFactVo(FactVo factVo) {
        this.factVo = factVo;
    }

}
