package com.sxf.cps.customer.domain.merchant.entity;

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
@ToString
@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Table(name = "rate_check_in_log")
public class RateCheckInLogEntity implements Serializable {
    private String id;
    private String merchantBrandId;
    private String factSn;
    private String factId;
    private String brandId;
    private String rateId;
    private String rateDesc;
    private String creator;
    private Timestamp created;
    private Timestamp modified;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fact_sn", length = 32)
    public String getFactSn() {
        return factSn;
    }

    public void setFactSn(String factSn) {
        this.factSn = factSn;
    }

    @Basic
    @Column(name = "fact_id", length = 32)
    public String getFactId() {
        return factId;
    }

    public void setFactId(String factId) {
        this.factId = factId;
    }

    @Basic
    @Column(name = "brand_id", length = 10)
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "rate_id", length = 32)
    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    @Basic
    @Column(name = "rate_desc", length = 64)
    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    @Basic
    @Column(name = "creator", length = 64)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
    @Column(name = "merchant_brand_id")
    public String getMerchantBrandId() {
        return merchantBrandId;
    }

    public void setMerchantBrandId(String merchantBrandId) {
        this.merchantBrandId = merchantBrandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateCheckInLogEntity that = (RateCheckInLogEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(factSn, that.factSn) &&
                Objects.equals(factId, that.factId) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(rateId, that.rateId) &&
                Objects.equals(rateDesc, that.rateDesc) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, factSn, factId, brandId, rateId, rateDesc, creator, created, modified);
    }
}
