package com.sxf.cps.customer.domain.merchant.cmd;


import com.sxf.cps.customer.domain.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * FIXME 遵循原有的业务，流程；在此基础上设计；在页面不改变的情况下，无需考虑流程的该变；
 *
 * FIXME 解决矛盾：
 * FIXME 1.根据目前的流程，找到适应的设计方法；
 * FIXME 2.改变目前的产品流程，在此基础下设计；
 *
 * 登记时， 同时发送1个命令，包含1张表需要的字段；
 * 装机时，使用mpos商户和新联梦商户归一，更新相关字段；同时弥补相关属性；
 */
@Aggregate
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Merchant {
    @AggregateIdentifier
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


    @Resource
    private MerchantStruct merchantStruct;

    @CommandHandler
    public Merchant(CreateMerchantCmd command) {
        apply(merchantStruct.toCreateMerchantEvent(command));
    }

    /**
     * FIXME 什么时候，会调用该方法，该方法启动什么作用；数据流是什么样的；
     * 接受事件来更新领域状态
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(CreateMerchantEvent event) {
        merchantStruct.updateMerchant(event, this);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant that = (Merchant) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
