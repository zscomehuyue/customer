package com.sxf.cps.customer.domain.merchant.cmd;

import com.sxf.cps.customer.api.merchant.enumtype.BrandEnum;
import com.sxf.cps.customer.api.merchant.enumtype.BrandFlagEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStateEnum;
import com.sxf.cps.customer.api.merchant.enumtype.FactStatusEnum;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Getter
@Setter
@Aggregate
@Accessors(chain = true)
@NoArgsConstructor
public class MerchantBrand {

    @AggregateIdentifier
    private String uuid;
    private String merchantId;
    private String merchantCode;
    private BrandEnum brandId;
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
    private RateCheckIn rateCheckIn;
    private List<RateCheckInLog> rateCheckInLogList;


    @Resource
    private MerchantStruct merchantStruct;

    /**
     * 该命令同时保存brand和merchant表；
     *
     * @param command
     */
    @CommandHandler
    public MerchantBrand(CreateMerchantBrandCmd command) {
        apply(merchantStruct.toCreateMerchantBrandEvent(command));
    }

    /**
     * FIXME 什么时候，会调用该方法，该方法启动什么作用；数据流是什么样的；
     * 接受事件来更新领域状态
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(CreateMerchantBrandEvent event) {
        merchantStruct.updateMerchantBrand(event, this);
        if (null == this.rateCheckIn) {
            this.rateCheckIn = merchantStruct.toRateCheckIn(event);
        } else {
            merchantStruct.updateRateCheckIn(event, this.rateCheckIn);
        }
        RateCheckInLog log = merchantStruct.toRateCheckInLog(event);
        if (null == this.rateCheckInLogList) {
            this.rateCheckInLogList = new ArrayList<>();
            this.rateCheckInLogList.add(log);
        } else if (!this.rateCheckInLogList.contains(log)) {
            this.rateCheckInLogList.add(log);
        } else {
            this.rateCheckInLogList.remove(log);
            this.rateCheckInLogList.add(log);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantBrand that = (MerchantBrand) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

}
