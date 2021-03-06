package com.sxf.cps.customer.domain.merchant.entity;


import com.sxf.cps.customer.domain.merchant.vo.FactVo;
import com.sxf.cps.customer.domain.merchant.vo.MerchantVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

/**
 * FIXME 全部单向关联，禁止双向关联；
 * FIXME 禁止所有的级联操作；
 * FIXME 禁止所有外键生成，在业务方面控制；
 * FIXME 全部在Root关联；
 * FIXME 实体是否，不配置任何关联对象，岂不是根简单？？，但是查询是需要的；
 */
@ToString
@Data
@Entity
@Table(name = "merchant_brand")
//NamedEntityGraph and EntityGraph 解决n+1
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@NamedEntityGraph(name = "MerchantBrandEntity.rateCheckInLogEntityList",
        attributeNodes = {
                @NamedAttributeNode("rateCheckInEntity"),
                @NamedAttributeNode("rateCheckInLogEntityList")})
public class MerchantBrandEntity implements Serializable {

    private String id;
    private String userId;
    private FactVo factVo;
    private MerchantVo merchantVo;
    private Timestamp created;
    private Timestamp modified;
    private RateCheckInEntity rateCheckInEntity;
    private List<RateCheckInLogEntity> rateCheckInLogEntityList;


    /**
     * MerchantBrandEntity中的uuid，通过rateCheckInEntity的merchant_brand_id进行关联
     */
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false,name = "id", referencedColumnName = "merchant_brand_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    public RateCheckInEntity getRateCheckInEntity() {
        return rateCheckInEntity;
    }

    public void setRateCheckInEntity(RateCheckInEntity rateCheckInEntity) {
        this.rateCheckInEntity = rateCheckInEntity;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(insertable = false, updatable = false, name = "merchant_brand_id", foreignKey = @ForeignKey(NO_CONSTRAINT))
    public List<RateCheckInLogEntity> getRateCheckInLogEntityList() {
        return rateCheckInLogEntityList;
    }

    public void setRateCheckInLogEntityList(List<RateCheckInLogEntity> rateCheckInLogEntityList) {
        this.rateCheckInLogEntityList = rateCheckInLogEntityList;
    }

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
