package com.sxf.cps.customer.domain.merchant.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rate_check_in_log", schema = "xlm")
public class RateCheckInLogEntity {
    private String uuid;
    private String factSn;
    private String factId;
    private String brandId;
    private String rateId;
    private String rateDesc;
    private String checkInUser;
    private Timestamp created;
    private Timestamp modified;

    @Id
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "fact_sn")
    public String getFactSn() {
        return factSn;
    }

    public void setFactSn(String factSn) {
        this.factSn = factSn;
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
    @Column(name = "brand_id")
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "rate_id")
    public String getRateId() {
        return rateId;
    }

    public void setRateId(String rateId) {
        this.rateId = rateId;
    }

    @Basic
    @Column(name = "rate_desc")
    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    @Basic
    @Column(name = "check_in_user")
    public String getCheckInUser() {
        return checkInUser;
    }

    public void setCheckInUser(String checkInUser) {
        this.checkInUser = checkInUser;
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
        RateCheckInLogEntity that = (RateCheckInLogEntity) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(factSn, that.factSn) &&
                Objects.equals(factId, that.factId) &&
                Objects.equals(brandId, that.brandId) &&
                Objects.equals(rateId, that.rateId) &&
                Objects.equals(rateDesc, that.rateDesc) &&
                Objects.equals(checkInUser, that.checkInUser) &&
                Objects.equals(created, that.created) &&
                Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, factSn, factId, brandId, rateId, rateDesc, checkInUser, created, modified);
    }
}
