package com.sxf.cps.customer.domain.merchant.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: zscome
 * DateTime: 2020-04-01 09:31
 */
@Embeddable
@Data
@ToString
@Accessors(chain = true)
public class VipVo implements Serializable {
    private Integer vipType;
    private Timestamp vipStartDate;
    private Timestamp vipEndDate;

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

}
