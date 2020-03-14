package com.sxf.cps.customer.application.service;

import com.sxf.cps.customer.infrastructure.util.DefaultTransaction;
import com.sxf.cps.customer.domain.merchant.event.CreateMerchantBrandEvent;
import com.sxf.cps.customer.domain.merchant.mapstruct.MerchantStruct;
import com.sxf.cps.customer.domain.merchant.service.MerchantBrandService;
import com.sxf.cps.customer.domain.merchant.service.RateCheckInLogService;
import com.sxf.cps.customer.domain.merchant.service.RateCheckInService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MerchantBrandAppService {

    @Resource
    private MerchantStruct merchantStruct;
    @Resource
    private RateCheckInService rateCheckInService;
    @Resource
    private RateCheckInLogService rateCheckInLogService;
    @Resource
    private MerchantBrandService merchantBrandService;

    @EventHandler
    @DefaultTransaction
    public void mergeMerchantBrand(CreateMerchantBrandEvent event){
        merchantBrandService.mergeMerchantBrandEntity(event,()->{
            rateCheckInService.mergeRateCheckIn(merchantStruct.toRateCheckInEntity(event));
            rateCheckInLogService.saveRateCheckInLogEntity(merchantStruct.toRateCheckInLogEntity(event));
            return null;
        });
    }

}
