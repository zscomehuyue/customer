package com.sxf.cps.customer.domain.merchant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sxf.cps.customer.domain.merchant.vo.VipVo;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

@Entity
@ToString
@Table(name = "merchant")
//json格式化报告错误，需要排出如下字段；
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler","merchantCode"})
public class MerchantEntity implements Serializable {
    private Long id;
    private String name;
    private String mobile;
    private String merchantCode;
    private String mobileCipher;
    private Timestamp registerDate;
    private Timestamp activeDate;
    private Timestamp callBackTime;
    private Timestamp created;
    private Timestamp modified;
    private VipVo vipVo;
    private List<MerchantBrandEntity> merchantBrandEntityList;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @OneToMany
    @JoinColumn(name = "merchant_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    public List<MerchantBrandEntity> getMerchantBrandEntityList() {
        return merchantBrandEntityList;
    }

    public void setMerchantBrandEntityList(List<MerchantBrandEntity> merchantBrandEntityList) {
        this.merchantBrandEntityList = merchantBrandEntityList;
    }

    @Embedded
    public VipVo getVipVo() {
        return vipVo;
    }

    public void setVipVo(VipVo vipVo) {
        this.vipVo = vipVo;
    }


}
